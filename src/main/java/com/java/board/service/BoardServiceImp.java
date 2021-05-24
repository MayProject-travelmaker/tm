package com.java.board.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;
import com.java.board.dto.MapDto;
import com.java.board.dto.NoticeDto;
import com.java.board.dto.ReplyDto;

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
		NoticeDto noticeDto = new NoticeDto();
		
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		HttpServletRequest request2 = (HttpServletRequest) map.get("request");
		String xAxis = request.getParameter("xAxis");
		String yAxis = request.getParameter("yAxis");
		mapDto.setxAxis(xAxis);
		mapDto.setyAxis(yAxis);
		
		//일반글,공지글에 따른 isNotice 처리
		int isNotice;
		String notice = request.getParameter("notice");
		if (notice == null) {	//일반글작성이면 isNotice 기본값 지정 (-1:일반글, 0:공지내릴때, 1:공지올릴때)
			isNotice = -1;
		} else {				//공지글작성
			isNotice = Integer.parseInt(notice);
			noticeDto.setIsNotice(isNotice);
		}
		
		MultipartFile upFile = request.getFile("file");
		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			String fileExtension = StringUtils.getFilenameExtension(fileName);
			File path = new File(request2.getSession().getServletContext().getRealPath("/resources/img/")); // 파일 업로드 상대경로
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
		dtoMap.put("noticeDto", noticeDto);
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("file", String.valueOf(upFile.isEmpty()));
		map2.put("map", request.getParameter("placeName"));
		
		int check = boardDao.boardWriteOk(dtoMap, isNotice, map2);

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

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		BoardDto boardDto = boardDao.boardRead(boardNo);
	 
		mav.addObject("boardDto", boardDto);
		
		mav.setViewName("board/read");

	}
	
	//update
	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
	
		BoardDto boardDto=boardDao.boardupdate(boardNo);
		
		mav.addObject("boardDto", boardDto);
		mav.addObject("boardNo", boardNo);
		mav.setViewName("board/update");
	}
	
	//=====================================================================즐겨찾기
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
	
	//=====================================================================댓글
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
	
	
}