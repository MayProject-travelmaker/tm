package com.java.board.service;

import org.springframework.web.servlet.ModelAndView;

public interface BoardService {

	//글쓰기
	void boardWriteOk(ModelAndView mav);

	//게시판 리스트(동행, 동행후기, 추천여행경로, 여행지후기
	void accompanyboardList(ModelAndView mav);
	void accompanyreviewList(ModelAndView mav);
	void recommendpathList(ModelAndView mav);
	void travelreviewList(ModelAndView mav);

	//읽기
	void boardRead(ModelAndView mav);

	//수정
	void boardUpdate(ModelAndView mav);
	
}