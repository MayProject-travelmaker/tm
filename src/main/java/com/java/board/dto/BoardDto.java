package com.java.board.dto;

import java.util.Date;

public class BoardDto {
	private int boardNo;
	private String postId;
	private int boardCode;
	private String title;
	private String content;
	private String area;
	private Date writeDate;
	private Date updateDate;
	private int readCnt;
	private int likeCnt;
	private int isDel;
	private int isPopular;
	
	private long fileSize;
	
	public BoardDto() {
		super();
	}
	
	public BoardDto(int boardNo, String postId, int boardCode, String title, String content, String area,
			Date writeDate, Date updateDate, int readCnt, int likeCnt, int isDel, int isPopular, long fileSize) {
		super();
		this.boardNo = boardNo;
		this.postId = postId;
		this.boardCode = boardCode;
		this.title = title;
		this.content = content;
		this.area = area;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.readCnt = readCnt;
		this.likeCnt = likeCnt;
		this.isDel = isDel;
		this.isPopular = isPopular;
		this.fileSize = fileSize;
	}
	
	
	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", postId=" + postId + ", boardCode=" + boardCode + ", title=" + title
				+ ", content=" + content + ", area=" + area + ", writeDate=" + writeDate + ", updateDate=" + updateDate
				+ ", readCnt=" + readCnt + ", likeCnt=" + likeCnt + ", isDel=" + isDel + ", isPopular=" + isPopular
				+ ", fileSize=" + fileSize + "]";
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(int isPopular) {
		this.isPopular = isPopular;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	
}