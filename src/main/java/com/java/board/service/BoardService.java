package com.java.board.service;

import org.springframework.web.servlet.ModelAndView;

public interface BoardService {

	//�۾���
	void boardWriteOk(ModelAndView mav);

	//�Խ��� ����Ʈ(����, �����ı�, ��õ������, �������ı�
	void accompanyboardList(ModelAndView mav);
	void accompanyreviewList(ModelAndView mav);
	void recommendpathList(ModelAndView mav);
	void travelreviewList(ModelAndView mav);

	//�б�
	void boardRead(ModelAndView mav);

	//����
	void boardUpdate(ModelAndView mav);
	
	
	//내 여행일지 업로드
	void diaryUpload (ModelAndView mav);
	//내 여행일지 업로드Ok
	void diaryUploadOk(ModelAndView mav);
}