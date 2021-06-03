package com.java.chat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;

@Component
public class ChatDaoImpl implements ChatDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// ��� ä�ù� ã��
	@Override
	public List<ChatRoomDto> findAllRoom() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("findAllRoom");
	}

	// ȸ�����̵� ���� ä�ù� ã��
	@Override
	public List<ChatRoomDto> findRoomById(String id) {
		System.out.println(id);
		System.out.println("result: "+sqlSessionTemplate.selectList("findRoomById", id));
		return sqlSessionTemplate.selectList("findRoomById", id);
	}

	// ä�ù� �����
	@Override
	public int createChatRoom(ChatRoomDto chatRoomDto) {
			
		return sqlSessionTemplate.insert("createChatRoom", chatRoomDto);
	}

	// ������ ä�ù� ���� ������
	@Override
	public ChatRoomDto getChatRoom(String id, int chatRoomNo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("getChatRoom", chatRoomNo);
	}
	
	// ä�� �޽��� �����ϱ�
	@Override
	public int insertChatMessage(ChatMessageDto chatMessageDto) {
		System.out.println("DAO - insertChatMessage");
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("insertChatMsg", chatMessageDto);
	}

	// ä�ù濡 ���� ������ Ȯ���ϱ�
	@Override
	public int findUserToChatRoom(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.selectOne("findUserToChatRoom", map);
	}

	@Override
	public int addUserToChatRoom(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.update("addUserToChatRoom", map);		
	}

	@Override
	public int findRoomByIdCnt(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("findRoomByIdCnt", id);
	}

	// ä�ù� ������ - ������ ���� ä�ù氳�������� Ȯ��
	@Override
	public int isChatRoomHost(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.selectOne("isChatRoomHost",map);
	}
	// ä�ù� ������ - �Ϲ� ������
	@Override
	public int exitChatRoom(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.update("exitChatRoom",map);
	}

}
