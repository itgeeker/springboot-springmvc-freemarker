package com.rakuten.idc.arc.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.UserService;

@Controller
@ConfigurationProperties(prefix = ArcConstants.MY_SITE)
public class UserController {

    private UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_USERS)
    public ModelAndView getListUsersView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.USERS_VIEW);
        model.addObject(ArcConstants.USERS, userService.getList());
        System.out.println(userService.getList());
        return model;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_REGISTER)
    public ModelAndView regiesterUser(HttpServletRequest request) {
        User user = new User();
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.USER_VIEW);
        model.addObject(ArcConstants.USER, user);
        return model;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_ADD_USER)
    public ModelAndView addUser(HttpServletRequest request) {
        User user = processUser(request);
        String reEnteredPassword = request
                .getParameter(ArcConstants.RE_ENTERED_PASSWORD);
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.RESULT_VIEW);
        if (!user.getPassword().equals(reEnteredPassword)) {
            model.addObject(ArcConstants.RESULT,
                    ArcConstants.PASSWORD_MATCH_FAILED);
        } else {
            userService.addUser(user);
            model.addObject(ArcConstants.RESULT,
                    ArcConstants.ADD_USER_SUCCESSFUL);
        }
        return model;
    }

    private User processUser(HttpServletRequest request) {
        User user = new User();
        String firstName = request.getParameter(ArcConstants.FIRST_NAME);
        String lastName = request.getParameter(ArcConstants.LAST_NAME);
        String email = request.getParameter(ArcConstants.EMAIL);
        String password = request.getParameter(ArcConstants.PASSWORD);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserName(firstName + lastName);
        user.setPassword(password);

        return user;
    }

}
