package com.java.report.dto;

import java.util.Date;

public class ReportDto {
	private String id;			// 신고된 회원 ID
	private int rp_ct_no;		// 신고 사유 코드 [21 ~ 26]
	private String rp_mem_id;	// 신고한 회원 ID
	private int rp_type;		// 신고유형 [1: 게시글 2: 댓글]
	private int post_no;		// 게시글 번호
	private int reply_no;		// 댓글 번호
	private Date rp_date;		// 신고 일시
	
	public ReportDto() {
		super();
	}

	public ReportDto(String id, int rp_ct_no, String rp_mem_id, int rp_type, int post_no, int reply_no, Date rp_date) {
		super();
		this.id = id;
		this.rp_ct_no = rp_ct_no;
		this.rp_mem_id = rp_mem_id;
		this.rp_type = rp_type;
		this.post_no = post_no;
		this.reply_no = reply_no;
		this.rp_date = rp_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRp_ct_no() {
		return rp_ct_no;
	}

	public void setRp_ct_no(int rp_ct_no) {
		this.rp_ct_no = rp_ct_no;
	}

	public String getRp_mem_id() {
		return rp_mem_id;
	}

	public void setRp_mem_id(String rp_mem_id) {
		this.rp_mem_id = rp_mem_id;
	}

	public int getRp_type() {
		return rp_type;
	}

	public void setRp_type(int rp_type) {
		this.rp_type = rp_type;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public Date getRp_date() {
		return rp_date;
	}

	public void setRp_date(Date rp_date) {
		this.rp_date = rp_date;
	}

	@Override
	public String toString() {
		return "ReportDto [id=" + id + ", rp_ct_no=" + rp_ct_no + ", rp_mem_id=" + rp_mem_id + ", rp_type=" + rp_type
				+ ", post_no=" + post_no + ", reply_no=" + reply_no + ", rp_date=" + rp_date + "]";
	}
	
}
