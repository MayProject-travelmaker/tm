package com.java.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.BoardDto;
import com.java.board.dto.ReplyDto;
import com.java.board.service.BoardService;
import com.java.chat.service.ChatService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ChatService chatService;
	
	//동행게시판 넘어가기
	@RequestMapping(value="/board/accompanylist.do", method= RequestMethod.GET)
	public ModelAndView boardAccompanyList(HttpServletRequest request, HttpServletResponse response
											, @RequestParam(required = false) String searchType
											, @RequestParam(required = false) String keyword) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		boardService.accompanyboardList(mav);
		return mav;
	}
		
	//동행후기게시판 넘어가기
	@RequestMapping(value="/board/accompanyreview.do", method= RequestMethod.GET)
	public ModelAndView boardAccompanyReview(HttpServletRequest request, HttpServletResponse response
											, @RequestParam(required = false) String searchType
											, @RequestParam(required = false) String keyword) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		boardService.accompanyreviewList(mav);
		return mav;
	}
	
	//추천경로게시판으로 넘어가기
	@RequestMapping(value="/board/recommendpath.do", method= RequestMethod.GET)
	public ModelAndView boardRecommendPath(HttpServletRequest request, HttpServletResponse response
											, @RequestParam(required = false) String searchType
											, @RequestParam(required = false) String keyword) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		boardService.recommendpathList(mav);
		return mav;
	}
	
	//여행지후기게시판으로 넘어가기
	@RequestMapping(value="/board/travelreview.do", method= RequestMethod.GET)
	public ModelAndView boardTravelReview(HttpServletRequest request, HttpServletResponse response
											, @RequestParam(required = false) String searchType
											, @RequestParam(required = false) String keyword) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		boardService.travelreviewList(mav);
		return mav;	
	}
	
	//글쓰기
	@RequestMapping(value="/board/write.do")
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("board/write");
	}
	
	
	//글쓰기완료
	@RequestMapping(value="/board/writeOk.do", method= RequestMethod.POST)
	public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("boardDto", boardDto);
		System.out.println("createChatRoom : " + request.getParameter("chatRoom"));
		
		// 동행 채팅방 만들기
		if(request.getParameter("chatRoom") != null) {
			mav.addObject("chatRoom", true);
		}
		boardService.boardWriteOk(mav);
		return mav;
	}

	//글읽기
	@RequestMapping(value="/board/read.do")
	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardRead(mav);
		return mav;
	}
	//글수정
	@RequestMapping(value="/board/update.do")
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardUpdate(mav);
		return mav;
	}
	//글수정완료
	@RequestMapping(value="/board/updateOk.do", method= RequestMethod.POST)
	public ModelAndView boardUpdateOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("boardDto", boardDto);
		
		boardService.boardUpdateOk(mav);
		return mav;
	}
	//글삭제완료
	@RequestMapping(value="/board/deleteOk.do", method= RequestMethod.GET)
	public ModelAndView boardDeleteOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("boardDto", boardDto);
	
		boardService.boardDeleteOk(mav);
		return mav;
	}
	
	//=====================================================================즐겨찾기
	//즐겨찾기
	@ResponseBody
	@RequestMapping(value = "/board/bookmark.do", method = RequestMethod.POST)
	public int bookmark(HttpSession session, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("id");
		mav.addObject("id", id);
		mav.addObject("boardDto", boardDto);
		
		int check = boardService.bookmark(mav);
		
		return check;	
	}
	
	//즐겨찾기 중복체크
	@ResponseBody
	@RequestMapping(value = "/board/bmCheck.do", method = RequestMethod.POST)
	public int bmCheck(HttpSession session, int boardNo) {
		String id = (String) session.getAttribute("id");
		
		int check = boardService.bmCheck(id, boardNo);

		return check;
	}
	
	//=====================================================================댓글
	//댓글입력
	@ResponseBody
	@RequestMapping(value = "/board/replyWrite.do", method = RequestMethod.POST)
	public int replyWrite(HttpSession session, ReplyDto replyDto) {
		String id = (String) session.getAttribute("id");
		replyDto.setId(id);
		return boardService.replyWrite(replyDto);
	}
	
	//댓글리스트
	@ResponseBody
	@RequestMapping(value = "/board/replyList.do", method = RequestMethod.POST)
	public List<ReplyDto> replyList(int boardNo) {
		List<ReplyDto> list = boardService.replyList(boardNo);
		return list;
	}
	
	//댓글삭제
	@ResponseBody
	@RequestMapping(value = "/board/replyDel.do", method = RequestMethod.POST)
	public int replyDel(int replyNo) {
		int check = boardService.replyDel(replyNo); 
		return check;
	}
	
	//댓글수정
	@ResponseBody
	@RequestMapping(value = "/board/replyUpd.do", method = RequestMethod.POST)
	public int replyUpd(int replyNo, String content) {
		ReplyDto replyDto = new ReplyDto();
		replyDto.setReplyNo(replyNo);
		replyDto.setContent(content);
		int check = boardService.replyUpd(replyDto);
		return check;
	}
	
	// 좋아요 버튼
	@ResponseBody
	@RequestMapping(value = "/board/likeOk.do", method = RequestMethod.GET)
	public HashMap<String, Object> boardLikeOk(@RequestParam Map<String, Object> param, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("boardNo", (String) param.get("boardNo")); // 게시글 번호
		mav.addObject("boardCode", (String) param.get("boardCode")); // 게시판 코드
		mav.addObject("postId", (String) param.get("postId")); // 작성자 아이디

		return boardService.boardLikeOk(mav);
	}
}