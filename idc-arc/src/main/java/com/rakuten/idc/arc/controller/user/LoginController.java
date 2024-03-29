package com.rakuten.idc.arc.controller.user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.CustomProfileV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;
import com.rakuten.idc.arc.service.UserService;

@Controller
@ConfigurationProperties(prefix = ArcConstants.MY_SITE)
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private LoginService loginService;
    private UserService userService;

    @Inject
    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
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
     * @throws ApiClientException 
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_AUTHENTICATE)
    public ModelAndView authenticate(HttpServletRequest request) throws ApiClientException {
        
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
        rmodel = loginService.authenticate(user);
      
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
            logger.debug("Login Successful !");
        } else {
            /**
             * Error while logging in.
             */
            error = (GidError) rmodel;
            model.addObject(ArcConstants.ERROR, error.toString());
            model.setViewName(ArcConstants.LOGIN_VIEW);
            logger.debug("Authentication Error ! "+error.toString());
        }
        return model;
    }
    
    /**
     * This method will show the profile information of the user.
     * @throws ApiClientException 
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_PROFILE)
    public ModelAndView getProfileDetails(HttpServletRequest request) throws ApiClientException {
        GetAuthModel authModel = new GetAuthModel();
        authModel = (GetAuthModel) request.getSession().getAttribute(ArcConstants.AUTHMODEL);
        Map<String,Object> userDetails = new LinkedHashMap<String,Object>();
            userDetails=loginService.getUserDetails(authModel.getAccess_token());
     
        /**
         * Set view related objects
         */
        ModelAndView model = new ModelAndView();
        model.addObject(ArcConstants.USER_DETAILS, userDetails);
        model.setViewName(ArcConstants.PROFILE_VIEW);
        return model;
    }
    
    /**
     * This method is just to handle the login view.
     * This will set the login page.
     * @param request
     * @return
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_CUSTOM_PROFILE)
    public ModelAndView getLoadCustomProfile(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.CUSTOM_PROFILE_VIEW);
        return model;
    }

    
    /**
     * This method will save the custom profile information of the user.
     * @throws ApiClientException 
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_SAVE_CUSTOM_PROFILE)
    public ModelAndView setCustomProfile(HttpServletRequest request) throws ApiClientException {
        GetAuthModel authModel = new GetAuthModel();
        ResponseModel rmodel = null;
        authModel = (GetAuthModel) request.getSession().getAttribute(ArcConstants.AUTHMODEL);
        
        
        CustomProfileV1_2 profile = createCustomProfile(request);
        rmodel = userService.addCustomProfile(profile,authModel.getAccess_token());
        
        System.out.println(rmodel.toString());
        
        Map<String,Object> userDetails = new LinkedHashMap<String,Object>();
        userDetails=loginService.getUserDetails(authModel.getAccess_token());
     
        /**
         * Set view related objects
         */
        ModelAndView model = new ModelAndView();
        model.addObject(ArcConstants.USER_DETAILS, userDetails);
        model.setViewName(ArcConstants.PROFILE_VIEW);
        return model;
    }
    
    
   private CustomProfileV1_2 createCustomProfile(HttpServletRequest request) {
        
        CustomProfileV1_2 profile = new CustomProfileV1_2();
        Map<String, String> customMap = new HashMap<String,String>();
        customMap.put("0", request.getParameter("aboutme"));
        customMap.put("1", request.getParameter("hobby"));
        customMap.put("2", request.getParameter("movies"));
        customMap.put("3", request.getParameter("book"));
        profile.setCpMap(customMap);
        return profile;
    }

    
}
