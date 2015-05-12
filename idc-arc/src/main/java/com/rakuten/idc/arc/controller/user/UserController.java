package com.rakuten.idc.arc.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.idc.arc.helper.ActionHelper;
import com.rakuten.idc.arc.service.UserService;

@Controller
@ConfigurationProperties(prefix="mysite")
public class UserController {
	
	private UserService userService;
	
	@Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }
	
	@RequestMapping("/users")
    public ModelAndView getListUsersView(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
        model.setViewName("user/user");
        model.addObject("users", userService.getList());
        return model;
    }


}
