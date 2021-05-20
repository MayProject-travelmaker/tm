package com.java.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	@RequestMapping(value="/member/register.do", method= RequestMethod.GET)
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
		
	}
	
	@RequestMapping(value="/member/findId.do", method= RequestMethod.GET)
	public ModelAndView memberFindId(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/findId");
		
	}
	
	@RequestMapping(value="/member/findPassword.do", method= RequestMethod.GET)
	public ModelAndView memberFindPassword(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/findPassword");
		
	}
	
	@RequestMapping(value="/member/registerOk.do", method=RequestMethod.POST) 
	public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response , MemberDto memberDto) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto",memberDto);
		
		memberService.memberRegisterOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.GET) 
	public ModelAndView memberIdCheckOk(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("request",request);
		
		memberService.memberidCheck(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView mav = new ModelAndView(); mav.addObject("request", request);
		 * memberService.memberUpdate(mav);
		 * 
		 * return mav;
		 */
		//업데이트 화면 확인
		return new ModelAndView("member/update");
	}
	
	@RequestMapping(value="/member/updateOk.do", method=RequestMethod.POST)
	public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MemberDto",memberDto);
		memberService.memberUpdateOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/login.do")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/login");
		
	}
	
	@RequestMapping(value="/member/loginOk.do")
	public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.memberloginOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/main.do")
	public ModelAndView memberMain(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/main");
		
	}
	
	@RequestMapping(value="/member/logout.do")
	public ModelAndView memberLogOut(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/logout");
	}
	
	@RequestMapping(value="/member/delete.do")
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/delete");
	}
	
	@RequestMapping(value="/member/deleteOk.do")
	public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.memberDeleteOk(mav);
		
		return null;
	}
	
	//-----------------------------------------------------------------추가--------------------------------------
	//마이페이지
	@RequestMapping(value="/member/mypage.do")
	public ModelAndView memberMypage(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/mypage");
		
	}
	
	//회원관리
	@RequestMapping(value="/member/management.do")
	public ModelAndView memberManagement(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		
		return new ModelAndView("member/management");
		
	}
	
	//회원정보수정
	@RequestMapping(value="/member/memberupdate.do")
	public ModelAndView memberMemberUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		
		return new ModelAndView("member/memberupdate");
		
	}
	//회원탈퇴
	@RequestMapping(value="/member/memberdelete.do")
	public ModelAndView memberMemerDelete(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/memberdelete");
		
	}
	

	//회원 조회
	@ResponseBody
	@RequestMapping(value="/member/memberlist")
	public ArrayList<MemberDto> memberList(Model model) {
		
		ArrayList<MemberDto> list = (ArrayList<MemberDto>) memberDao.memberList();

		System.out.println(list);
		return list;
	}
	
	//회원 차단하기 
	@ResponseBody
	@RequestMapping(value="/member/addblack")
	public String record(HttpServletRequest request) {
		String id = request.getParameter("id");
		memberService.addBlack(id);
		return null;
	}
	
	//차단리스트 조회
	@ResponseBody
	@RequestMapping(value="/member/blacklist")
	public ArrayList<MemberDto> blackList(Model model) {
		
		ArrayList<MemberDto> blacklist = (ArrayList<MemberDto>) memberDao.blackList();
		return blacklist;
	}
	
	//차단리스트 조회
//	@ResponseBody
//	@RequestMapping(value="/member/reportlist")
//	public ArrayList<BoardDto> reportList(Model model) {
//		
//		ArrayList<MemberDto> reportlist = (ArrayList<BoardDto>) boardDao.reportList();
//		return report;
//	}
}
	