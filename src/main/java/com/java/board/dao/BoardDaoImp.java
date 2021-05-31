package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.board.dto.BoardDto;
import com.java.board.dto.DiaryDto;

@Component
public class BoardDaoImp implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//湲��곌린
	@Override
	public int boardWriteOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2) {
		
		if (isNotice == 1) {
			return sqlSessionTemplate.insert("notice_insert", dtoMap);	//怨듭�湲�
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.insert("board_file_map_insert", dtoMap);	//�쇰�湲�_����,吏��� �ы��
			
		} else if (map2.get("file") != "true" && map2.get("map").isEmpty() == true) {
			return sqlSessionTemplate.insert("board_file_insert", dtoMap);	//�쇰�湲�_���쇰�
			
		} else if (map2.get("file") == "true" && map2.get("map").isEmpty() != true) {
			return sqlSessionTemplate.insert("board_map_insert", dtoMap);	//�쇰�湲�_吏���留�
			
		} 
		return sqlSessionTemplate.insert("board_insert", dtoMap);	//�쇰�湲�_湲�留�
	}

	//���� 寃����� 由ъ�ㅽ��
	@Override
	public List<BoardDto> accompanyboardList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("accompanyboard_list", Map);
	}

	//���� 寃����� read 移댁�댄��
	@Override
	public int accompanyboardCount() {
		
		return sqlSessionTemplate.selectOne("accompanyboard_getCount");
	}

	//���� ��湲� 寃����� 由ъ�ㅽ��
	@Override
	public List<BoardDto> accompanyreviewList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("accompanyreview_list", Map);
	}

	//���� ��湲� 寃����� read 移댁�댄��
	@Override
	public int accompanyreviewCount() {
		
		return sqlSessionTemplate.selectOne("accompanyreview_getCount");
	}

	//異�泥� �ы�� 寃쎈� 寃����� 由ъ�ㅽ��
	@Override
	public List<BoardDto> recommendpathList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("recommendpath_list", Map);
	}

	
	//異�泥� �ы�� 寃쎈� read 移댁�댄��
	@Override
	public int recommendpathCount() {
		
		return sqlSessionTemplate.selectOne("recommendpath_getCount");
	}


	//�ы��吏� ��湲� 寃����� 由ъ�ㅽ��
	@Override
	public List<BoardDto> travelreviewList(int startRow, int endRow) {
		Map<String, Integer> Map = new HashMap<String, Integer>();
		Map.put("startRow", startRow);
		Map.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("travelreview_list", Map);
	}
	
	//�ы��吏� ��湲� read 移댁�댄��
	@Override
	public int travelreviewCount() {

		return sqlSessionTemplate.selectOne("travelreview_getCount");
	}

	//湲� ���몃낫湲�
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
	//나의여행일지 insert
	@Override
	public int diaryUploadOk(List<DiaryDto> newFileList) {
		
		return sqlSessionTemplate.insert("diary_insert", newFileList);
	}

	//나의여행일지 list select
	@Override
	public DiaryDto diaryList(int diaryNo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("diary_list", diaryNo);
	}
	
	
	

	
}