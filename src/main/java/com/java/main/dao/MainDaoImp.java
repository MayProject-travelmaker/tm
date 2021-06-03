package com.java.main.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;

@Component
public class MainDaoImp implements MainDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int accompanyBoardCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardList_getCount");
	}

	@Override
	public List<BoardDto> boardList() {
		return sqlSessionTemplate.selectList("board_list");
	}

	@Override
	public int boardFileCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardFile_Count");
	}

	@Override
	public List<BoardFileDto> boardFileList() {
//		System.out.println(sqlSessionTemplate.selectList("boardFile_list"));
		return sqlSessionTemplate.selectList("boardFile_list");
	}

	@Override
	public List<BoardDto> boardReviewList() {
//		System.out.println(sqlSessionTemplate.selectList("boardReview_list"));
		return sqlSessionTemplate.selectList("boardReview_list");
	}

//	@Override
//	public int boardReviewListImg(HashMap<String, Object> dtoMap) {
//		sqlSessionTemplate.selectList("boardFile_list", dtoMap);
//		sqlSessionTemplate.selectList("boardReview_list", dtoMap);
//		System.out.println(dtoMap);
//		return 1;
//	}

//	@Override
//	public BoardDto popularRead(int boardPopular) {
//		
//		return sqlSessionTemplate.selectOne("board_read_popular", boardPopular);
//	}






}
