package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import com.java.board.dto.BoardDto;

public interface BoardDao {

	//글쓰기
	public int boardWriteOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2);

	//동행 게시판 리스트, 카운트
	public List<BoardDto> accompanyboardList(int startRow, int endRow);
	public int accompanyboardCount();

	//동행 후기 리스트, 카운트
	public List<BoardDto> accompanyreviewList(int startRow, int endRow);
	public int accompanyreviewCount();

	//추천 여행경로 리스트, 카운트
	public List<BoardDto> recommendpathList(int startRow, int endRow);
	public int recommendpathCount();

	//여행지 후기 리스트, 카운트
	public List<BoardDto> travelreviewList(int startRow, int endRow);
	public int travelreviewCount();

	//상세글읽기
	public BoardDto boardRead(int boardNo);

	//update
	public BoardDto boardupdate(int boardNo);

}