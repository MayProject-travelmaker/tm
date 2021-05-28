package com.java.board.dto;

import java.util.Date;

public class ReplyDto {
	private int replyNo;
	private int boardNo;
	private int boardCode;
	private String postId;
	private String id;
	private String content;
	private Date writeDate;
	private Date updateDate;
	private int isDel;
	//´ë´ñ±Û
	private int groupNo;
	private int sequenceNo;
	private int sequenceLevel;
	
	public ReplyDto() {
		super();
	}
	
	public ReplyDto(int replyNo, int boardNo, int boardCode, String postId, String id, String content, Date writeDate,
			Date updateDate, int isDel, int groupNo, int sequenceNo, int sequenceLevel) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.postId = postId;
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.isDel = isDel;
		this.groupNo = groupNo;
		this.sequenceNo = sequenceNo;
		this.sequenceLevel = sequenceLevel;
	}

	@Override
	public String toString() {
		return "ReplyDto [replyNo=" + replyNo + ", boardNo=" + boardNo + ", boardCode=" + boardCode + ", postId="
				+ postId + ", id=" + id + ", content=" + content + ", writeDate=" + writeDate + ", updateDate="
				+ updateDate + ", isDel=" + isDel + ", groupNo=" + groupNo + ", sequenceNo="
				+ sequenceNo + ", sequenceLevel=" + sequenceLevel + "]";
	}

	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public int getSequenceLevel() {
		return sequenceLevel;
	}
	public void setSequenceLevel(int sequenceLevel) {
		this.sequenceLevel = sequenceLevel;
	}
	
}