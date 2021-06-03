package com.java.main.dao;

import java.util.HashMap;
import java.util.List;

import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;

public interface MainDao {
	

	public int accompanyBoardCount();

	public List<BoardDto> boardList();

	public int boardFileCount();

	public List<BoardFileDto> boardFileList();

	public List<BoardDto> boardReviewList();

//	public int boardReviewListImg(HashMap<String, Object> dtoMap);

//	public BoardDto popularRead(int boardPopular);



}
