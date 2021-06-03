package com.java.chat.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.chat.dto.ChatRoomDto;
import com.java.chat.service.ChatService;
import com.java.homepage.HomeController;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value = "/chat/chatRoomList.do", method = RequestMethod.GET)
	public ModelAndView chatRoomList(Locale locale, Model model, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		//chatService.findAllRoom(mav); 전체 채팅방 조회
		chatService.findRoomById(mav);
		
		return mav;
	}
	
	// 채팅방 입장
	@RequestMapping(value = "/chat/chat.do", method = RequestMethod.GET)
	public ModelAndView chat(Locale locale, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("멀티채팅방 입장");
		// 세션 아이디가 채팅방 처음 방문인지 확인
		// 첫 방문이면 채팅 참여자에 아이디 추가
		
		ChatRoomDto chatRoomDto = chatService.addUserToChatRoom(request, Integer.parseInt(request.getParameter("chatRoomNo")));
		//chatService.findRoomById(Integer.parseInt(request.getParameter("chatRoomNo")));
		
		// 이전 채팅메시지 읽어오기
		chatService.readChatMessage(request, mav);
		
		mav.addObject("chatRoomDto", chatRoomDto);
		mav.addObject("chatRoomNo", request.getParameter("chatRoomNo"));
		if(chatRoomDto.getUsers() != null) {
			String users = chatRoomDto.getUsers();
			String[] user = users.split(";");
			mav.addObject("users", user);
		} else {
			mav.addObject("users",null);
		}
		// 해당 채팅방으로 입장
		mav.setViewName("chat/chat");
		return mav;
	}
	
	// 채팅메시지 저장
	@ResponseBody
	@RequestMapping(value="/chat/insertChatMessage.do", method=RequestMethod.POST)
	public int insertChatMessage(@RequestParam(value="msgList[]") List<String> msgList, @RequestParam(value="chatRoomNo") int chatRoomNo, HttpServletRequest request ) {
		
		chatService.insertChatMessage(request, msgList, chatRoomNo);
		return 0;
		
	}
	
	@RequestMapping(value="/chat/exitChatRoom.do", method=RequestMethod.GET)
	public ModelAndView exitChatRoom(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		chatService.exitChatRoom(mav);
		
		return mav;
	}
}
