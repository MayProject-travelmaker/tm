package com.java.chat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.chat.dao.ChatDao;
import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;

@Component
public class ChatService {
	
	@Autowired
	private ChatDao chatDao;
	private ChatMessageDto chatMessageDto;
	
	// 모든 채팅방 조회
	public void findAllRoom(ModelAndView mav){
		List<ChatRoomDto> chatRoomDtoList = chatDao.findAllRoom();
		System.out.println("findAllRoom ChatService : " + chatRoomDtoList);
		mav.addObject("chatRoomList", chatRoomDtoList);
		
		mav.setViewName("chat/chatRoomList");
	}
	
	// 참가한 채팅방 리스트 조회
	public void findRoomById(ModelAndView mav){
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id : "+id);
		int check = chatDao.findRoomByIdCnt(id);
		if(check > 0) {
			// 해당회원이 가지고있는 채팅이 있으면
			List<ChatRoomDto> chatRoomDtoList = chatDao.findRoomById(id);
			mav.addObject("chatRoomList", chatRoomDtoList);
			System.out.println("chatRoomDtoList : " + chatRoomDtoList);
		} else {
			mav.addObject("chatRoomList", null);
		}
		
		mav.setViewName("chat/chatRoomList");
	}
	
	public int createChatRoom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("id")+request.getParameter("boardNo") );
		String id = (String) session.getAttribute("id"); // 방 생성 아이디
		int boardCode = 11;
		
		ChatRoomDto chatRoomDto = new ChatRoomDto();
		chatRoomDto.setPostId(id);
		chatRoomDto.setBoardCode(boardCode);
		
		int check = chatDao.createChatRoom(chatRoomDto);
		
		return check;
	}

	public int insertChatMessage(HttpServletRequest request, List<String> msgList, int chatRoomNo) {
			String fileName = Integer.toString(chatRoomNo);
			String txt = "";
			
			// 메시지가 존재하면
			if(msgList.size() > 0) {
				
				// 파일 경로 생성
				File path = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")); 
				System.out.println(path);
				path.mkdir();
				// 파일 경로가 존재하면
				if (path.exists() && path.isDirectory()) {
					// 파일 객체 생성
					File file = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")+fileName+".txt");
					
					try {
						// true 지정시 파일의 기존 내용에 이어서 작성 (속도향상을 위해 bufferwriter 혼용)
						BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
						
						// 파일 안에 문자열 쓰기
						for(int i = 0; i < msgList.size(); i++) {
							txt += msgList.get(i) + "\n";
						}
						fw.write(txt);
						// 객체 닫기
						fw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			///int check = chatDao.insertChatMessage(chatMessageDto);
			//System.out.println("저장완료");
			
			return 1;
		
	}

	// 이전 채팅메시지 읽어오기
	public void readChatMessage(HttpServletRequest request, ModelAndView mav) {
		// 채팅방 번호
		int chatRoomNo = Integer.parseInt((String) request.getParameter("chatRoomNo"));
		// 채팅 파일명
		String fileName = Integer.toString(chatRoomNo);
		// 기존 채팅메시지 리스트
		ArrayList<String> msgData = new ArrayList<String>();
		try {
			// 파일 객체 생성
			File file = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")+fileName+".txt");
			
			// 해당 채팅번호의 파일이 존재하면 파일 읽어오기
			if (file.exists()) {
				//입력 스트림 생성
	            FileReader filereader = new FileReader(file);
	            //입력 버퍼 생성
	            BufferedReader bufReader = new BufferedReader(filereader);
	            String line = "";

	            while((line = bufReader.readLine()) != null){
	                msgData.add(line);
	            }
	            //.readLine()은 끝에 개행문자를 읽지 않는다.            
	            bufReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// msgData 담기
		mav.addObject("msgData", msgData);
	}// readChatMessage

	// 유저가 채팅방 참여인원인지 파악
	public ChatRoomDto addUserToChatRoom(HttpServletRequest request, int chatRoomNo) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int check = chatDao.findUserToChatRoom(id, chatRoomNo);
		
		// 유저가 기존 채팅방 참여자가 아닌경우
		if(check == 0) {
			// 채팅방 참여자에 유저 정보 추가
			chatDao.addUserToChatRoom(id, chatRoomNo);
		}
		// 해당채팅방 정보 얻어오기
		return chatDao.getChatRoom(id, chatRoomNo);
	}

	// 채팅방 나가기
	public void exitChatRoom(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int chatRoomNo = Integer.parseInt((String) request.getParameter("chatRoomNo"));
		
		System.out.println(id + chatRoomNo);
		// 채팅방을 나가는 유저가 채팅방 개설자라면 -> 채팅방 삭제
		int check = chatDao.isChatRoomHost(id, chatRoomNo);
		if(check > 0) {
			// 나가는 유저가 채팅방 개설자
			
		} else {
			// 나가는 유저가 일반 참여자
			chatDao.exitChatRoom(id, chatRoomNo);
		}
		
		mav.setViewName("redirect:chatRoomList.do");
	}
}

