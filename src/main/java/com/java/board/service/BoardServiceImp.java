package com.java.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;
import com.java.board.dto.DiaryDto;
import com.java.board.dto.MapDto;
import com.java.board.dto.ReplyDto;
import com.java.chat.dto.ChatRoomDto;

@Component
public class BoardServiceImp implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//글쓰기
	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		BoardDto boardDto = (BoardDto) map.get("boardDto");
		BoardFileDto boardFileDto = new BoardFileDto();
		MapDto mapDto = new MapDto();
		
		// 채팅방 생성
		boolean chatRoom = false;
		if(map.get("chatRoom") != null) {
			chatRoom= (boolean) map.get("chatRoom");
		} else {
			chatRoom = false;
		}
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		HttpServletRequest request2 = (HttpServletRequest) map.get("request");
		String xAxis = request.getParameter("xAxis");
		String yAxis = request.getParameter("yAxis");
		mapDto.setxAxis(xAxis);
		mapDto.setyAxis(yAxis);
		
		//content 개행문자 처리
		boardDto.setContent(boardDto.getContent().replace("\r\n", "<br>"));
				
		// 일반글,공지글에 따른 isNotice 처리
		int isNotice;
		String notice = request.getParameter("notice");
		if (notice == null) { // 일반글일때
			isNotice = 0;
		} else { // 공지글일때
			isNotice = Integer.parseInt(notice); // 1
		}
		boardDto.setIsNotice(isNotice);
		
		MultipartFile upFile = request.getFile("file");
		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			String fileExtension = StringUtils.getFilenameExtension(fileName);
			File path = new File("C:/resources/img/"); // 파일 업로드 경로
			path.mkdir();
			if (path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);
					boardFileDto.setFileName(fileName);
					boardFileDto.setFilePath(file.getAbsolutePath());
					boardFileDto.setFileExtension(fileExtension);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		HashMap<String, Object> dtoMap = new HashMap<String, Object>();
		dtoMap.put("boardDto", boardDto);
		dtoMap.put("boardFileDto", boardFileDto);
		dtoMap.put("mapDto", mapDto);
		dtoMap.put("chatRoom", chatRoom); // 채팅방 생성
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("file", String.valueOf(upFile.isEmpty()));
		map2.put("map", request.getParameter("placeName"));
		
		int check = boardDao.boardWriteOk(dtoMap, map2);

		mav.addObject("check", check);
		mav.setViewName("board/writeOk");
	}

	// 동행 게시판 리스트
	@Override
	public void accompanyboardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null) {
			pageNumber = "1";
		}
		int currentPage = Integer.parseInt(pageNumber);

		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
		
		int count = boardDao.accompanyboardCount(searchType, keyword);

		List<BoardDto> accompanyboardList = null;
		if (count > 0) {
			accompanyboardList = boardDao.accompanyboardList(startRow, endRow, searchType, keyword);
		}

		mav.addObject("boardList", accompanyboardList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/accompanylist");
	}

	// 여행후기 리스트
	@Override
	public void accompanyreviewList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null) {
			pageNumber = "1";
		}
		int currentPage = Integer.parseInt(pageNumber);

		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
		
		int count = boardDao.accompanyreviewCount(searchType, keyword);

		List<BoardDto> accompanyreviewList = null;
		if (count > 0) {
			accompanyreviewList = boardDao.accompanyreviewList(startRow, endRow, searchType, keyword);
		}

		mav.addObject("boardList", accompanyreviewList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/accompanyreview");

	}

	// 추천 여행경로 리스트
	@Override
	public void recommendpathList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null) {
			pageNumber = "1";
		}
		int currentPage = Integer.parseInt(pageNumber);

		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;
		
		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");

		int count = boardDao.recommendpathCount(searchType, keyword);

		List<BoardDto> recommendpathList = null;
		if (count > 0) {
			recommendpathList = boardDao.recommendpathList(startRow, endRow, searchType, keyword);
		}

		mav.addObject("boardList", recommendpathList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/recommendpath");
	}

	// 여행지 후기 리스트
	@Override
	public void travelreviewList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null) {
			pageNumber = "1";
		}
		int currentPage = Integer.parseInt(pageNumber);

		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		//게시글검색
		String searchType = (String) map.get("searchType");
		String keyword = (String) map.get("keyword");
				
		int count = boardDao.travelreviewCount(searchType, keyword);

		List<BoardDto> travelreviewList = null;
		if (count > 0) {
			travelreviewList = boardDao.travelreviewList(startRow, endRow, searchType, keyword);
		}

		mav.addObject("boardList", travelreviewList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/travelreview");

	}

	//글 상세보기
	@Override
	public void boardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) request.getSession();
	
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int mapNo = Integer.parseInt(request.getParameter("boardNo"));
		int fileNo = Integer.parseInt(request.getParameter("boardNo"));

		String likeId = (String) session.getAttribute("id");	// 세션 아이디
		BoardDto boardDto = boardDao.boardRead(boardNo);
		MapDto mapDto = boardDao.mapRead(mapNo);
		BoardFileDto boardFileDto = boardDao.fileRead(fileNo);
		
		// 채팅방 번호
	 	int chatRoomNo = boardDao.findChatRoomByBoardNo(boardNo);
		// 좋아요
		int isLiked = 0;
		if(likeId != null) {
			// 로그인한 회원인 경우, 해당 게시글 좋아요 버튼을 눌렀는지 확인
			HashMap<String, Object> likeMap = new HashMap<String, Object>();
			likeMap.put("likeId", likeId);
			likeMap.put("boardNo", boardNo);
			isLiked = boardDao.isBoardLike(likeMap);
		}
		
		mav.addObject("boardDto", boardDto);
		mav.addObject("isLiked", isLiked);
		
		mav.addObject("boardDto", boardDto);
		mav.addObject("mapDto", mapDto);
		mav.addObject("boardFileDto", boardFileDto);
		// 채팅방
		mav.addObject("chatRoomNo",chatRoomNo);
		mav.setViewName("board/read");
	}
	
	//update
	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int mapNo = Integer.parseInt(request.getParameter("boardNo"));
		int fileNo = Integer.parseInt(request.getParameter("boardNo"));
	
		BoardDto boardDto=boardDao.boardupdate(boardNo);
		MapDto mapDto = boardDao.mapRead(mapNo);
		BoardFileDto boardFileDto = boardDao.fileRead(fileNo);
		
		//content 개행문자 처리
		boardDto.setContent(boardDto.getContent().replace("<br>","\r\n"));
		
		mav.addObject("boardDto", boardDto);
		mav.addObject("boardNo", boardNo);
		mav.addObject("mapDto", mapDto);
		mav.addObject("boardFileDto", boardFileDto);
		
		mav.setViewName("board/update");
	}

	//updateOk
		@Override
		public void boardUpdateOk(ModelAndView mav) {
			Map<String, Object> map = mav.getModelMap();
			
			BoardDto boardDto = (BoardDto) map.get("boardDto");
			BoardFileDto boardFileDto = new BoardFileDto();
			MapDto mapDto = new MapDto();
			
			MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
			String xAxis = request.getParameter("xAxis");
			String yAxis = request.getParameter("yAxis");
			mapDto.setxAxis(xAxis);
			mapDto.setyAxis(yAxis);
			
			//content 개행문자 처리
			boardDto.setContent(boardDto.getContent().replace("\r\n", "<br>"));
			
			//일반글,공지글에 따른 isNotice 처리
			int isNotice;
			String notice = request.getParameter("notice");
			if (notice == null) {	//일반글일때
				isNotice = 0;
			} else {				//공지글일때
				isNotice = Integer.parseInt(notice); //0 or 1
			}
			boardDto.setIsNotice(isNotice);
			
			MultipartFile upFile = request.getFile("file");
			if (upFile.getSize() != 0) {
				String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
				String fileExtension = StringUtils.getFilenameExtension(fileName);
				File path = new File("C:/resources/img/"); // 파일 업로드 경로
				path.mkdir();
				if (path.exists() && path.isDirectory()) {
					File file = new File(path, fileName);
					try {
						upFile.transferTo(file);
						boardFileDto.setFileName(fileName);
						boardFileDto.setFilePath(file.getAbsolutePath());
						boardFileDto.setFileExtension(fileExtension);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			HashMap<String, Object> dtoMap = new HashMap<String, Object>();
			dtoMap.put("boardDto", boardDto);
			dtoMap.put("boardFileDto", boardFileDto);
			dtoMap.put("mapDto", mapDto);
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("file", String.valueOf(upFile.isEmpty()));
			map2.put("map", request.getParameter("placeName"));
			
			int check = boardDao.boardUpdateOk(dtoMap, map2);

			mav.addObject("check", check);
			mav.setViewName("board/updateOk");
		}
	
	//deleteOk
	@Override
	public void boardDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		BoardDto boardDto = boardDao.boardCodeCheck(boardNo);
	 
		HashMap<String, Object> dtoMap = new HashMap<String, Object>();
		dtoMap.put("boardDto", boardDto);
		
		int check = boardDao.boardDeleteOk(dtoMap);
		
		mav.addObject("boardDto", boardDto);
		mav.addObject("check", check);
		
		mav.setViewName("board/deleteOk");
	}
	
	//즐겨찾기
	@Override
	public int bookmark(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();

		String id = (String) map.get("id");
		BoardDto boardDto = (BoardDto) map.get("boardDto");
		
		int check = boardDao.bookmark(id, boardDto);
		
		return check;
	}
	
	//즐겨찾기 중복체크
	@Override
	public int bmCheck(String id, int boardNo) {
		int check = boardDao.bmCheck(id, boardNo);
		
		return check;
	}
	
	//댓글입력
	@Override
	public int replyWrite(ReplyDto replyDto) {
		//댓글
		int sequenceNo = 0;
		
		//시퀀스넘버가 0이라면 (=댓글일때)
		if (replyDto.getSequenceNo() == 0) {
			int maxGNo = boardDao.maxGroupNo();
			int groupNo = maxGNo + 1;
			replyDto.setGroupNo(groupNo);
			
			sequenceNo += 1;
			replyDto.setSequenceNo(sequenceNo);
		} else {	//대댓글일때
			int groupNo = replyDto.getGroupNo();
			int maxSeqNo = boardDao.maxSequenceNo(groupNo);
			sequenceNo = maxSeqNo + 1;
			replyDto.setSequenceNo(sequenceNo);
		}
		
		return boardDao.replyWrite(replyDto);
	}
	
	//댓글리스트
	@Override
	public List<ReplyDto> replyList(int boardNo) {
		return boardDao.replyList(boardNo);
	}
	
	//댓글삭제
	@Override
	public int replyDel(int replyNo) {
		return boardDao.replyDel(replyNo);
	}
	
	//댓글수정
	@Override
	public int replyUpd(ReplyDto replyDto) {
		return boardDao.replyUpd(replyDto);
	}
	
	
	// 좋아요
	@Override
	public HashMap<String, Object> boardLikeOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) request.getSession();

		String likeId = (String) session.getAttribute("id"); // 좋아요 누른 세션 아이디
		int boardNo = Integer.parseInt((String) map.get("boardNo")); // 게시글 번호
		int boardCode = Integer.parseInt((String) map.get("boardCode")); // 게시판 코드
		String postId = (String) map.get("postId"); // 게시글 작성자 아이디

		// 해시맵에 데이터 담기
		HashMap<String, Object> likeMap = new HashMap<String, Object>();
		likeMap.put("likeId", likeId);
		likeMap.put("boardNo", boardNo);
		likeMap.put("boardCode", boardCode);
		likeMap.put("postId", postId);
		// 세션아이디 해당 게시글 좋아요 클릭 유무 확인
		int check = boardDao.isBoardLike(likeMap);
		String likeType = null;
		if (check > 0) {
			// 좋아요 누른 적 있는 경우 -> 좋아요 취소
			likeType = "del";
			boardDao.boardLikeDel(likeMap);
		} else {
			// 좋아요 누른 적 없는 경우 -> 좋아요
			likeType = "ok";
			boardDao.boardLikeOk(likeMap);
		}

		// 인기글 update - isPopluar
		int isPopluar_update_check = boardDao.IsPopularUpdate(boardCode);

		// 반환값 결과, 좋아요 유형
		HashMap<String, Object> result = new HashMap<String, Object>();
		// result.put("check", check);
		result.put("likeType", likeType);

		return result;
	}

	//여행일지 리스트
	@Override
	public void diaryList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		HttpSession session = request.getSession();
		String diId = (String) session.getAttribute("id");
		
		List<DiaryDto> diaryDto = boardDao.diaryList(diId);
		
		System.out.println("============11111");
		System.out.println(diaryDto);
		System.out.println("============111111");
		
		mav.addObject("diaryList",diaryDto);
		
		mav.setViewName("board/mydiary");
		
	}

	//내 여행일지 업로드Ok
	@Override
	public void diaryUploadOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		HttpServletRequest request2 = (HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String diContent = request.getParameter("diContent");
		
		List<MultipartFile> fileList = request.getFiles("file");
		List<DiaryDto> newFileList = new ArrayList<DiaryDto>();
		
		for (MultipartFile mf : fileList) {
			DiaryDto diaryDto = new DiaryDto();
			diaryDto.setDiId(id);
			diaryDto.setDiContent(diContent.replace("\r\n", "<br>"));	//개행처리

			String fileName = Long.toString(System.currentTimeMillis()) + "_" + mf.getOriginalFilename();
			
            File path = new File("C:/resources/diaryImg/");
            if(!path.exists()){
            	path.mkdirs();
            }
            if (path.exists() && path.isDirectory()) {
				try {
					mf.transferTo(new File(path, fileName));
					diaryDto.setImgName(fileName);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
			newFileList.add(diaryDto);
		}
		
		int check = boardDao.diaryUploadOk(newFileList);
		
		mav.addObject("check", check);
		mav.setViewName("board/writeOk2");
	}
	//여행일지 삭제
	@Override
	public int diaryDel(int diaryNo) {
		return boardDao.diaryDel(diaryNo);
	}
	//여행일지 수정
	@Override
	public void diaryUpd(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
		
		DiaryDto diaryDto = boardDao.diaryUpd(diaryNo);
		
		mav.addObject("diaryDto", diaryDto);
		mav.setViewName("board/mydiaryUpdate");
	}
	//여행일지 수정 완료
	@Override
	public int diaryUpdOk(int diaryNo, String diContent) {
		int check = boardDao.diaryUpdOk(diaryNo, diContent);
		return check;
	}
}