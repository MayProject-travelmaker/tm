package com.java.mypage.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.board.dto.BoardDto;
import com.java.board.dto.ReplyDto;

@Component
public class MypageDaoImpl implements MypageDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 내가 쓴 글
	@Override
	public List<BoardDto> myBoardList(String id, int startRow, int endRow, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("startRow", Integer.toString(startRow));
		map.put("endRow", Integer.toString(endRow));
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		System.out.println("map : "+map);
		return sqlSessionTemplate.selectList("myBoardList",map);
	}

	// 내가 쓴 댓글
	@Override
	public List<ReplyDto> myReplyList(String id, int startRow, int endRow, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("startRow", Integer.toString(startRow));
		map.put("endRow", Integer.toString(endRow));
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("myReplyList", map);
	}
	
	// 내가 좋아요 누른 글
	@Override
	public List<BoardDto> myLikeList(String id, int startRow, int endRow, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("startRow", Integer.toString(startRow));
		map.put("endRow", Integer.toString(endRow));
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("myLikeList", map);
	}

	// 내가 즐겨찾기 추가 한 글
	@Override
	public List<BoardDto> myBookmarkList(String id, int startRow, int endRow, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("startRow", Integer.toString(startRow));
		map.put("endRow", Integer.toString(endRow));
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("myBookmarkList", map);
	}

	// 내가 쓴 글 개수
	@Override
	public int myBoardListCount(String id, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSessionTemplate.selectOne("myBoardListCount", map);
	}
	
	// 내가 쓴 댓글 개수
	@Override
	public int myReplyListCount(String id, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSessionTemplate.selectOne("myReplyListCount", map);
	}	

	// 내가 좋아요 누른 글 개수
	@Override
	public int myLikeListCount(String id, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSessionTemplate.selectOne("myLikeListCount", map);
	}
	
	// 내가 즐겨찾기 추가 한 글 개수
	@Override
	public int myBookmarkListCount(String id, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSessionTemplate.selectOne("myBookmarkListCount", map);
	}
	
}
