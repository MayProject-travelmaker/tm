package com.java.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.java.board.dto.NoticeDto;

@Component
public class BoardServiceImp implements BoardService {

	@Autowired
	private BoardDao boardDao;

	//湲��곌린
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
		
		//�쇰�湲�,怨듭�湲��� �곕Ⅸ isNotice 泥�由�
		int isNotice;
		String notice = request.getParameter("notice");
		if (notice == null) {	//�쇰�湲����깆�대㈃ isNotice 湲곕낯媛� 吏��� (-1:�쇰�湲�, 0:怨듭��대┫��, 1:怨듭��щ┫��)
			isNotice = -1;
		} else {				//怨듭�湲�����
			isNotice = Integer.parseInt(notice);
			noticeDto.setIsNotice(isNotice);
		}
		
		MultipartFile upFile = request.getFile("file");
		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			String fileExtension = StringUtils.getFilenameExtension(fileName);
			File path = new File(request2.getSession().getServletContext().getRealPath("/resources/img/")); // ���� ��濡��� ����寃쎈�
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

	// ���� 寃����� 由ъ�ㅽ��
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

		int count = boardDao.accompanyboardCount();

		List<BoardDto> accompanyboardList = null;
		if (count > 0) {
			accompanyboardList = boardDao.accompanyboardList(startRow, endRow);
		}
	

		mav.addObject("boardList", accompanyboardList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		
		mav.setViewName("board/accompanylist");
	}

	// �ы����湲� 由ъ�ㅽ��
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

		int count = boardDao.accompanyreviewCount();

		List<BoardDto> accompanyreviewList = null;
		if (count > 0) {
			accompanyreviewList = boardDao.accompanyreviewList(startRow, endRow);
		}

		mav.addObject("boardList", accompanyreviewList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/accompanyreview");

	}

	// 異�泥� �ы��寃쎈� 由ъ�ㅽ��
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

		int count = boardDao.recommendpathCount();

		List<BoardDto> recommendpathList = null;
		if (count > 0) {
			recommendpathList = boardDao.recommendpathList(startRow, endRow);
		}

		mav.addObject("boardList", recommendpathList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/recommendpath");
	}

	// �ы��吏� ��湲� 由ъ�ㅽ��
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

		int count = boardDao.travelreviewCount();

		List<BoardDto> travelreviewList = null;
		if (count > 0) {
			travelreviewList = boardDao.travelreviewList(startRow, endRow);
		}

		mav.addObject("boardList", travelreviewList);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);

		mav.setViewName("board/travelreview");

	}

	//湲� ���몃낫湲�
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

	//�ы���쇱� ��濡���
	@Override
	public void diaryUpload(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		
		
		
		
		
		
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
		
		String baseDir = request2.getSession().getServletContext().getRealPath("/resources/img/");
		String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
		
		for (MultipartFile mf : fileList) {
			DiaryDto diaryDto = new DiaryDto();
			diaryDto.setDiId(id);
			diaryDto.setDiContent(diContent);

			String fileName = Long.toString(System.currentTimeMillis()) + "_" + mf.getOriginalFilename();
			
            File path = new File(formattedDate);
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

}