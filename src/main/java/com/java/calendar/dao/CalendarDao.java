package com.java.calendar.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.calendar.dto.CalendarDto;

@Component
public class CalendarDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
//	private static final String namespace = "com.java.calendar.dao.CalendarDao";
	
	public List<CalendarDto> showSchedule(String id) throws Exception {
		return sqlSessionTemplate.selectList("showSchedule", id);
	}
	
	public void addSchedule(CalendarDto calendarDto) throws Exception {
		System.out.println("일정추가 :"+calendarDto);
		sqlSessionTemplate.insert("addSchedule", calendarDto);
	}

	
	public CalendarDto readSchedule(String id, int num) { 
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("num", num);
		return sqlSessionTemplate.selectOne("showOneSchedule",map); 
	}

	public void updateSchedule(CalendarDto calendarDto) {
		System.out.println("일정수정 :"+calendarDto);
		sqlSessionTemplate.update("updateSchedule", calendarDto);
		
	}

	public void deleteSchedule(CalendarDto calendarDto) {
		System.out.println(calendarDto.toString());
		sqlSessionTemplate.delete("deleteSchedule",calendarDto);
	}
	 
}
