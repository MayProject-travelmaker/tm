package com.java.member.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;

@Component
public class MemberServiceImp implements MemberService{
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void memberRegisterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		
		memberDto.setMemberLevel(1);		//멤버레벨 숫자로 알맞게 지정해주어야 함, 1:관리자, 2:일반사용자, 3:차단된 유저 , 4:탈퇴한 유저
		
		int check = memberDao.memberinsert(memberDto);
		
		mav.addObject("check",check);
		mav.setViewName("member/registerOk");
		
	}	

	@Override
	public void memberidCheck(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		
		int check = memberDao.memberidCheck(id);
		
		mav.addObject("check",check);
		mav.setViewName("member/idCheck");
		
		
	}

	@Override
	public void memberlogin(ModelAndView mav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberloginOk(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		String memberLevel = memberDao.loginOk(id, password);
		
		mav.addObject("memberLevel",memberLevel);
		mav.addObject("id",id);
		mav.setViewName("member/loginOk");
	}

	@Override
	public void main(ModelAndView mav) {
		
		
		
	}

	@Override
	public void memberloginOut(ModelAndView mav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberUpdateOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		
		int check =  memberDao.memberUpdateOk(memberDto);
		mav.addObject("check",check);
		mav.setViewName("member/updateOk");
		
	}

	@Override
	public void memberUpdate(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDto memberDto = memberDao.memberUpdate(id);
		
		mav.addObject("memberDto",memberDto);
		mav.setViewName("member/update");
		
	}
	
	@Override
	public void memberDelete(ModelAndView mav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDeleteOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		
	}
	
	
	//회원차단
	@Override
	public void addBlack(String id) {
		
		System.out.println(id);
		int check = memberDao.addBlack(id);
		
		System.out.println("블랙리스트 성공:1, 실패:0"+" "+check);
		
		
	}

	
}
