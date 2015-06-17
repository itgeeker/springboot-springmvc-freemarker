package com.rakuten.idc.arc.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateAddressModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateCardModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateProfileModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.CreateMemberModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Inject
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_SIGNUP)
    public ModelAndView loadSignup(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.SIGNUP_VIEW);
        model.addObject(ArcConstants.MESSAGE, ArcConstants.SINGUP_MESSAGE);
        return model;
    }

    @RequestMapping(ArcConstants.REQUEST_MAPPING_DO_SIGNUP)
    public ModelAndView doSignup(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        GidError error = null;
        String authToken;
        ResponseModel responseModel = userService.getClientAuthention();

        if (responseModel.getResponseCode() == 200) {
            GetAuthModel authModel = (GetAuthModel) responseModel;
            authToken = authModel.getAccess_token();
            System.out.println("AuthToken : " + authToken);

        } else {
            error = (GidError) responseModel;
            model.setViewName(ArcConstants.SIGNUP_VIEW);
            model.addObject(ArcConstants.ERROR, error.getError());
            return model; // If any Authentication Error, then return to view.
        }

        MemberV1_2 member = createMember(request);
        responseModel = userService.createUser(member, authToken);

        CreateMemberModel createMemberModel = null;
        if (responseModel.getResponseCode() == 200) {
            // everything is all right
            createMemberModel = (CreateMemberModel) responseModel;
            model.setViewName(ArcConstants.SUCCESS_VIEW);
            model.addObject(ArcConstants.CREATE_MEMBER_MODEL, createMemberModel);
        } else {
            // Something went wrong !!
            error = (GidError) responseModel;
            model.setViewName(ArcConstants.SIGNUP_VIEW);
            model.addObject(ArcConstants.ERROR, error.toString());
        }
        return model;
    }

    /**
     * Private method to create a member out of request parameters.
     * 
     * @param request
     * @return
     */
    private MemberV1_2 createMember(HttpServletRequest request) {

        String userName = request.getParameter("username");
        System.out.println("UserName :" + userName);
        String password = request.getParameter("password");
        System.out.println("password :" + password);
        String pr_email = request.getParameter("pr_email");
        System.out.println("pr_email :" + pr_email);
        String pr_first_name = request.getParameter("pr_first_name");
        System.out.println("pr_first_name :" + pr_first_name);
        String pr_last_name = request.getParameter("pr_last_name");
        System.out.println("pr_last_name :" + pr_last_name);
        String ad_first_name = request.getParameter("ad_first_name");
        System.out.println("ad_first_name :" + ad_first_name);
        String ad_last_name = request.getParameter("ad_last_name");
        System.out.println("ad_last_name :" + ad_last_name);
        String ad_street_address1 = request.getParameter("ad_street_address1");
        System.out.println("ad_street_address1 :" + ad_street_address1);
        String ad_street_address2 = request.getParameter("ad_street_address2");
        System.out.println("ad_street_address2 :" + ad_street_address2);
        String ad_country_cd = request.getParameter("ad_country_cd");
        System.out.println("ad_country_cd :" + ad_country_cd);
        String ad_region_cd = request.getParameter("ad_region_cd");
        System.out.println("ad_region_cd :" + ad_region_cd);
        String ad_postal_cd = request.getParameter("ad_postal_cd");
        System.out.println("ad_postal_cd :" + ad_postal_cd);

        MemberV1_2 member = new MemberV1_2();
        member = new MemberV1_2();
        member.setUsername(userName);
        member.setPassword(password);
        member.setIpaddress("10.13.72.62");
        member.setReg_route("myrakuten");

        CreateProfileModel profile = new CreateProfileModel();
        profile.setPr_email(pr_email);
        profile.setPr_first_name(pr_first_name);
        profile.setPr_last_name(pr_last_name);
        member.setProfile(profile);

        CreateAddressModel address = new CreateAddressModel();
        address.setAd_country_cd(ad_country_cd);
        address.setAd_last_name(ad_last_name);
        address.setAd_first_name(ad_first_name);
        address.setAd_postal_cd(ad_postal_cd);
        address.setAd_region_cd(ad_region_cd);
        address.setAd_street_address1(ad_street_address1);
        address.setAd_street_address2(ad_street_address2);
        member.setAddress(address);

        CreateCardModel card = null;
        member.setCard(card);

        return member;
    }
}
