package com.java.chat.dao;

import java.util.List;
import java.util.Map;

import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;

public interface ChatDao {
	
	// 모든 채팅방 찾기
	public List<ChatRoomDto> findAllRoom();
	
	// 회원아이디 별로 채팅방 찾기
	public List<ChatRoomDto> findRoomById(String id);
	
	// 채팅방 만들기
	public int createChatRoom(ChatRoomDto chatRoomDto);

	// 참가한 채팅방 정보 얻어오기
	public ChatRoomDto getChatRoom(String id, int boardNo);
	
	// 채팅메시지 저장하기
	public int insertChatMessage(ChatMessageDto chatMessageDto);

	public int findUserToChatRoom(String id, int chatRoomNo);

	public int addUserToChatRoom(String id, int chatRoomNo);

	public int findRoomByIdCnt(String id);

	public int isChatRoomHost(String id, int chatRoomNo);

	public int exitChatRoom(String id, int chatRoomNo);

}
