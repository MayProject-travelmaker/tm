package com.java.board.dto;

import java.util.Date;

public class DiaryDto {
	private int diaryNo;
	private String diId;
	private String diContent;
	private Date diDate;
	private String imgName;
	
	
	
	
	public DiaryDto() {
		super();
	}

	public DiaryDto(int diaryNo, String diId, String diContent, Date diDate, String imgName) {
		super();
		this.diaryNo = diaryNo;
		this.diId = diId;
		this.diContent = diContent;
		this.diDate = diDate;
		this.imgName = imgName;
	}

	@Override
	public String toString() {
		return "DiaryDto [diaryNo=" + diaryNo + ", diId=" + diId + ", diContent=" + diContent + ", diDate=" + diDate
				+ ", imgName=" + imgName + "]";
	}

	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getDiId() {
		return diId;
	}

	public void setDiId(String diId) {
		this.diId = diId;
	}

	public String getDiContent() {
		return diContent;
	}

	public void setDiContent(String diContent) {
		this.diContent = diContent;
	}

	public Date getDiDate() {
		return diDate;
	}

	public void setDiDate(Date diDate) {
		this.diDate = diDate;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	

}
