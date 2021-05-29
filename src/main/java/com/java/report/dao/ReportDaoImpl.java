package com.java.report.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.board.dto.BoardDto;
import com.java.report.dto.ReportDto;

@Component
public class ReportDaoImpl implements ReportDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 신고 등록
	@Override
	public int reportOk(ReportDto reportDto) {

		return sqlSessionTemplate.insert("report_insert", reportDto);
	}

	@Override
	public List<ReportDto> reportList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("report_list");
	}

	

}
