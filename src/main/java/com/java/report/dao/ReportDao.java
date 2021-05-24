package com.java.report.dao;

import com.java.report.dto.ReportDto;

public interface ReportDao {
	
	// 신고 등록
	public int reportOk(ReportDto reportDto);
}
