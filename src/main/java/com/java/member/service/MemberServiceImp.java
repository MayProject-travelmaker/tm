package com.java.member.service;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.sun.mail.iap.Response;

@Component
public class MemberServiceImp implements MemberService{
	@Autowired
	private MemberDao memberDao;
	@Inject
	JavaMailSender mailSender;
	
	// 회원가입
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
	
	// 아이디 중복확인
	@Override
	public void memberidCheck(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		String id = (String)map.get("id");
		System.out.println("MemberSserviceImpl id: "+id);
		int check = memberDao.memberidCheck(id);
		
		mav.addObject("check",check);
	}

	// 회원가입 이메일인증 메일 전송
	@Override
	public void sendEmail(ModelAndView mav) {
		Random random = new Random();
		int randomNumber = random.nextInt(4589362) + 49311;
		Map<String,Object> map = mav.getModelMap();
		String email = (String)map.get("email");
		String from = "projeun2@gmail.com";
		String to = email;
		String subject = "[TRAVLE MAKER] 회원가입 인증 이메일 입니다.";
		String content = "<h4>[회원가입 인증]</h4><br>"+
				"인증번호는 <strong>" + randomNumber + "</strong> 입니다.<br><br>"+
				"인증번호를 홈페이지에 입력해 주세요.";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(from); // 보내는 사람 이메일
			messageHelper.setTo(to); // 받는 사람 이메일
			messageHelper.setSubject(subject); // 메일 제목
			messageHelper.setText(content, true); // 메일 내용(true: html태그 인식)
			
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mav.addObject("randomNumber", randomNumber);
		
	}

	// 로그인
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
			
