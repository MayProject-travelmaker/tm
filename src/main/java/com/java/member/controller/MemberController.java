package com.java.member.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.calendar.service.CalendarService;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

@Controller
public class MemberController {
	
	
	//mypage 달력 테스트===========
//	@Autowired
//	private CalendarService service;
	//==========================
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 아이디 찾기
	@RequestMapping(value="/member/findId.do", method= RequestMethod.GET)
	public ModelAndView memberFindId(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/findId");	
	}
	// 아이디 찾기 완료
	@RequestMapping(value="/member/findIdOk.do", method= RequestMethod.POST)
	public ModelAndView memberFindIdOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.memberFindId(mav);
		
		return mav;	
	}
	// 비밀번호 찾기
	@RequestMapping(value="/member/findPassword.do", method= RequestMethod.GET)
	public ModelAndView memberFindPassword(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/findPassword");
		
	}
	// 비밀번호 찾기 - 아이디, 이메일 존재하는 회원 확인 할 때
	@ResponseBody
	@RequestMapping(value="/member/checkIdAndEmail.do", method=RequestMethod.POST)
	public String checkIdAndEmail(@RequestParam("id")String id, @RequestParam("email")String email) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.addObject("email",email);
		
		String check = Integer.toString(memberService.checkIdAndEmail(mav));
		return check;
	}
	// 비밀번호 찾기 - 이메일 인증 전송
	@RequestMapping(value="/member/findPasswordSendEmail.do", method=RequestMethod.POST)
	public ModelAndView findPasswordSendEmail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.sendEmailToFindPwd(mav);
		return mav;
	}
	@RequestMapping(value="/member/changePassword.do", method=RequestMethod.GET)
	public ModelAndView memberChangePassword(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/changePassword");
	}
	@RequestMapping(value="/member/changePwd.do", method=RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		memberService.changePassword(mav);
		return mav;
	}
	// 회원가입
	@RequestMapping(value="/member/register.do", method= RequestMethod.GET)
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
		
	}
	

	// 회원 가입 완료
	@RequestMapping(value="/member/registerOk.do", method=RequestMethod.POST) 
	public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response , MemberDto memberDto) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto",memberDto);
		
		memberService.memberRegisterOk(mav);
		return mav;
	}
	
	// 아이디 중복 확인
	@ResponseBody
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST) 
	public String memberIdCheckOk(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.addObject("id",id);
		
		memberService.memberidCheck(mav);
		Map<String,Object> map = mav.getModelMap();
		String check = Integer.toString((int)map.get("check"));
		return check;
	}
	
	// 이메일 인증 발송
	@ResponseBody
	@RequestMapping(value="/member/sendEmail.do", method=RequestMethod.POST)
	public String sendEmail(@RequestParam("email")String email, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("email", email);
		
		memberService.sendEmail(mav);
		
		Map<String, Object> map = mav.getModelMap();
		String randomNumber = Integer.toString((int)map.get("randomNumber"));
		return randomNumber;
	}
	
	// 회원정보
	@RequestMapping(value="/member/memberProfile.do", method=RequestMethod.GET)
	public ModelAndView memberProfile(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("member/memberProfile");
	}
	// 회원정보 확인
	@RequestMapping(value="/member/memberProfileOk.do", method=RequestMethod.POST)
	public ModelAndView memberProfileOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.memberProfileOk(mav);
		return mav;
	}
	
	// 회원정보 수정화면
	@RequestMapping(value="/member/memberupdate.do", method=RequestMethod.GET)
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView mav = new ModelAndView(); mav.addObject("request", request);
		 * memberService.memberUpdate(mav);
		 * 
		 * return mav;
		 */
		//업데이트 화면 확인
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		memberService.memberUpdate(mav);

		return mav;

		// 업데이트 화면 확인
	}
	// 회원정보 수정 완료
	@RequestMapping(value="/member/updateOk.do", method=RequestMethod.POST)
	public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("response", response);
		mav.addObject("memberDto",memberDto);
		memberService.memberUpdateOk(mav);
		return mav;
	}
	
	// 로그인
	@RequestMapping(value="/member/login.do")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/login");
		
	}
	
	// 로그인 완료
	@RequestMapping(value="/member/loginOk.do", method=RequestMethod.POST)
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
	// 로그아웃
	@RequestMapping(value="/member/logout.do")
	public ModelAndView memberLogOut(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/logout");
	}
	// 회원 탈퇴
	@RequestMapping(value="/member/delete.do")
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/delete");
	}
	
	// 회원 탈퇴 완료
	@RequestMapping(value="/member/deleteOk.do")
	public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("response",response);
		if(memberService.memberDeleteOk(mav)>0) {
			// 회원탈퇴가 성공 할 경우
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("redirect:/");
		} else {
			// 회원탈퇴가 실패 할 경우
			return mav;
		}
	}
	
	//-----------------------------------------------------------------추가--------------------------------------
//	마이페이지
//	@RequestMapping(value="/member/mypage.do")
//		public String schedule(Model model) throws Exception {
//		model.addAttribute("showSchedule", service.showSchedule());
//		return "/member/mypage";
//	}
	
	
	
	
	//회원관리
	@RequestMapping(value="/member/management.do")
	public ModelAndView memberManagement(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		
		return new ModelAndView("member/management");
		
	}

//	//회원정보수정
//	@RequestMapping(value="/member/memberupdate.do")
//	public ModelAndView memberMemberUpdate(HttpServletRequest request, HttpServletResponse response) {
//		return new ModelAndView("member/memberupdate");
//		
//	}
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
	
	@ResponseBody
	@RequestMapping(value = "/member/VerifyRecaptcha", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
		VerifyRecaptcha.setSecretKey("6LfJuj4bAAAAACMHnm0ixYJfErlhUNJM1qAg61zX");
		String gRecaptchaResponse = request.getParameter("recaptcha");
//		System.out.println(gRecaptchaResponse);
		// 0 = 성공, 1 = 실패, -1 = 오류
		try {
			if (VerifyRecaptcha.verify(gRecaptchaResponse))
				return 0;
			else 
				return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
	
	
