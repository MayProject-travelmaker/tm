package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.board.dto.BoardDto;
import com.java.board.dto.ReplyDto;

@Component
public class BoardDaoImp implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//글쓰기
	@Override
	public int boardWriteOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2) {
		
		if (isNotice == 1) {
			return sqlSessionTemplate.insert("notice_insert", dtoMap);	//공지글
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.insert("board_file_map_insert", dtoMap);	//일반글_파일,지도 포함
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() == true) {
			return sqlSessionTemplate.insert("board_file_insert", dtoMap);	//일반글_파일만
			
		} else if (map2.get("file") == "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.insert("board_map_insert", dtoMap);	//일반글_지도만
			
		} 
		return sqlSessionTemplate.insert("board_insert", dtoMap);	//일반글_글만
	}

	//동행 게시판 리스트
	@Override
	public List<BoardDto> accompanyboardList(int startRow, int endRow, String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("accompanyboard_list", Map);
	}

	//동행 게시판 read 카운트
	@Override
	public int accompanyboardCount(String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectOne("accompanyboard_getCount", Map);
	}

	//동행 후기 게시판 리스트
	@Override
	public List<BoardDto> accompanyreviewList(int startRow, int endRow, String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("accompanyreview_list", Map);
	}

	//동행 후기 게시판 read 카운트
	@Override
	public int accompanyreviewCount(String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectOne("accompanyreview_getCount", Map);
	}

	//추천 여행 경로 게시판 리스트
	@Override
	public List<BoardDto> recommendpathList(int startRow, int endRow, String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("recommendpath_list", Map);
	}

	
	//추천 여행 경로 read 카운트
	@Override
	public int recommendpathCount(String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectOne("recommendpath_getCount", Map);
	}


	//여행지 후기 게시판 리스트
	@Override
	public List<BoardDto> travelreviewList(int startRow, int endRow, String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("travelreview_list", Map);
	}
	
	//여행지 후기 read 카운트
	@Override
	public int travelreviewCount(String searchType, String keyword) {
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("searchType", searchType);
		Map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectOne("travelreview_getCount", Map);
	}

	//글 상세보기
	@Override
	public BoardDto boardRead(int boardNo) {
		sqlSessionTemplate.update("board_view", boardNo);
		return sqlSessionTemplate.selectOne("board_read", boardNo);
	}

	//update
	@Override
	public BoardDto boardupdate(int boardNo) {
		
		return sqlSessionTemplate.selectOne("board_update", boardNo);
	}
	
	//=====================================================================즐겨찾기
	//즐겨찾기
	@Override
	public int bookmark(String id, BoardDto boardDto) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("boardDto", boardDto);
				
		return sqlSessionTemplate.insert("bookmark", map);
	}
	
	//즐겨찾기 중복체크
	@Override
	public int bmCheck(String id, int boardNo) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("boardNo", Integer.toString(boardNo));
		
		return sqlSessionTemplate.selectOne("bmCheck", map);
	}
	
	//=====================================================================댓글
	//댓글입력
	@Override
	public int replyWrite(ReplyDto replyDto) {
		return sqlSessionTemplate.insert("replyWrite", replyDto);
	}
	
	//댓글리스트
	@Override
	public List<ReplyDto> replyList(int boardNo) {
		return sqlSessionTemplate.selectList("replyList", boardNo);
	}
	
	//댓글삭제
	@Override
	public int replyDel(int replyNo) {
		return sqlSessionTemplate.update("replyDel", replyNo);
	}
	
	//댓글수정
	@Override
	public int replyUpd(ReplyDto replyDto) {
		return sqlSessionTemplate.update("replyUpd", replyDto);
	}

	//대댓글 max(group_no) 구하기
	@Override
	public int maxGroupNo() {
		return sqlSessionTemplate.selectOne("maxGNo");
	}

	//대댓글 max(sequence_no) 구하기
	@Override
	public int maxSequenceNo(int groupNo) {
		return sqlSessionTemplate.selectOne("maxSeqNo", groupNo);
	}
	

	
}