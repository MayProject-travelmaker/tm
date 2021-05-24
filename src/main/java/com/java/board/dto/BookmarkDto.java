package com.java.board.dto;

import java.util.Date;

public class BookmarkDto {
	private int bookmarkNo;
	private Date bmDate;
	
	public BookmarkDto() {
		super();
	}
	
	public BookmarkDto(int bookmarkNo, Date bmDate) {
		super();
		this.bookmarkNo = bookmarkNo;
		this.bmDate = bmDate;
	}
	
	@Override
	public String toString() {
		return "BookmarkDto [bookmarkNo=" + bookmarkNo + ", bmDate=" + bmDate + "]";
	}
	
	public int getBookmarkNo() {
		return bookmarkNo;
	}
	public void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}
	public Date getBmDate() {
		return bmDate;
	}
	public void setBmDate(Date bmDate) {
		this.bmDate = bmDate;
	}
	
}