			if(String.valueOf(result.get("MEMBER_LEVEL")).equals("4")) {
				mav.addObject("message", "탈퇴한 회원입니다");
				mav.setViewName("member/login");
			} else if(String.valueOf(result.get("MEMBER_LEVEL")).equals("3")) {
				mav.addObject("message", "차단된 회원입니다");
				mav.setViewName("member/login");
			}
			else {
				mav.setViewName("redirect:/");
			}
		} else {
			// 로그인 실패
			// 아이디가 없거나 비밀번호가 match되지 않을경우 로그인 실패
			mav.addObject("message","로그인이 실패했습니다.");
			mav.setViewName("member/login");
		}
		
	}

	// 회원정보 확인
	@Override
	public void memberProfileOk(ModelAndView mav) {
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String password = request.getParameter("password");
		
		Map<String, Object> result = memberDao.loginOk(id, password);
		if(result != null && result.size() > 0 && cryptPassword.matches(password, (String) result.get("PASSWORD"))) {
			// 회원정보 일치
			mav.setViewName("redirect:memberupdate.do"); // 회원정보 출력 화면으로 이동
		} else {
			// 회원정보 불일치
			mav.addObject("message","비밀번호가 일치하지 않습니다.");
			mav.setViewName("member/memberProfile");
		}
		
	}
	
	// 회원정보 수정 화면
	@Override
	public void memberUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDto memberDto = memberDao.memberUpdate(id);
		
		mav.addObject("member",memberDto);
		mav.setViewName("member/memberupdate");
		
	}
	
	// 회원정보 수정 완료
	@Override
	public void memberUpdateOk(ModelAndView mav) {
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		String id = (String) session.getAttribute("id"); 					// 아이디
		String password = cryptPassword.encode(memberDto.getPassword());	// 비밀번호
		
		memberDto.setId(id);
		memberDto.setPassword(password);

		int check =  memberDao.memberUpdateOk(memberDto);
		mav.addObject("check",check);
		if(check > 0) {
			mav.addObject("message","회원정보 수정이 완료되었습니다.");
		} else {
			mav.addObject("message","회원정보 수정이 실패했습니다.");
		}
		mav.addObject("member",memberDto);
		mav.setViewName("member/memberupdate");
	}
	
	// 회원탈퇴
	@Override
	public int memberDeleteOk(ModelAndView mav) {
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String password = request.getParameter("password");
		
		HashMap<String,Object> result = memberDao.loginOk(id, password);
		int check = 0;
		if(result != null && result.size() > 0 && cryptPassword.matches(password, (String) result.get("PASSWORD"))) {
			// 회원정보 일치
			System.out.println("성공");
			check = memberDao.memberDelete(id, password); // 회원등급(탈퇴회원) 변경
			if(check > 0) {
				mav.addObject("message", "회원탈퇴가 완료되었습니다.");
			} else {
				System.out.println("실패");
				mav.addObject("message", "회원탈퇴가 실패했습니다.");
				mav.setViewName("member/memberdelete");
			}
		} else {
			// 회원정보 불일치
			System.out.println("실패2");
			mav.addObject("message","비밀번호가 일치하지 않습니다.");
			mav.setViewName("member/memberdelete");
		}
		return check;
	}
	// 아이디 찾기
	@Override
	public void memberFindId(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		String id = memberDao.memberFindId(name, phone);
		
		if(id == null) {
			// 아이디 찾기 실패
			mav.addObject("message","일치하는 회원정보가 없습니다.");
			mav.setViewName("/member/findId");
		} else {
			// 아이디 찾기 성공
			mav.addObject("id", id);
			mav.setViewName("/member/findIdOk");
		}
	}
	// 비밀번호 찾기 - 아이디, 이메일 존재 확인
	@Override
	public int checkIdAndEmail(ModelAndView mav) {
		Map<String,Object> map = mav.getModel();
		String id = (String) map.get("id");
		String email = (String) map.get("email");
		
		
		int check = memberDao.checkIdAndEmail(id, email);
		return check;
	}
	// 비밀번호 찾기 - 인증 이메일 전송하기
	@Override
	public void sendEmailToFindPwd(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");
		String from = "projeun2@gmail.com";
		String to = email+domain;
		//System.out.println("MAIL: "+to);
		
		// 임의의 인증키 생성 & DB에 인증키 업데이트하기
		Random random = new Random();
		int authKey = random.nextInt(4589362) + 49311;
		
		int check = memberDao.updateAuthKey(id,email,Integer.toString(authKey));
		
		// 인증이메일 전송하기
		String subject = "[TRAVLE MAKER] 비밀번호 찾기 인증 이메일 입니다.";
		String content = "<h4>[비밀번호 찾기 이메일 인증]</h4><br>"+
				"<a href='http://localhost/project/member/changePassword.do?id="+id+
				"&authKey="+authKey+
				"' target='_blank'>이메일 인증 확인</a>";
		//System.out.println("MemberServiceImple email: "+email);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(from); // 보내는 사람 이메일
			messageHelper.setTo(to); // 받는 사람 이메일
			messageHelper.setSubject(subject); // 메일 제목
			messageHelper.setText(content,true); // 메일 내용(true: html태그 인식)
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mav.setViewName("/member/login");
	}
	// 비밀번호 찾기 - 비밀번호 변경하기
	@Override
	public void changePassword(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder(); // 비밀번호 암호화
		String id = request.getParameter("id");
		String password = cryptPassword.encode(request.getParameter("password"));
		String authKey = request.getParameter("authKey");
		
		int check = memberDao.changePassword(id,password,authKey);
		mav.addObject("check",check);
		if(check == 1) {
			// 비밀번호 변경 성공
			memberDao.updateAuthKey(id, password, "Y"); // 비밀번호 인증키 변경
			mav.setViewName("member/changePasswordOk");
		} else {
			// 비밀번호 변경 실패
			mav.setViewName("member/changePasswordOk");
		}
	}
	
	
	//회원차단
	@Override
	public void addBlack(String id) {
		
		System.out.println(id);
		int check = memberDao.addBlack(id);
		
		System.out.println("블랙리스트 성공:1, 실패:0"+" "+check);
		
		
	}

	
}
