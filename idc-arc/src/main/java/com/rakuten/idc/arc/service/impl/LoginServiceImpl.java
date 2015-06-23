package com.rakuten.idc.arc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.MemberModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.constants.Util;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    public ResponseModel authenticate(User user)throws ApiClientException{
        return authenticateV1_2(user);
        /*
         * for (User u : userServices.getList()) { if
         * (u.getUserName().equals(user.getUserName()) &&
         * u.getPassword().equals(user.getPassword())) return true; } return
         * false;
         */
    }

    public ResponseModel authenticateV1_2(User user) throws ApiClientException{
        ResponseModel model = userServices.getUserAuthentication(user);
        return model;
    }

    /**
     * This is just a method written for reference to know how to read from
     * property files.
     */
    @SuppressWarnings("unused")
    private void readingFromPropertyFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(
                "/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger.debug(properties.get(ArcConstants.VIEW_PREFIX).toString());
        logger.debug(properties.get(ArcConstants.VIEW_SUFFIX).toString());
        logger.debug(properties.get(ArcConstants.TEMPLATE_LOADER_PATH).toString());
    }

    /**
     * Getting the user details of the authenticated user.
     */
    @Override
    public Map<String, Object> getUserDetails(String passwordAuthenticationToken)throws ApiClientException {
        logger.debug("Entering the getUserDetails..");
        Map<String, Object> userDetails = new HashMap<String, Object>();
        ResponseModel model = userServices.getUserDetails(passwordAuthenticationToken);
        
        if (model.getResponseCode() == 200) {
            userDetails=Util.displayMemberDetails((MemberModel) model);
        } else {
            userDetails.put(ArcConstants.ERROR, model.toString());
            logger.error("Error happened: " + model.toString());
        }
        logger.debug("Exiting the getUserDetails...");
        return userDetails;
    }


}
