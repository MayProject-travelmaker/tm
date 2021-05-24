package com.java.board.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.ReplyDto;

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
	
	//즐겨찾기
	int bookmark(ModelAndView mav);
	//즐겨찾기 중복체크
	int bmCheck(String id, int boardNo);
	
	//댓글입력
	int replyWrite(ReplyDto replyDto);
	//댓글리스트
	List<ReplyDto> replyList(int boardNo);
	//댓글삭제
	int replyDel(int replyNo);
	//댓글수정
	int replyUpd(ReplyDto replyDto);

	
}