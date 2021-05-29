package com.java.calendar.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CalendarDto {
	private String id;
	private String subject;
	private String startDate;
	private String endDate;
	private String memo;
	private int	num;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CalendarDto [id=" + id + ", subject=" + subject + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", memo=" + memo + ", num=" + num + "]";
	}
	
	
	
	
}
