package com.java.report.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.report.dao.ReportDao;
import com.java.report.dto.ReportDto;
import com.java.report.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportDao reportDao;
	// 신고 완료
	@RequestMapping(value="/report/reportOk.do", method=RequestMethod.POST)
	public ModelAndView reportOk(HttpServletRequest request, HttpServletResponse response, ReportDto reportDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("reportDto", reportDto);
		
		int check = reportService.reportOk(mav);
		
		return mav;
	}
	
	//신고리스트 조회
	@ResponseBody
	@RequestMapping(value="/report/reportlist")
	public ArrayList<ReportDto> reportList(Model model) {
			
		ArrayList<ReportDto> reportlist = (ArrayList<ReportDto>) reportDao.reportList();
		return reportlist;
	}
	
	
}
