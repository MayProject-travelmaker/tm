package com.java.board.dao;

import java.util.HashMap;
import java.util.List;

import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;
import com.java.board.dto.MapDto;
import com.java.board.dto.ReplyDto;

public interface BoardDao {

	//글쓰기
	public int boardWriteOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2);

	//동행 게시판 리스트, 카운트
	public List<BoardDto> accompanyboardList(int startRow, int endRow, String searchType, String keyword);
	public int accompanyboardCount(String searchType, String keyword);

	//동행 후기 리스트, 카운트
	public List<BoardDto> accompanyreviewList(int startRow, int endRow, String searchType, String keyword);
	public int accompanyreviewCount(String searchType, String keyword);

	//추천 여행경로 리스트, 카운트
	public List<BoardDto> recommendpathList(int startRow, int endRow, String searchType, String keyword);
	public int recommendpathCount(String searchType, String keyword);

	//여행지 후기 리스트, 카운트
	public List<BoardDto> travelreviewList(int startRow, int endRow, String searchType, String keyword);
	public int travelreviewCount(String searchType, String keyword);

	//상세글읽기
	public BoardDto boardRead(int boardNo);

	//update
	public BoardDto boardupdate(int boardNo);
	public int boardUpdateOk(HashMap<String, Object> dtoMap, HashMap<String, String> map2);

	//delete
	public BoardDto boardCodeCheck(int boardNo);
	public int boardDeleteOk(HashMap<String, Object> dtoMap);

	//즐겨찾기
	public int bookmark(String id, BoardDto boardDto);
	//즐겨찾기 중복체크
	public int bmCheck(String id, int boardNo);

	//댓글작성
	public int replyWrite(ReplyDto replyDto);
	//댓글리스트
	public List<ReplyDto> replyList(int boardNo);
	//댓글삭제
	public int replyDel(int replyNo);
	//댓글수정
	public int replyUpd(ReplyDto replyDto);
	//대댓글 max(group_no) 구하기
	public int maxGroupNo();
	//대댓글 max(sequence_no) 구하기
	public int maxSequenceNo(int groupNo);
	
	public MapDto mapRead(int mapNo);

	public BoardFileDto fileRead(int fileNo);
	
	
}