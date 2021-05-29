package com.java.calendar.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.calendar.dao.CalendarDao;
import com.java.calendar.dto.CalendarDto;

@Component
public class CalendarService {
	
	@Autowired
	private CalendarDao calendarDao;
	
	public List<CalendarDto> showSchedule(String id) throws Exception {
		return calendarDao.showSchedule(id);
	}
	
	public void addSchedule(CalendarDto calendarDto, String id) throws Exception {
		
		calendarDto.setId(id);
		calendarDao.addSchedule(calendarDto);
	}

	public void readSchedule(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		String id=(String) session.getAttribute("id");
		CalendarDto calendarDto = calendarDao.readSchedule(id,num);
		mav.addObject("calendarDto",calendarDto);
		mav.setViewName("member/readSchedule");
	}

	public void updateSchedule(CalendarDto calendarDto, String id) {
		calendarDto.setId(id);
		calendarDao.updateSchedule(calendarDto);
		
	}

	public void deleteSchedule(CalendarDto calendarDto, String id) {
		
		calendarDto.setId(id);
		calendarDao.deleteSchedule(calendarDto);
	}
}
