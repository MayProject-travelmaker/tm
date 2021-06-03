package com.java.chat.dto;

import java.util.Date;

public class ChatMessageDto {
	// 채팅 메시지 번호
	private int chatMessageNo;
	// 채팅방 번호
	private int chatRoomNo;
	// 발신자 아이디
	private String id;
	// 게시글 작성자 아이디
	private String postId;
	// 게시글 번호
	private int boardNo;
	// 게시판 코드
	private int boardCode;
	// 채팅 메시지
	private String message;
	// 메시지 확인 여부
	private int isRead;
	// 메시지 전송 일자
	private Date sendDate;
	
	private MessageType type;
	
	public ChatMessageDto() {
		super();
	}

	
	public ChatMessageDto(int chatMessageNo, int chatRoomNo, String id, String postId, int boardNo,
			int boardCode, String message, int isRead, Date sendDate,  MessageType type) {
		super();
		this.chatMessageNo = chatMessageNo;
		this.chatRoomNo = chatRoomNo;
		this.id = id;
		this.postId = postId;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.message = message;
		this.isRead = isRead;
		this.sendDate = sendDate;
		this.type = type;
	}


	public MessageType getType() {
		return type;
	}


	public void setType(MessageType type) {
		this.type = type;
	}


	public int getChatMessageNo() {
		return chatMessageNo;
	}


	public void setChatMessageNo(int chatMessageNo) {
		this.chatMessageNo = chatMessageNo;
	}


	public int getChatRoomNo() {
		return chatRoomNo;
	}


	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPostId() {
		return postId;
	}


	public void setPostId(String postId) {
		this.postId = postId;
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getIsRead() {
		return isRead;
	}


	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}


	public Date getSendDate() {
		return sendDate;
	}


	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}


	@Override
	public String toString() {
		return "ChatMessageDto [chatMessageNo=" + chatMessageNo + ", chatRoomNo=" + chatRoomNo + ", id=" + id
				+ ", postId=" + postId + ", boardNo=" + boardNo + ", boardCode=" + boardCode + ", message=" + message
				+ ", isRead=" + isRead + ", sendDate=" + sendDate + "]";
	}
	
}
