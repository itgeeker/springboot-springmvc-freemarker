package com.rakuten.idc.arc.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;

@Controller
@ConfigurationProperties(prefix = ArcConstants.MY_SITE)
public class LoginController {

    private LoginService loginService;

    @Inject
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_LOGIN)
    public ModelAndView getListUsersView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.LOGIN_VIEW);
        return model;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_AUTHENTICATE)
    public ModelAndView authenticate(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.RESULT_VIEW);
        User user = new User();
        user.setUserName(request.getParameter(ArcConstants.USERNAME));
        user.setPassword(request.getParameter(ArcConstants.PASSWORD));
        if (loginService.authenticate(user)) {
            model.addObject(ArcConstants.RESULT,
                    ArcConstants.AUTHENTICATION_SUCCESS);
            model.addObject(ArcConstants.USER_DETAILS,
                    loginService.getUserDetails(user));
        } else {
            model.addObject(ArcConstants.RESULT,
                    ArcConstants.AUTHENTICATION_FAILURE);
        }
        return model;
    }
}
