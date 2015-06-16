package com.rakuten.idc.arc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.rakuten.gid.services.rest.client.ApiClient;
import com.rakuten.gid.services.rest.client.ApiManager;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.AuthV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.AddressModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.CardModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GlobalIdModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.ProfileModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.Address;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.MemberModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    public boolean authenticate(User user) {
        return authenticateV1_2(user);
        /*
         * for (User u : userServices.getList()) { if
         * (u.getUserName().equals(user.getUserName()) &&
         * u.getPassword().equals(user.getPassword())) return true; } return
         * false;
         */
    }

    public boolean authenticateV1_2(User user) {
        String grantType = "password";
        String username = user.getUserName();// "testcustomprofile2";
        String password = user.getPassword();// "test_123";
        String authenticationToken = null;
        GidError error = null;
        GetAuthModel authModelPassword = null;
        AuthV1_2 auth = new AuthV1_2();

        try {
            Class.forName(ArcConstants.REGISTER_API_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        auth.setGrant_type(grantType);
        auth.setUsername(username);
        auth.setPassword(password);
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, auth,
                authenticationToken);
        ResponseModel model = (ResponseModel) apiClient.getFirst();

        if (model.getResponseCode() == 200) {
            authModelPassword = (GetAuthModel) model;
            authenticationToken = authModelPassword.getAccess_token();
            user.setPasswordAuthenticationToken(authenticationToken);
            System.out.println("password token : " + authenticationToken);
            return true;
        } else {
            error = (GidError) model;
            System.out.println(ArcConstants.AUTHENTICATION_ERROR
                    + error.toString());
            return false;
        }

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
        System.out.println(properties.get(ArcConstants.VIEW_PREFIX));
        System.out.println(properties.get(ArcConstants.VIEW_SUFFIX));
        System.out.println(properties.get(ArcConstants.TEMPLATE_LOADER_PATH));
    }

    @Override
    public Map<String, Object> getUserDetails(String passwordAuthenticationToken) {
        System.out.println("Entering the getUserDetails");
        Map<String, Object> userDetails = new HashMap<String, Object>();
        GidError error = null;
        MemberModel memberModel = null;

        MemberV1_2 member = new MemberV1_2();
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                passwordAuthenticationToken);
        ResponseModel model = (ResponseModel) apiClient.getFirst();
        if (model.getResponseCode() == 200) {
            memberModel = (MemberModel) model;
            userDetails.put("PROFILE_MODEL", createProfileMap(memberModel.getProfile()));
            userDetails.put("CARD_MODEL", createCardModelMap(memberModel.getCard()));
            userDetails.put("ADDRESS_MODEL", createAddressMap(memberModel.getAddress()));
            userDetails.put("GLOBAL_ID_MODEL", createGlobalIdMap(memberModel.getId()));
 //         userDetails.put("MEMBER_MODEL", memberModel.toString());
            
            System.out.println("MEMBER_MODEL : "+ memberModel.toString());
            System.out.println("User Details : "+ userDetails);
        } else {
            error = (GidError) model;
            userDetails.put("ERROR", error);
            System.out.println("Error happened: " + error.toString());
        }
        System.out.println("Exiting the getUserDetails");
        return userDetails;
    }

    private Object createGlobalIdMap(GlobalIdModel id) {
        Map<String,Object> globalIdMap = new LinkedHashMap<String,Object>();
        globalIdMap.put("client_member_id", id.getClient_member_id());
        globalIdMap.put("member_id", id.getMember_id());
        return globalIdMap;
    }

    private Map<String, Object> createAddressMap(List<AddressModel> address) {
       Map<String, Object> addresMap = new LinkedHashMap<String, Object>();
       if(null!=address){
           for(int i=0;i<address.size();i++){
               addresMap.put("AddressModel:", i+1);
               addresMap.put("id", address.get(i).getId());
               addresMap.put("security_parameters", address.get(i).getSecurity_parameters());
               Address[] addresses = address.get(i).getAddresses();
               if(null!=addresses){
                   for(int j=0;j<addresses.length;j++){
                       addresMap.put("Address : ", +j+1);
                       addresMap.put("first_name"+j, addresses[j].getFirst_name());
                       addresMap.put("first_name_kana"+j, addresses[j].getFirst_name_kana());
                       addresMap.put("middle_name"+j, addresses[j].getMiddle_name());
                       addresMap.put("last_name"+j, addresses[j].getLast_name());
                       addresMap.put("last_name_kana"+j, addresses[j].getLast_name_kana());
                       addresMap.put("company_name"+j, addresses[j].getCompany_name());
                       addresMap.put("company_type"+j, addresses[j].getCompany_type());
                       addresMap.put("locality"+j, addresses[j].getLocality());
                       addresMap.put("street_address1"+j, addresses[j].getStreet_address1());
                       addresMap.put("street_address2"+j, addresses[j].getStreet_address2());
                       addresMap.put("region_name"+j, addresses[j].getRegion_name());
                       addresMap.put("region_cd"+j, addresses[j].getRegion_cd());
                       addresMap.put("country_name"+j, addresses[j].getCountry_name());
                       addresMap.put("country_cd"+j, addresses[j].getCountry_cd());
                       addresMap.put("postal_cd"+j, addresses[j].getPostal_cd());
                       addresMap.put("phone"+j, addresses[j].getPhone());
                       addresMap.put("home_address"+j, addresses[j].getHome_address());
                       addresMap.put("default_delivery_address"+j, addresses[j].getDefault_delivery_address());
                       addresMap.put("updt_system_id"+j, addresses[j].getUpdt_system_id());
                       addresMap.put("updated_datetime"+j, addresses[j].getUpdated_datetime());
                       addresMap.put("registered_datetime"+j, addresses[j].getRegistered_datetime());
                       addresMap.put("reg_system_id"+j, addresses[j].getReg_system_id());
                       addresMap.put("user_memo"+j, addresses[j].getUser_memo());
                   }
               }
           }
       }
        return addresMap;
    }

    private  Map<String, Object>  createCardModelMap(List<CardModel> cardModels) {
        Map<String, Object> cardModelMap = new LinkedHashMap<String, Object>();
        if(null!=cardModels){
            for(int i=0;i<cardModels.size();i++){
                cardModelMap.put("Card model :", i+1);
                cardModelMap.put("card_holder_name_"+i, cardModels.get(i).getCard_holder_name());
                cardModelMap.put("registered_datetime_"+i, cardModels.get(i).getRegistered_datetime());
                cardModelMap.put("is_primary_"+i, cardModels.get(i).getIs_primary());
                cardModelMap.put("billing_address_id_"+i, cardModels.get(i).getBilling_address_id());
                cardModelMap.put("status_"+i, cardModels.get(i).getStatus());
                cardModelMap.put("expiration_month_"+i, cardModels.get(i).getExpiration_month());
                cardModelMap.put("expiration_year_"+i, cardModels.get(i).getExpiration_year());
                cardModelMap.put("iin_"+i, cardModels.get(i).getIin());
                cardModelMap.put("brand_cd_"+i, cardModels.get(i).getBrand_cd());
                cardModelMap.put("country_name_"+i, cardModels.get(i).getCountry_name());
                cardModelMap.put("country_cd_"+i, cardModels.get(i).getCountry_cd());
                cardModelMap.put("card_token_"+i, cardModels.get(i).getCard_token());
                cardModelMap.put("masked_card_number_"+i, cardModels.get(i).getMasked_card_number());
                cardModelMap.put("updated_datetime_"+i, cardModels.get(i).getUpdated_datetime());
                cardModelMap.put("last4digits_"+i, cardModels.get(i).getLast4digits());
            }
        }
        return cardModelMap;
    }

    private Map<String,Object> createProfileMap(ProfileModel profile) {
      
        Map<String,Object> profileMap = new LinkedHashMap<String, Object>();
        profileMap.put("first_name", profile.getFirst_name());
        profileMap.put("first_name_kana", profile.getFirst_name_kana());
        profileMap.put("middle_name", profile.getMiddle_name());
        profileMap.put("last_name", profile.getLast_name());
        profileMap.put("last_name_kana", profile.getLast_name_kana());
        profileMap.put("nickname", profile.getNickname());
        profileMap.put("mobile_phone", profile.getMobile_phone());
        profileMap.put("email", profile.getEmail());
        profileMap.put("gender", profile.getGender());
        profileMap.put("dob", profile.getDob());
        profileMap.put("dob_dd", profile.getDob_dd());
        profileMap.put("dob_mm", profile.getDob_mm());
        profileMap.put("dob_yyyy", profile.getDob_yyyy());
        profileMap.put("updated_datetime", profile.getUpdated_datetime());
        profileMap.put("updt_system_id", profile.getUpdt_system_id());
        profileMap.put("registered_datetime", profile.getRegistered_datetime());
        
        return profileMap;
    }
}
