package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import com.java.board.dto.BoardDto;

public interface BoardDao {

	//�۾���
	public int boardWriteOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2);

	//���� �Խ��� ����Ʈ, ī��Ʈ
	public List<BoardDto> accompanyboardList(int startRow, int endRow);
	public int accompanyboardCount();

	//���� �ı� ����Ʈ, ī��Ʈ
	public List<BoardDto> accompanyreviewList(int startRow, int endRow);
	public int accompanyreviewCount();

	//��õ ������ ����Ʈ, ī��Ʈ
	public List<BoardDto> recommendpathList(int startRow, int endRow);
	public int recommendpathCount();

	//������ �ı� ����Ʈ, ī��Ʈ
	public List<BoardDto> travelreviewList(int startRow, int endRow);
	public int travelreviewCount();

	//�󼼱��б�
	public BoardDto boardRead(int boardNo);

	//update
	public BoardDto boardupdate(int boardNo);

	// 추가한 항목
	// 글 수정
	public int boardupdateOk(HashMap<String, Object> dtoMap, int isNotice, HashMap<String, String> map2);

	// 좋아요 클릭 유무 확인
	public int isBoardLike(HashMap<String, Object> likeMap);

	// 좋아요 취소
	public int boardLikeDel(HashMap<String, Object> likeMap);

	// 좋아요
	public int boardLikeOk(HashMap<String, Object> likeMap);

	// 인기글 세팅
	public int IsPopularUpdate(int boardCode);

}