package com.java.mypage.service;

import org.springframework.web.servlet.ModelAndView;

public interface MypageService {
	
	// 내가 쓴 글
	public void myBoardList(ModelAndView mav);
	
	// 내가 쓴 댓글
	public void myReplyList(ModelAndView mav);
	
	// 내가 좋아요 누른 글
	public void myLikeList(ModelAndView mav);
	
	// 즐겨찾기 추가한 글
	public void myBookmarkList(ModelAndView mav);
}
