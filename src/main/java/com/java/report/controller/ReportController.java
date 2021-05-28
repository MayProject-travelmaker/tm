package com.java.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.report.dto.ReportDto;
import com.java.report.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	// 신고 완료
	@ResponseBody
	@RequestMapping(value="/report/reportOk.do", method=RequestMethod.POST)
	public String reportOk(HttpServletRequest request, HttpServletResponse response, ReportDto reportDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("reportDto", reportDto);
		
		String check = Integer.toString(reportService.reportOk(mav));

		return check;
	}
}
