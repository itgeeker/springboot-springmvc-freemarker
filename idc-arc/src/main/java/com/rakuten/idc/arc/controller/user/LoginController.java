package com.rakuten.idc.arc.controller.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
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

    /**
     * This method is just to handle the login view.
     * This will set the login page.
     * @param request
     * @return
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_LOGIN)
    public ModelAndView getListUsersView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.LOGIN_VIEW);
        return model;
    }

    /**
     * The request will hold the username and password credentials,
     * now user wants to log in to the system.
     * @param request
     * @return
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_AUTHENTICATE)
    public ModelAndView authenticate(HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
       /**
        * Invalidating the old session and creating a new session.
        */
        if(null != session.getAttribute(ArcConstants.AUTHMODEL)){
            session.invalidate();
            session = request.getSession(true);
        }
        
        User user = new User();
        user.setUserName(request.getParameter(ArcConstants.USERNAME));
        user.setPassword(request.getParameter(ArcConstants.PASSWORD));
        
        ResponseModel rmodel = null;
        try {
            rmodel = loginService.authenticate(user);
        } catch (ApiClientException e) {
            e.printStackTrace();
        }
      
        GetAuthModel authModel = null;
        GidError error = null;
        ModelAndView model = new ModelAndView();
        if (rmodel.getResponseCode() == 200) {
         /**
          * Successful Login
          */
            authModel = (GetAuthModel) rmodel;    
            session.setAttribute(ArcConstants.AUTHMODEL, authModel);
            model.addObject(ArcConstants.RESULT, ArcConstants.AUTHENTICATION_SUCCESS);
            model.addObject(ArcConstants.USER, user);
            model.setViewName(ArcConstants.LOGIN_SUCCESS);
            
        } else {
            /**
             * Error while logging in.
             */
            error = (GidError) rmodel;
            System.out.println(ArcConstants.AUTHENTICATION_ERROR + error.toString());
            model.addObject(ArcConstants.ERROR, error.toString());
            model.setViewName(ArcConstants.LOGIN_VIEW);
        }
        return model;
    }
    
    /**
     * This method will show the profile information of the user.
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_PROFILE)
    public ModelAndView getProfileDetails(HttpServletRequest request) {
        GetAuthModel authModel = new GetAuthModel();
        authModel = (GetAuthModel) request.getSession().getAttribute(ArcConstants.AUTHMODEL);
        Map<String,Object> userDetails = new LinkedHashMap<String,Object>();
        try {
            userDetails=loginService.getUserDetails(authModel.getAccess_token());
        } catch (ApiClientException e) {
            e.printStackTrace();
        }
      
        /**
         * Set view related objects
         */
        ModelAndView model = new ModelAndView();
        model.addObject(ArcConstants.USER_DETAILS, userDetails);
        model.setViewName(ArcConstants.PROFILE_VIEW);
        return model;
    }
}
