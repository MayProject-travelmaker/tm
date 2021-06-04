package com.java.chat.dto;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatRoomDto {
	// 채팅방 번호
	private int chatRoomNo;
	// 게시글 작성자 아이디
	private String postId;
	// 게시글 번호
	private int boardNo;
	// 게시판 코드
	private int boardCode;
	// 채팅방 생성 일자
	private Date chatRoomDate;
	// 채팅방 참가 유저
	private String users;
	
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	public ChatRoomDto() {
		super();
	}
	
	
	
	public ChatRoomDto(int chatRoomNo, String postId, int boardNo, int boardCode, Date chatRoomDate, String users,
			Set<WebSocketSession> sessions) {
		super();
		this.chatRoomNo = chatRoomNo;
		this.postId = postId;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.chatRoomDate = chatRoomDate;
		this.users = users;
		this.sessions = sessions;
	}



	public int getChatRoomNo() {
		return chatRoomNo;
	}
	
	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
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
	
	public Date getChatRoomDate() {
		return chatRoomDate;
	}
	
	public void setChatRoomDate(Date chatRoomDate) {
		this.chatRoomDate = chatRoomDate;
	}
	
	
	public String getUsers() {
		return users;
	}



	public void setUsers(String users) {
		this.users = users;
	}



	@Override
	public String toString() {
		return "ChatRoomDto [chatRoomNo=" + chatRoomNo + ", postId=" + postId + ", boardNo=" + boardNo + ", boardCode="
				+ boardCode + ", chatRoomDate=" + chatRoomDate + "]";
	}

	public void handleMessage(WebSocketSession session, ChatMessageDto chatMessage, ObjectMapper objectMapper) throws IOException {
		if(chatMessage.getType() == com.java.chat.dto.MessageType.ENTER){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getId() + "님이 입장하셨습니다.");
        }
        else if(chatMessage.getType() == com.java.chat.dto.MessageType.LEAVE){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getId() + "님임 퇴장하셨습니다.");
        }
        else{
            chatMessage.setMessage(chatMessage.getId() + " : " + chatMessage.getMessage());
        }
        send(chatMessage,objectMapper);
    }

    private void send(ChatMessageDto chatMessage, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                                    writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
		
	}
	
	
}
