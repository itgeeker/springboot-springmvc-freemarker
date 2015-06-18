package com.rakuten.idc.arc.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.CustomProfileV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateAddressModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateProfileModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.CreateMemberModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.constants.Util;
import com.rakuten.idc.arc.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Inject
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This is the get Request to get the /signup page.
     * @param request
     * @return
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_SIGNUP)
    public ModelAndView loadSignup(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(ArcConstants.SIGNUP_VIEW);
        model.addObject(ArcConstants.MESSAGE, ArcConstants.SINGUP_MESSAGE);
        return model;
    }

    /**
     * The request contains the user details.
     * User needs to be registered.
     * @param request
     * @return
     */
    @RequestMapping(ArcConstants.REQUEST_MAPPING_DO_SIGNUP)
    public ModelAndView doSignup(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        GidError error = null;
        String clientAuthToken ;
        ResponseModel responseModel = null;
        try {
            responseModel = userService.getClientAuthention();
        } catch (ApiClientException e) {
            e.printStackTrace();
        }

        if (responseModel.getResponseCode() == 200) {
            GetAuthModel authModel = (GetAuthModel) responseModel;
            clientAuthToken = authModel.getAccess_token();
            System.out.println("AuthToken : " + clientAuthToken);

        } else {
            error = (GidError) responseModel;
            model.setViewName(ArcConstants.SIGNUP_VIEW);
            model.addObject(ArcConstants.ERROR, error.getError());
            return model; // If any Authentication Error, then return to view.
        }

        /**
         * Client Authentication succeeded, 
         * Now we need to register the member.
         */
        MemberV1_2 member = createMember(request);
        try {
            responseModel = userService.createUser(member, clientAuthToken);
        } catch (ApiClientException e) {
            e.printStackTrace();
        }

        if (responseModel.getResponseCode() == 200) {
            model.setViewName(ArcConstants.PROFILE_VIEW);
            model.addObject(ArcConstants.USER_DETAILS, 
                    Util.displayCreateMemberModelDetails((CreateMemberModel)responseModel));
            /**
             * Setting the custom Profile for the user which is created.
             */
            CustomProfileV1_2 profile = createCustomProfile(request);
            try {
                responseModel= userService.addCustomProfile(profile,clientAuthToken);
            } catch (ApiClientException e) {
                e.printStackTrace();
            }
            if(responseModel.getResponseCode()==200){
                System.out.println("Custom Profile Added successfully !");
            }else{
                System.out.println("Error while adding Custom profile !" + profile.toString());
            }
        } else {
            // Something went wrong !!
            error = (GidError) responseModel;
            model.setViewName(ArcConstants.SIGNUP_VIEW);
            model.addObject(ArcConstants.ERROR, error.toString());
        }
        return model;
    }

    private CustomProfileV1_2 createCustomProfile(HttpServletRequest request) {
        
        CustomProfileV1_2 profile = new CustomProfileV1_2();
        Map<String, String> customMap = new HashMap<String,String>();
        customMap.put("0", request.getParameter("hobby"));
        customMap.put("1", request.getParameter("favorites"));
        profile.setCpMap(customMap);
        return profile;
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
//      address.setAd_street_address2(ad_street_address2);
        member.setAddress(address);

//        CreateCardModel card = null;
//        member.setCard(card);

        return member;
    }
}
