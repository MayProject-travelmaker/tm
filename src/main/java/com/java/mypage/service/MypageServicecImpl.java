package com.java.mypage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.BoardDto;
import com.java.board.dto.ReplyDto;
import com.java.mypage.dao.MypageDao;

@Component
public class MypageServicecImpl implements MypageService{

	@Autowired
	private MypageDao mypageDao;
	
	int currentPage;	// 현재 페이지 번호
	int boardSize = 5;		// 게시글 개수
	int startRow;		// 게시글 시작 rowNum
	int endRow;			// 게시글 끝 rowNum
	// 내가 쓴 글
	@Override
	public void myBoardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");		// 세션 아이디
		String pageNumber = request.getParameter("pageNumber");
		
		// 페이징 처리를 위한 처리
		if(pageNumber == null) {
			// 첫 번째 페이지
			pageNumber = "1";
		}
		
		currentPage = Integer.parseInt(pageNumber);	// 현재 페이지 번호
		startRow = (currentPage - 1) * boardSize + 1;
		endRow = currentPage * boardSize;
		
		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
				
		// 내가 쓴 글 목록 불러오기
		List<BoardDto> myBoardList = null;
		int count = mypageDao.myBoardListCount(id, searchType, keyword);	// 내가 쓴 글 전체 개수
		myBoardList = mypageDao.myBoardList(id, startRow, endRow, searchType, keyword);	// 페이징 처리가 적용된 내가 쓴 글 목록

		if(myBoardList != null) {
			// 내가 쓴 글이 존재 할 경우
			mav.addObject("myBoardList", myBoardList);
		} 
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.setViewName("mypage/myBoard");
	}

	// 내가 쓴 댓글
	@Override
	public void myReplyList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String pageNumber = request.getParameter("pageNumber");
		
		// 페이징 처리를 위한 처리
		if(pageNumber == null) {
			// 첫 번째 페이지
			pageNumber = "1";
		}
		
		currentPage = Integer.parseInt(pageNumber);	// 현재 페이지 번호
		startRow = (currentPage - 1) * boardSize + 1;
		endRow = currentPage * boardSize;
		
		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
		
		// 내가 쓴 댓글 목록 불러오기
		List<ReplyDto> myReplyList = null;
		int count = mypageDao.myReplyListCount(id, searchType, keyword);	// 내가 쓴 댓글 전체 개수
		myReplyList = mypageDao.myReplyList(id, startRow, endRow, searchType, keyword);	// 페이징 처리가 적용된 내가 쓴 댓글 목록

		if(myReplyList != null) {
			// 내가 쓴 댓글이 존재 할 경우
			mav.addObject("myReplyList", myReplyList);
		} 
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.setViewName("mypage/myReply");
		
	}

	// 내가 좋아요 누른 글
	@Override
	public void myLikeList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String pageNumber = request.getParameter("pageNumber");
		
		// 페이징 처리를 위한 처리
		if(pageNumber == null) {
			// 첫 번째 페이지
			pageNumber = "1";
		}
		
		currentPage = Integer.parseInt(pageNumber);	// 현재 페이지 번호
		startRow = (currentPage - 1) * boardSize + 1;
		endRow = currentPage * boardSize;
		
		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
		
		// 내가 좋아요 누른 글 목록 불러오기
		List<BoardDto> myLikeList = null;
		int count = mypageDao.myLikeListCount(id, searchType, keyword);	// 내가 좋아요 누른 글 전체 개수
		myLikeList = mypageDao.myLikeList(id, startRow, endRow, searchType, keyword);	// 페이징 처리가 적용된 내가 좋아요 누른 글 목록

		if(myLikeList != null) {
			// 내가 좋아요 누른 글이 존재 할 경우
			mav.addObject("myLikeList", myLikeList);
		} 
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.setViewName("mypage/myLike");
	}

	// 내가 즐겨찾기 한 글
	@Override
	public void myBookmarkList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String pageNumber = request.getParameter("pageNumber");
		
		// 페이징 처리를 위한 처리
		if(pageNumber == null) {
			// 첫 번째 페이지
			pageNumber = "1";
		}
		
		currentPage = Integer.parseInt(pageNumber);	// 현재 페이지 번호
		startRow = (currentPage - 1) * boardSize + 1;
		endRow = currentPage * boardSize;
		
		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
		
		// 내가 좋아요 누른 글 목록 불러오기
		List<BoardDto> myBookmarkList = null;
		int count = mypageDao.myBookmarkListCount(id, searchType, keyword);	// 내가 즐겨찾기 한 글 전체 개수
		myBookmarkList = mypageDao.myBookmarkList(id, startRow, endRow, searchType, keyword);	// 페이징 처리가 적용된 내가 즐겨찾기 한 글 목록

		if(myBookmarkList != null) {
			// 내가 즐겨찾기 한 글이 존재 할 경우
			mav.addObject("myBookmarkList", myBookmarkList);
		} 
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.setViewName("mypage/myBookmark");
		
	}

}
