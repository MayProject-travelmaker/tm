package com.java.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.mypage.service.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 내가 쓴 게시글
	@RequestMapping(value="/mypage/myBoard.do", method=RequestMethod.GET)
	public ModelAndView myBoardList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String searchType, @RequestParam(required = false) String keyword) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		mypageService.myBoardList(mav);

		return mav;
	}
	
	// 내가 쓴 댓글
	@RequestMapping(value="/mypage/myReply.do", method=RequestMethod.GET)
	public ModelAndView myReplyList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String searchType, @RequestParam(required = false) String keyword ) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		mypageService.myReplyList(mav);
		
		return mav;
	}
	
	// 내가 좋아요 누른 글
	@RequestMapping(value="/mypage/myLike.do", method=RequestMethod.GET)
	public ModelAndView myLikeList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String searchType, @RequestParam(required = false) String keyword) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		mypageService.myLikeList(mav);
		
		return mav;
	}
	
	// 즐겨찾기
	@RequestMapping(value="/mypage/myBookmark.do", method=RequestMethod.GET)
	public ModelAndView myBookmarkList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String searchType, @RequestParam(required = false) String keyword) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		mypageService.myBookmarkList(mav);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
}
