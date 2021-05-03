package com.java.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
public void memberRegisterOk(ModelAndView mav);
	
	public void memberidCheck(ModelAndView mav);

	public void memberlogin(ModelAndView mav);

	public void memberloginOk(ModelAndView mav);

	public void main(ModelAndView mav);

	public void memberloginOut(ModelAndView mav);

	public void memberUpdateOk(ModelAndView mav);

	public void memberUpdate(ModelAndView mav);
	
	public void memberDelete(ModelAndView mav);
	
	public void memberDeleteOk(ModelAndView mav);
}
