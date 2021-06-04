package com.java.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.main.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	
	  @RequestMapping(value="/")
	   public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response) {
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("request", request);
	      
	      mainService.mainPage(mav);
	      return mav;
	   }
}
