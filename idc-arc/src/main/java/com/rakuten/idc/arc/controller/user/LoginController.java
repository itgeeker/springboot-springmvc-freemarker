package com.rakuten.idc.arc.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.AuthV1_2;
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

    @RequestMapping(ArcConstants.REQUEST_MAPPING_LOGIN)
    public ModelAndView getListUsersView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.LOGIN_VIEW);
        return model;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_AUTHENTICATE)
    public ModelAndView authenticate(HttpServletRequest request) {
        
        //remove old session
        HttpSession session = request.getSession(true);
        if(session.getAttribute(ArcConstants.AUTHMODEL_PASSWORD) != null){
            session.invalidate();
        }
        ModelAndView model = new ModelAndView();
        
        User user = new User();
        user.setUserName(request.getParameter(ArcConstants.USERNAME));
        user.setPassword(request.getParameter(ArcConstants.PASSWORD));
        
        ResponseModel rmodel = null;
        
        try{
            rmodel = loginService.authenticate(user);
        }catch(Exception e){
            System.out.println("exception in authentication : " + e);           
            model.addObject(ArcConstants.ERROR, ArcConstants.AUTHENTICATION_ERROR);
            //set view to a success page
            model.setViewName(ArcConstants.LOGIN_VIEW);
        }
        
        GetAuthModel authModel = null;
        GidError error = null;
        if (rmodel.getResponseCode() == 200) {
            authModel = (GetAuthModel) rmodel;    

            //set authmodel in session
            session.setAttribute(ArcConstants.AUTHMODEL_PASSWORD, authModel);
            //add success message
            model.addObject(ArcConstants.RESULT_SUCCESS,
                    ArcConstants.AUTHENTICATION_SUCCESS);
            model.addObject(ArcConstants.AUTHMODEL_PASSWORD, authModel);
            model.addObject(ArcConstants.USER, user);
            //set view to a success page
            model.setViewName(ArcConstants.LOGIN_SUCCESS);
            
            
        } else {
            error = (GidError) rmodel;
            System.out.println(ArcConstants.AUTHENTICATION_ERROR + error.toString());
            //add success message
            model.addObject(ArcConstants.ERROR, error.toString());
            model.addObject(ArcConstants.ERROR, ArcConstants.AUTHENTICATION_ERROR);
            //set view to a success page
            model.setViewName(ArcConstants.LOGIN_VIEW);
            
        }
        
        return model;
    }
}
