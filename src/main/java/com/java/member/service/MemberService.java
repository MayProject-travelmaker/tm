package com.java.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
	
	// 아이디 중복확인
	public void memberidCheck(ModelAndView mav);
	
	// 회원가입 이메일인증 메일 전송
	public void sendEmail(ModelAndView mav);

	// 회원가입
	public void memberRegisterOk(ModelAndView mav);

	// 로그인
	public void memberloginOk(ModelAndView mav);
	
	// 회원정보 확인
	public void memberProfileOk(ModelAndView mav);
		
	// 회원정보 수정
	public void memberUpdate(ModelAndView mav);
	
	// 회원정보 수정 완료
	public void memberUpdateOk(ModelAndView mav);

	// 회원탈퇴
	public int memberDeleteOk(ModelAndView mav);
	
	// 아이디 찾기
	public void memberFindId(ModelAndView mav);

	// 비밀번호 찾기 - 아이디, 이메일 존재 확인
	public int checkIdAndEmail(ModelAndView mav);

	// 비밀번호 찾기 - 인증 이메일 전송하기
	public void sendEmailToFindPwd(ModelAndView mav);

	// 비밀번호 찾기 - 비밀번호 변경하기
	public void changePassword(ModelAndView mav);

}
