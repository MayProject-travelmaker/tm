package com.java.board.dto;

import java.util.Date;

public class NoticeDto {

	private int noticeNo;
	private String title;
	private String content;
	private int readCnt;
	private int isNotice;
	private Date writeDate;
	private Date updateDate;
	
	public NoticeDto() {
		super();
	}
	
	public NoticeDto(int noticeNo, String title, String content, int readCnt, int isNotice, Date writeDate,
			Date updateDate) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.isNotice = isNotice;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", title=" + title + ", content=" + content + ", readCnt=" + readCnt
				+ ", isNotice=" + isNotice + ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
	}
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public int getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
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
	
}