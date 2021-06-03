package com.java.chat.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.chat.dao.ChatDao;
import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;
import com.java.chat.service.ChatService;


@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler {
	@Autowired
	ChatService chatService;
	ChatDao chatDao;
	ChatMessageDto chatMessageDto;
	
	// 세션 리스트
	// 세션리스트 - 기존 채팅방
	//private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// 세션리스트 - 멀티 채팅방 ("방 번호", "세션")
	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	//private final ChatRoomRepository chatRoomRepository = new ChatRoomRepository();
	//private final ObjectMapper objectMapper = new ObjectMapper();
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	
	 // 클라이언트가 연결 되었을 때 실행
//	 
//	 @Override
//	 public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		 // 채팅방에 접속한 사용자 세션을 리스트에 저장
//		 sessionList.add(session);
//		 
//		 logger.info("{} 연결됨",session.getId());
//		 System.out.println("{} 연결됨"+ session.getId()); 
//		 
//		 for(int i = 0; i < sessionList.size(); i++) {
//			 WebSocketSession s = sessionList.get(i);
//			 s.sendMessage(new TextMessage(session.getId() + "님이 입장 했습니다."));
//		 }
//	 }
	 

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
        
        //모든 유저(세션)에게 메세지 출력 - 기존 채팅방
//		 for(WebSocketSession sess : sessionList) { 
//        System.out.println("{}로 부터 {} 받음");
//			 sess.sendMessage(new TextMessage(message.getPayload()));
//			 System.out.println("{}로 부터 {} 받음"+ sess);
//		 }
		 
        super.handleTextMessage(session, message);
        // JSON --> Map으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
        
        // 인터셉터를 이용 해 세션 아이디 받아오기
        Map<String,Object> sessionMap = session.getAttributes();
        String id = (String)sessionMap.get("id");
        System.out.println("세션 아이디 : "+id);
        // 현재 시간
        Date time = new Date();
        // 시간 형태 변환
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        // 현재 날짜,시각
        String currentTime = format.format(time);
        // messageType별로 채팅 메시지 전송
        switch(mapReceive.get("messageType")) {
        
        // CLIENT 입장
        case "ENTER":
        	// 세션 리스트에 저장
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("chatRoomNo", mapReceive.get("chatRoomNo")); 	// 채팅방 번호
        	map.put("session", session);							// 세션
        	map.put("currentTime", currentTime);					// 현재 시각
        	sessionList.add(map);

        	// 같은 채팅방에 입장 메시지 전송
        	for(int i = 0; i < sessionList.size(); i++) {
        		Map<String, Object> mapSessionList = sessionList.get(i);
        		
        		String chatRoomNo = (String) mapSessionList.get("chatRoomNo");
        		WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
        		
        		if(chatRoomNo.equals(mapReceive.get("chatRoomNo"))) {
        			Map<String, String> mapToSend = new HashMap<String, String>();
        			mapToSend.put("chatRoomNo", chatRoomNo);
        			mapToSend.put("messageType", "ENTER");
        			mapToSend.put("currentTime", currentTime);
        			mapToSend.put("msg", id+"님이 입장 했습니다.");
        			
        			String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
        		}
        	}
        	break;
        	
        // CLIENT 메시지
        case "CHAT":
        	//chatService.insertChatMessage(id, Integer.parseInt(mapReceive.get("chatRoomNo")), mapReceive.get("msg"));
        	// 같은 채팅방에 메시지 전송
        	for(int i = 0; i < sessionList.size(); i++) {
        		Map<String, Object> mapSessionList = sessionList.get(i);
        		String chatRoomNo = (String) mapSessionList.get("chatRoomNo");
        		WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
        		
        		if(chatRoomNo.equals(mapReceive.get("chatRoomNo"))) {
        			Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("chatRoomNo", chatRoomNo);
					mapToSend.put("messageType", "CHAT");
					mapToSend.put("currentTime", currentTime);
					mapToSend.put("msg", id + " : " + mapReceive.get("msg"));

					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
        		}
        	}
        	//chatService.insertChatMessage(id, Integer.parseInt(mapReceive.get("chatRoomNo")), mapReceive.get("msg"));
        	break;
        	
        }
        //        String msg = message.getPayload();
//        ChatMessageDto chatMessage = objectMapper.readValue(msg, ChatMessageDto.class);
//        ChatRoomDto chatRoom = chatRoomRepository.findRoomById(chatMessage.getChatRoomNo());
//        chatRoom.handleMessage(session, chatMessage, objectMapper);
	}
	
	// 클라이언트 연결을 끊었을 때 실행
	 @Override 
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 인터셉터를 이용 해 세션 아이디 받아오기
		Map<String, Object> sessionMap = session.getAttributes();
		String id = (String) sessionMap.get("id");
		System.out.println("세션 아이디 : " + id);
	        
		super.afterConnectionClosed(session, status);
		 
		ObjectMapper objectMapper = new ObjectMapper();
		String current_chatRoomNo = "";

		// 사용자 세션을 리스트에서 제거
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> map = sessionList.get(i);
			String chatRoomNo = (String) map.get("chatRoomNo");
			WebSocketSession sess = (WebSocketSession) map.get("session");

			if (session.equals(sess)) {
				current_chatRoomNo = chatRoomNo;
				sessionList.remove(map);
				break;
			}
		}
		
		// 같은 채팅방에 퇴장 메세지 전송
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> mapSessionList = sessionList.get(i);
			String chatRoomNo = (String) mapSessionList.get("chatRoomNo");
			WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

			if (chatRoomNo.equals(current_chatRoomNo)) {
				Map<String, String> mapToSend = new HashMap<String, String>();
				mapToSend.put("chatRoomNo", chatRoomNo);
				mapToSend.put("messageType", "EXIT");
				mapToSend.put("msg", id + "님이 퇴장 했습니다.");

				String jsonStr = objectMapper.writeValueAsString(mapToSend);
				sess.sendMessage(new TextMessage(jsonStr));
			}
		}


		 
//		 // 채팅방에서 퇴장한 사용자의 세션을 리스트에서 제거
//		 sessionList.remove(session);
//		 
//		 logger.info("{} 연결 끊김.", session.getId());
//		 System.out.println("{} 연결 끊김."+session.getId()); 
//		 
//		 for(int i=0; i < sessionList.size(); i++) {
//			 WebSocketSession s = sessionList.get(i);
//			 s.sendMessage(new TextMessage(session.getId()+"님이 퇴장 했습니다"));
//		 }
	}
	 
}
