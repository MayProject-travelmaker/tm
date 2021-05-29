package com.java.member.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.java.member.dto.MemberDto;

public interface MemberService {
	
	// ���̵� �ߺ�Ȯ��
	public void memberidCheck(ModelAndView mav);
	
	// ȸ������ �̸������� ���� ����
	public void sendEmail(ModelAndView mav);

	// ȸ������
	public void memberRegisterOk(ModelAndView mav);

	// �α���
	public void memberloginOk(ModelAndView mav);
	
	// ȸ������ Ȯ��
	public void memberProfileOk(ModelAndView mav);
		
	// ȸ������ ����
	public void memberUpdate(ModelAndView mav);
	
	// ȸ������ ���� �Ϸ�
	public void memberUpdateOk(ModelAndView mav);

	//회원차단
	public void addBlack(String id);
	

	// ȸ��Ż��
	public int memberDeleteOk(ModelAndView mav);
	
	// ���̵� ã��
	public void memberFindId(ModelAndView mav);

	// ��й�ȣ ã�� - ���̵�, �̸��� ���� Ȯ��
	public int checkIdAndEmail(ModelAndView mav);

	// ��й�ȣ ã�� - ���� �̸��� �����ϱ�
	public void sendEmailToFindPwd(ModelAndView mav);

	// ��й�ȣ ã�� - ��й�ȣ �����ϱ�
	public void changePassword(ModelAndView mav);

}
