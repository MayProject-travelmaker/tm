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
	
	// ���� ����Ʈ
	// ���Ǹ���Ʈ - ���� ä�ù�
	//private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// ���Ǹ���Ʈ - ��Ƽ ä�ù� ("�� ��ȣ", "����")
	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	//private final ChatRoomRepository chatRoomRepository = new ChatRoomRepository();
	//private final ObjectMapper objectMapper = new ObjectMapper();
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	
	 // Ŭ���̾�Ʈ�� ���� �Ǿ��� �� ����
//	 
//	 @Override
//	 public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		 // ä�ù濡 ������ ����� ������ ����Ʈ�� ����
//		 sessionList.add(session);
//		 
//		 logger.info("{} �����",session.getId());
//		 System.out.println("{} �����"+ session.getId()); 
//		 
//		 for(int i = 0; i < sessionList.size(); i++) {
//			 WebSocketSession s = sessionList.get(i);
//			 s.sendMessage(new TextMessage(session.getId() + "���� ���� �߽��ϴ�."));
//		 }
//	 }
	 

	// Ŭ���̾�Ʈ�� ������ ������ �޽����� �������� �� ����
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("{}�� ���� {} ����", session.getId(), message.getPayload());
        
        //��� ����(����)���� �޼��� ��� - ���� ä�ù�
//		 for(WebSocketSession sess : sessionList) { 
//        System.out.println("{}�� ���� {} ����");
//			 sess.sendMessage(new TextMessage(message.getPayload()));
//			 System.out.println("{}�� ���� {} ����"+ sess);
//		 }
		 
        super.handleTextMessage(session, message);
        // JSON --> Map���� ��ȯ
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
        
        // ���ͼ��͸� �̿� �� ���� ���̵� �޾ƿ���
        Map<String,Object> sessionMap = session.getAttributes();
        String id = (String)sessionMap.get("id");
        System.out.println("���� ���̵� : "+id);
        // ���� �ð�
        Date time = new Date();
        // �ð� ���� ��ȯ
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        // ���� ��¥,�ð�
        String currentTime = format.format(time);
        // messageType���� ä�� �޽��� ����
        switch(mapReceive.get("messageType")) {
        
        // CLIENT ����
        case "ENTER":
        	// ���� ����Ʈ�� ����
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("chatRoomNo", mapReceive.get("chatRoomNo")); 	// ä�ù� ��ȣ
        	map.put("session", session);							// ����
        	map.put("currentTime", currentTime);					// ���� �ð�
        	sessionList.add(map);

        	// ���� ä�ù濡 ���� �޽��� ����
        	for(int i = 0; i < sessionList.size(); i++) {
        		Map<String, Object> mapSessionList = sessionList.get(i);
        		
        		String chatRoomNo = (String) mapSessionList.get("chatRoomNo");
        		WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
        		
        		if(chatRoomNo.equals(mapReceive.get("chatRoomNo"))) {
        			Map<String, String> mapToSend = new HashMap<String, String>();
        			mapToSend.put("chatRoomNo", chatRoomNo);
        			mapToSend.put("messageType", "ENTER");
        			mapToSend.put("currentTime", currentTime);
        			mapToSend.put("msg", id+"���� ���� �߽��ϴ�.");
        			
        			String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
        		}
        	}
        	break;
        	
        // CLIENT �޽���
        case "CHAT":
        	//chatService.insertChatMessage(id, Integer.parseInt(mapReceive.get("chatRoomNo")), mapReceive.get("msg"));
        	// ���� ä�ù濡 �޽��� ����
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
	
	// Ŭ���̾�Ʈ ������ ������ �� ����
	 @Override 
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// ���ͼ��͸� �̿� �� ���� ���̵� �޾ƿ���
		Map<String, Object> sessionMap = session.getAttributes();
		String id = (String) sessionMap.get("id");
		System.out.println("���� ���̵� : " + id);
	        
		super.afterConnectionClosed(session, status);
		 
		ObjectMapper objectMapper = new ObjectMapper();
		String current_chatRoomNo = "";

		// ����� ������ ����Ʈ���� ����
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
		
		// ���� ä�ù濡 ���� �޼��� ����
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> mapSessionList = sessionList.get(i);
			String chatRoomNo = (String) mapSessionList.get("chatRoomNo");
			WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

			if (chatRoomNo.equals(current_chatRoomNo)) {
				Map<String, String> mapToSend = new HashMap<String, String>();
				mapToSend.put("chatRoomNo", chatRoomNo);
				mapToSend.put("messageType", "EXIT");
				mapToSend.put("msg", id + "���� ���� �߽��ϴ�.");

				String jsonStr = objectMapper.writeValueAsString(mapToSend);
				sess.sendMessage(new TextMessage(jsonStr));
			}
		}


		 
//		 // ä�ù濡�� ������ ������� ������ ����Ʈ���� ����
//		 sessionList.remove(session);
//		 
//		 logger.info("{} ���� ����.", session.getId());
//		 System.out.println("{} ���� ����."+session.getId()); 
//		 
//		 for(int i=0; i < sessionList.size(); i++) {
//			 WebSocketSession s = sessionList.get(i);
//			 s.sendMessage(new TextMessage(session.getId()+"���� ���� �߽��ϴ�"));
//		 }
	}
	 
}
