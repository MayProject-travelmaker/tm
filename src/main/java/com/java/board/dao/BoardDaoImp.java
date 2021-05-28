package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.board.dto.BoardDto;

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
	public List<BoardDto> accompanyboardList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("accompanyboard_list", Map);
	}

	//동행 게시판 read 카운트
	@Override
	public int accompanyboardCount() {
		
		return sqlSessionTemplate.selectOne("accompanyboard_getCount");
	}

	//동행 후기 게시판 리스트
	@Override
	public List<BoardDto> accompanyreviewList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("accompanyreview_list", Map);
	}

	//동행 후기 게시판 read 카운트
	@Override
	public int accompanyreviewCount() {
		
		return sqlSessionTemplate.selectOne("accompanyreview_getCount");
	}

	//추천 여행 경로 게시판 리스트
	@Override
	public List<BoardDto> recommendpathList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("recommendpath_list", Map);
	}

	
	//추천 여행 경로 read 카운트
	@Override
	public int recommendpathCount() {
		
		return sqlSessionTemplate.selectOne("recommendpath_getCount");
	}


	//여행지 후기 게시판 리스트
	@Override
	public List<BoardDto> travelreviewList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("travelreview_list", Map);
	}
	
	//여행지 후기 read 카운트
	@Override
	public int travelreviewCount() {

		return sqlSessionTemplate.selectOne("travelreview_getCount");
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

	@Override
	public int boardupdateOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2) {
		if (isNotice == 1) {
			return sqlSessionTemplate.update("notice_update", dtoMap);	//공지글
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.update("board_file_map_update", dtoMap);	//일반글_파일,지도 포함
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() == true) {
			return sqlSessionTemplate.update("board_file_update", dtoMap);	//일반글_파일만
			
		} else if (map2.get("file") == "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.update("board_map_update", dtoMap);	//일반글_지도만
			
		} 
		return sqlSessionTemplate.update("board_updateOk", dtoMap);	//일반글_글만
	}
	
	// 게시글 좋아요 클릭 유무 확인
	@Override
	public int isBoardLike(HashMap<String, Object> likeMap) {
		return sqlSessionTemplate.selectOne("like_isLiked", likeMap);
	}
	
	// 좋아요 취소
	@Override
	public int boardLikeDel(HashMap<String, Object> likeMap) {
		int check = sqlSessionTemplate.delete("like_del", likeMap);
		if(check > 0) {
			// 좋아요 취소 성공 -> 좋아요 수 감소
			return sqlSessionTemplate.update("board_likeDel_cnt_update", likeMap);
		} else {
			// 좋아요 취소 실패
			return 0;
		}
		
	}
	
	// 좋아요
	@Override
	public int boardLikeOk(HashMap<String, Object> likeMap) {
		int check = sqlSessionTemplate.insert("like_ok", likeMap);
		if(check > 0) {
			// 좋아요 성공 -> 좋아요 수 증가
			return sqlSessionTemplate.update("board_likeOk_cnt_update", likeMap);
		} else {
			// 좋아요 실패
			return 0;
		}
	}

	// 인기글(isPopular) 세팅
	@Override
	public int IsPopularUpdate(int boardCode) {
		// isPopular 1인 게시글의 isPopular = 0으로 바꾸고 나서, 좋아요 랭킹 top 3를 isPopular = 1로 수정되게
		sqlSessionTemplate.update("isPopluar_reset", boardCode);
		int check = sqlSessionTemplate.update("isPopular_update", boardCode);
		return check;
	}
}