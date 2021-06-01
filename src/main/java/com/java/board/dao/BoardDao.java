package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import com.java.board.dto.BoardDto;
import com.java.board.dto.DiaryDto;

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

	public int diaryUploadOk(List<DiaryDto> newFileList);
	
	public List<DiaryDto> diaryList(String diId);
	
	

}