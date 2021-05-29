package com.java.report.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.report.dao.ReportDao;
import com.java.report.dto.ReportDto;

@Component
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDao reportDao;
	
	// 신고 등록
	@Override
	public int reportOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		ReportDto reportDto = (ReportDto)map.get("reportDto");
//		if(reportDto.getRp_type() == 1) {
//			// 게시글 신고인 경우, 댓글번호 0 으로 고정
//			reportDto.setReply_no(0);
//		}
		return reportDao.reportOk(reportDto);
	}

}
