package com.java.member.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;

@Component
public class MemberServiceImp implements MemberService{
	@Autowired
	private MemberDao memberDao;
	@Inject
	JavaMailSender mailSender;
	
	@Override
	public void memberRegisterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		String password = cryptPassword.encode(memberDto.getPassword());	// 비밀번호 암호화
//		System.out.println("암호화된 비밀번호 : "+password);
		
		memberDto.setPassword(password);
		memberDto.setMemberLevel(2);		// 회원등급, 1:관리자, 2:일반유저, 3:차단된유저 , 4:탈퇴한유저
		memberDto.setReportCount(0);
		int check = memberDao.memberinsert(memberDto);
		
		mav.addObject("check",check);
		
		mav.setViewName("member/registerOk");
		
	}	
	// 회원가입 아이디 중복확인 서비스
	@Override
	public void memberidCheck(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		String id = (String)map.get("id");
		System.out.println("MemberSserviceImpl id: "+id);
		int check = memberDao.memberidCheck(id);
		
		mav.addObject("check",check);
		
		
	}

	// 이메일인증 메일 전송 서비스
	@Override
	public void sendEmail(ModelAndView mav) {
		Random random = new Random();
		int randomNumber = random.nextInt(4589362) + 49311;
		Map<String,Object> map = mav.getModelMap();
		String email = (String)map.get("email");
		String from = "projeun2@gmail.com";
		String to = email;
		String subject = "[TRAVLE MAKER] 회원가입 인증 이메일 입니다.";
		String content = 
				"인증번호는 " + randomNumber + "입니다."+
				System.getProperty("line.seperator")+
				"인증번호를 홈페이지에 입력해 주세요.";
		System.out.println("MemberServiceImple email: "+email);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(from); // 보내는 사람 이메일
			messageHelper.setTo(to); // 받는 사람 이메일
			messageHelper.setSubject(subject); // 메일 제목
			messageHelper.setText(content); // 메일 내용
			
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mav.addObject("randomNumber", randomNumber);
		
	}
	@Override
	public void memberlogin(ModelAndView mav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberloginOk(ModelAndView mav) {
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password"); // 암호화시킨 비밀번호
		HashMap<String,Object> result = memberDao.loginOk(id, password);
		if(result != null && result.size() > 0 && cryptPassword.matches(password, (String) result.get("PASSWORD"))) {
			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("id", id); // 세션에 id 저장하기
			session.setAttribute("memberLevel", result.get("MEMBER_LEVEL")); // 세션에 memberLevel 저장하기
//			mav.addObject("memberLevel", result.get("MEMBER_LEVEL")); 	// param
//			mav.addObject("id",id);	// param
			
			mav.setViewName("redirect:/");
		} else {
			// 로그인 실패
			// 아이디가 없거나 비밀번호가 match되지 않을경우 로그인 실패
			mav.addObject("message","로그인이 실패했습니다.");
			mav.setViewName("member/login");
		}
		
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

	
}
