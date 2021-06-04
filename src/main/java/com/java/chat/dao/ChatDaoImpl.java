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
	
	// 모든 채팅방 찾기
	@Override
	public List<ChatRoomDto> findAllRoom() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("findAllRoom");
	}

	// 회원아이디 별로 채팅방 찾기
	@Override
	public List<ChatRoomDto> findRoomById(String id) {
		System.out.println(id);
		System.out.println("result: "+sqlSessionTemplate.selectList("findRoomById", id));
		return sqlSessionTemplate.selectList("findRoomById", id);
	}

	// 채팅방 만들기
	@Override
	public int createChatRoom(ChatRoomDto chatRoomDto) {
			
		return sqlSessionTemplate.insert("createChatRoom", chatRoomDto);
	}

	// 참가한 채팅방 정보 얻어오기
	@Override
	public ChatRoomDto getChatRoom(String id, int chatRoomNo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("getChatRoom", chatRoomNo);
	}
	
	// 채팅 메시지 저장하기
	@Override
	public int insertChatMessage(ChatMessageDto chatMessageDto) {
		System.out.println("DAO - insertChatMessage");
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("insertChatMsg", chatMessageDto);
	}

	// 채팅방에 유저 참여자 확인하기
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

	// 채팅방 나가기 - 나가는 유저 채팅방개설자인지 확인
	@Override
	public int isChatRoomHost(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.selectOne("isChatRoomHost",map);
	}
	// 채팅방 나가기 - 일반 참여자
	@Override
	public int exitChatRoom(String id, int chatRoomNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatRoomNo", chatRoomNo);
		
		return sqlSessionTemplate.update("exitChatRoom",map);
	}

}
