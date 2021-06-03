package com.java.chat.dao;

import java.util.List;
import java.util.Map;

import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;

public interface ChatDao {
	
	// ��� ä�ù� ã��
	public List<ChatRoomDto> findAllRoom();
	
	// ȸ�����̵� ���� ä�ù� ã��
	public List<ChatRoomDto> findRoomById(String id);
	
	// ä�ù� �����
	public int createChatRoom(ChatRoomDto chatRoomDto);

	// ������ ä�ù� ���� ������
	public ChatRoomDto getChatRoom(String id, int boardNo);
	
	// ä�ø޽��� �����ϱ�
	public int insertChatMessage(ChatMessageDto chatMessageDto);

	public int findUserToChatRoom(String id, int chatRoomNo);

	public int addUserToChatRoom(String id, int chatRoomNo);

	public int findRoomByIdCnt(String id);

	public int isChatRoomHost(String id, int chatRoomNo);

	public int exitChatRoom(String id, int chatRoomNo);

}
