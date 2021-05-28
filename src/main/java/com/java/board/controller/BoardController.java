package com.java.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.java.board.dto.BoardDto;
import com.java.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//����Խ��� �Ѿ��
	@RequestMapping(value="/board/accompanylist.do", method= RequestMethod.GET)
	public ModelAndView boardAccompanyList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		boardService.accompanyboardList(mav);
		return mav;
			
	}
		
	//�����ı�Խ��� �Ѿ��
	@RequestMapping(value="/board/accompanyreview.do", method= RequestMethod.GET)
	public ModelAndView boardAccompanyReview(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		boardService.accompanyreviewList(mav);
		return mav;
			
	}
	
	//��õ��ΰԽ������� �Ѿ��
	@RequestMapping(value="/board/recommendpath.do", method= RequestMethod.GET)
	public ModelAndView boardRecommendPath(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		boardService.recommendpathList(mav);
		return mav;
			
	}
	
	//�������ı�Խ������� �Ѿ��
	@RequestMapping(value="/board/travelreview.do", method= RequestMethod.GET)
	public ModelAndView boardTravelReview(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		boardService.travelreviewList(mav);
		return mav;	
	}
	
	//�۾���
	@RequestMapping(value="/board/write.do")
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("board/write");
	}
	
	
	//�۾���Ϸ�
	@RequestMapping(value="/board/writeOk.do", method= RequestMethod.POST)
	public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("boardDto", boardDto);
		
		boardService.boardWriteOk(mav);
		return mav;
	}

	//���б�
	@RequestMapping(value="/board/read.do")
	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardRead(mav);
		return mav;
	}
	//�ۼ���
	@RequestMapping(value="/board/update.do")
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardUpdate(mav);
		return mav;
	}
	//�ۼ����Ϸ�
	@RequestMapping(value="/board/updateOk.do", method= RequestMethod.POST)
	public ModelAndView boardUpdateOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("boardDto", boardDto);
		
		boardService.boardUpdateOk(mav);
		
		return mav;	
	}
	//�ۻ����Ϸ�
	@RequestMapping(value="/board/deleteOk.do")
	public ModelAndView boardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("board/deleteOk");	
	}
	
	
	// 좋아요 버튼
	@ResponseBody
	@RequestMapping(value="/board/likeOk.do", method=RequestMethod.GET)
	public HashMap<String, Object> boardLikeOk(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		mav.addObject("boardNo", (String) param.get("boardNo"));		// 게시글 번호
		mav.addObject("boardCode", (String) param.get("boardCode"));	// 게시판 코드
		mav.addObject("postId", (String) param.get("postId"));			// 작성자 아이디
		
		
		return boardService.boardLikeOk(mav);
	}
}