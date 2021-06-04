package com.java.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.BoardDto;
import com.java.board.dto.BoardFileDto;
import com.java.main.dao.MainDao;

@Component
public class MainServiceImp implements MainService {

	@Autowired
	private MainDao mainDao;
	
	  @Override
	   public void mainPage(ModelAndView mav) {
	      Map<String, Object> map = mav.getModelMap();
	               
	      int accompanyBoardCount = mainDao.accompanyBoardCount();

	      //accompany board making
	      List<BoardDto> boardList = null;
	      if (accompanyBoardCount > 0) {
	         boardList = mainDao.boardList();
	      }
	            
	      mav.addObject("boardList", boardList);
	      
	      //popular board making
	      int boardFileCount = mainDao.boardFileCount();
	      
	      List<BoardFileDto> boardFileList = null;
	      if (boardFileCount > 0) {
	         boardFileList = mainDao.boardFileList();
	      }
	      
	      List<BoardDto> boardReviewList = null;
	      if (accompanyBoardCount > 0) {
	         boardReviewList = mainDao.boardReviewList();
	      }
	      //list 2개로 담아서 값 가져와서 setting (img 9개 만들어짐...)
	      mav.addObject("boardFileList", boardFileList);
	      mav.addObject("boardReviewList", boardReviewList);
	      
	      mav.setViewName("index");
	   }

}
