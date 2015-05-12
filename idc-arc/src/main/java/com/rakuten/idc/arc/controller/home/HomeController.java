package com.rakuten.idc.arc.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.idc.arc.helper.ActionHelper;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home/home");
		String str = "Welcome to Accounts.Rakuten.com";
		mav.addObject("message", str);
		return mav;
	}
}