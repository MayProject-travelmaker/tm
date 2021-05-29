package com.java.calendar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.calendar.dto.CalendarDto;
import com.java.calendar.service.CalendarService;
import com.java.member.dto.MemberDto;

@Controller
public class CalendarController {
	@Autowired
	private CalendarService service;
	
	
	//캘린더에 전체 일정 불러오기 
	@RequestMapping(value="/member/mypage.do")
	public String schedule(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		model.addAttribute("showSchedule", service.showSchedule(id));
		return "/member/mypage";
	}
	
	//일정 추가
	@ResponseBody
	@RequestMapping(value="/member/addSchedule", method=RequestMethod.POST)
	public Map<Object,Object> addSchedule(@RequestBody CalendarDto calendarDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<Object,Object> map = new HashMap<Object,Object>();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		service.addSchedule(calendarDto,id);
		
		return map;
	}
	
	
	//일정 등록 팝업창
	@RequestMapping(value="/member/schedulePopup")
	public ModelAndView schedulePopup(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/schedulePopup");
			
	}
	
	//일정 불러오기(개별)
	@RequestMapping(value="/member/readSchedule")
	public ModelAndView readSchedule(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(request.getParameter("num"));
		
		mav.addObject("request",request);
		
		service.readSchedule(mav);
		
		
		return mav;
	}
	
	//일정 수정
	@ResponseBody
	@RequestMapping(value="/member/updateSchedule",  method=RequestMethod.POST)
	public Map<Object,Object> updateSchedule(@RequestBody CalendarDto calendarDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<Object,Object> map = new HashMap<Object,Object>();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		service.updateSchedule(calendarDto,id);
		
		return map;
	}
	
	//일정 삭제
	@ResponseBody
	@RequestMapping(value="/member/deleteSchedule",  method=RequestMethod.POST)
	public Map<Object,Object> deleteSchedule(@RequestBody CalendarDto calendarDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<Object,Object> map = new HashMap<Object,Object>();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		service.deleteSchedule(calendarDto, id);
		
		return map;
	}
	
	

}
