package com.java.report.dao;

import java.util.List;
import java.util.ArrayList;
import com.java.report.dto.ReportDto;

public interface ReportDao {
	
	// 신고 등록
	public int reportOk(ReportDto reportDto);
	
	public List<ReportDto> reportList() ;
}
