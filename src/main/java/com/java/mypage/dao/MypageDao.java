package com.java.mypage.dao;

import java.util.List;

import com.java.board.dto.BoardDto;
import com.java.board.dto.ReplyDto;

public interface MypageDao {

	// 내가 쓴 글
	public List<BoardDto> myBoardList(String id, int StartRow, int endRow, String searchType, String keyword);

	// 내가 쓴 댓글
	public List<ReplyDto> myReplyList(String id, int StartRow, int endRow, String searchType, String keyword);
	
	// 내가 좋아요 누른 글
	public List<BoardDto> myLikeList(String id, int StartRow, int endRow, String searchType, String keyword);
	
	// 내가 즐겨찾기 한 글
	public List<BoardDto> myBookmarkList(String id, int StartRow, int endRow, String searchType, String keyword);

	// 내가 쓴 글 개수
	public int myBoardListCount(String id, String searchType, String keyword);

	// 내가 쓴 댓글 개수
	public int myReplyListCount(String id, String searchType, String keyword);
	
	// 내가 좋아요 누른 글 개수
	public int myLikeListCount(String id, String searchType, String keyword);

	// 내가 즐겨찾기 한 글 개수
	public int myBookmarkListCount(String id, String searchType, String keyword);
}
