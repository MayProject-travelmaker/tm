package com.java.chat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.chat.dao.ChatDao;
import com.java.chat.dto.ChatMessageDto;
import com.java.chat.dto.ChatRoomDto;

@Component
public class ChatService {
	
	@Autowired
	private ChatDao chatDao;
	private ChatMessageDto chatMessageDto;
	
	// ��� ä�ù� ��ȸ
	public void findAllRoom(ModelAndView mav){
		List<ChatRoomDto> chatRoomDtoList = chatDao.findAllRoom();
		System.out.println("findAllRoom ChatService : " + chatRoomDtoList);
		mav.addObject("chatRoomList", chatRoomDtoList);
		
		mav.setViewName("chat/chatRoomList");
	}
	
	// ������ ä�ù� ����Ʈ ��ȸ
	public void findRoomById(ModelAndView mav){
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id : "+id);
		int check = chatDao.findRoomByIdCnt(id);
		if(check > 0) {
			// �ش�ȸ���� �������ִ� ä���� ������
			List<ChatRoomDto> chatRoomDtoList = chatDao.findRoomById(id);
			mav.addObject("chatRoomList", chatRoomDtoList);
			System.out.println("chatRoomDtoList : " + chatRoomDtoList);
		} else {
			mav.addObject("chatRoomList", null);
		}
		
		mav.setViewName("chat/chatRoomList");
	}
	
	public int createChatRoom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("id")+request.getParameter("boardNo") );
		String id = (String) session.getAttribute("id"); // �� ���� ���̵�
		int boardCode = 11;
		
		ChatRoomDto chatRoomDto = new ChatRoomDto();
		chatRoomDto.setPostId(id);
		chatRoomDto.setBoardCode(boardCode);
		
		int check = chatDao.createChatRoom(chatRoomDto);
		
		return check;
	}

	public int insertChatMessage(HttpServletRequest request, List<String> msgList, int chatRoomNo) {
			String fileName = Integer.toString(chatRoomNo);
			String txt = "";
			
			// �޽����� �����ϸ�
			if(msgList.size() > 0) {
				
				// ���� ��� ����
				File path = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")); 
				System.out.println(path);
				path.mkdir();
				// ���� ��ΰ� �����ϸ�
				if (path.exists() && path.isDirectory()) {
					// ���� ��ü ����
					File file = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")+fileName+".txt");
					
					try {
						// true ������ ������ ���� ���뿡 �̾ �ۼ� (�ӵ������ ���� bufferwriter ȥ��)
						BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
						
						// ���� �ȿ� ���ڿ� ����
						for(int i = 0; i < msgList.size(); i++) {
							txt += msgList.get(i) + "\n";
						}
						fw.write(txt);
						// ��ü �ݱ�
						fw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			///int check = chatDao.insertChatMessage(chatMessageDto);
			//System.out.println("����Ϸ�");
			
			return 1;
		
	}

	// ���� ä�ø޽��� �о����
	public void readChatMessage(HttpServletRequest request, ModelAndView mav) {
		// ä�ù� ��ȣ
		int chatRoomNo = Integer.parseInt((String) request.getParameter("chatRoomNo"));
		// ä�� ���ϸ�
		String fileName = Integer.toString(chatRoomNo);
		// ���� ä�ø޽��� ����Ʈ
		ArrayList<String> msgData = new ArrayList<String>();
		try {
			// ���� ��ü ����
			File file = new File(request.getSession().getServletContext().getRealPath("/resources/chat/")+fileName+".txt");
			
			// �ش� ä�ù�ȣ�� ������ �����ϸ� ���� �о����
			if (file.exists()) {
				//�Է� ��Ʈ�� ����
	            FileReader filereader = new FileReader(file);
	            //�Է� ���� ����
	            BufferedReader bufReader = new BufferedReader(filereader);
	            String line = "";

	            while((line = bufReader.readLine()) != null){
	                msgData.add(line);
	            }
	            //.readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.            
	            bufReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// msgData ���
		mav.addObject("msgData", msgData);
	}// readChatMessage

	// ������ ä�ù� �����ο����� �ľ�
	public ChatRoomDto addUserToChatRoom(HttpServletRequest request, int chatRoomNo) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int check = chatDao.findUserToChatRoom(id, chatRoomNo);
		
		// ������ ���� ä�ù� �����ڰ� �ƴѰ��
		if(check == 0) {
			// ä�ù� �����ڿ� ���� ���� �߰�
			chatDao.addUserToChatRoom(id, chatRoomNo);
		}
		// �ش�ä�ù� ���� ������
		return chatDao.getChatRoom(id, chatRoomNo);
	}

	// ä�ù� ������
	public void exitChatRoom(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int chatRoomNo = Integer.parseInt((String) request.getParameter("chatRoomNo"));
		
		System.out.println(id + chatRoomNo);
		// ä�ù��� ������ ������ ä�ù� �����ڶ�� -> ä�ù� ����
		int check = chatDao.isChatRoomHost(id, chatRoomNo);
		if(check > 0) {
			// ������ ������ ä�ù� ������
			
		} else {
			// ������ ������ �Ϲ� ������
			chatDao.exitChatRoom(id, chatRoomNo);
		}
		
		mav.setViewName("redirect:chatRoomList.do");
	}
}

