package com.java.board.service;

import java.util.HashMap;

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

	
	// 추가한 항목
	void boardUpdateOk(ModelAndView mav);

	// 좋아요
	HashMap<String, Object> boardLikeOk(ModelAndView mav);
	
}