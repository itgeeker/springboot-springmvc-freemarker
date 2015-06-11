package com.rakuten.idc.arc.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.idc.arc.constants.ArcConstants;

@Controller
public class HomeController {
    @RequestMapping(value = ArcConstants.ROOT, method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        String messageString = ArcConstants.WELCOME_MESSAGE;
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ArcConstants.HOME_VIEW);
        mav.addObject(ArcConstants.MESSAGE, messageString);
        return mav;
    }
}