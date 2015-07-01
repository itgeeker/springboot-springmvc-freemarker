package com.rakuten.idc.arc.constants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;

import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.Address;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.CardModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.ProfileModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.CreateMemberModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.MemberModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidResponseModel;
public class Util {
    
    /**
     * Private constructor for the Util Class to
     * make sure no one can initialize this.
     */
    private Util(){
    }

    /**
     * This method will be used to convert the MemberModel object to map
     * of values having <String,Object> to display in UI/view .
     * @param memberModel
     * @return
     */
    public static Map<String, Object> displayMemberDetails(MemberModel memberModel) {
        Map<String, Object> userDetails = new LinkedHashMap<String, Object>();
        userDetails.put(ArcConstants.PROFILE_MODEL, createProfileMap(memberModel.getProfile()));
        userDetails.put(ArcConstants.CARD_MODEL, createCardModelMap(memberModel.getCard()));
        userDetails.put(ArcConstants.ADDRESS_MODEL, createAddressMap(memberModel.getAddress()));
        userDetails.put(ArcConstants.GLOBAL_ID_MODEL, createGlobalIdMap(memberModel.getId()));
        userDetails.put(ArcConstants.CUSTOM_PROFILE, createCustomMap(memberModel.getCustom_profiles()));
        
        System.out.println("MEMBER_MODEL : "+ memberModel.toString());
        System.out.println("User Details : "+ userDetails);
        return userDetails;
    }

    private static Object createGlobalIdMap(GidResponseModel id) {
        Map<String,Object> globalIdMap = new LinkedHashMap<String,Object>();
        globalIdMap.put("client_member_id", id.getClient_member_id());
        globalIdMap.put("member_id", id.getMember_id());
        return globalIdMap;
    }
    
    
    

    private static Map<String, Object> createAddressMap(List<Address> address) {
       Map<String, Object> addresMap = new LinkedHashMap<String, Object>();
       if(null!=address){
           for(int i=0;i<address.size();i++){
               addresMap.put("Address:", i+1);
               addresMap.put("first_name"+i, address.get(i).getFirst_name());
               addresMap.put("first_name_kana"+i, address.get(i).getFirst_name_kana());
               addresMap.put("middle_name"+i, address.get(i).getMiddle_name());
               addresMap.put("last_name"+i, address.get(i).getLast_name());
               addresMap.put("last_name_kana"+i, address.get(i).getLast_name_kana());
               addresMap.put("company_name"+i, address.get(i).getCompany_name());
               addresMap.put("company_type"+i, address.get(i).getCompany_type());
               addresMap.put("locality"+i, address.get(i).getLocality());
               addresMap.put("street_address1"+i, address.get(i).getStreet_address1());
               addresMap.put("street_address2"+i, address.get(i).getStreet_address2());
               addresMap.put("region_name"+i, address.get(i).getRegion_name());
               addresMap.put("region_cd"+i, address.get(i).getRegion_cd());
               addresMap.put("country_name"+i, address.get(i).getCountry_name());
               addresMap.put("country_cd"+i, address.get(i).getCountry_cd());
               addresMap.put("postal_cd"+i, address.get(i).getPostal_cd());
               addresMap.put("phone"+i, address.get(i).getPhone());
               addresMap.put("home_address"+i, address.get(i).getHome_address());
               addresMap.put("default_delivery_address"+i, address.get(i).getDefault_delivery_address());
               addresMap.put("updt_system_id"+i, address.get(i).getUpdt_system_id());
               addresMap.put("updated_datetime"+i, address.get(i).getUpdated_datetime());
               addresMap.put("registered_datetime"+i, address.get(i).getRegistered_datetime());
               addresMap.put("reg_system_id"+i, address.get(i).getReg_system_id());
               addresMap.put("user_memo"+i, address.get(i).getUser_memo());
           }
       }
        return addresMap;
    }

    private static  Map<String, Object>  createCardModelMap(List<CardModel> cardModels) {
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

    private static Map<String,Object> createProfileMap(ProfileModel profile) {
      
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

    /**
     * This will take the CreateMemberModel and converts to map of <String,Object>
     * for the purpose of UI display.
     * @param responseModel
     * @return
     */
    public static Map<String, Object> displayCreateMemberModelDetails(CreateMemberModel responseModel) {
        Map<String, Object> userDetails = new LinkedHashMap<String, Object>();
        userDetails.put(ArcConstants.CREATE_MEMBER_MODEL, createMemberModelMap(responseModel));
        return userDetails;
    }

    private static Object createMemberModelMap(CreateMemberModel responseModel) {
        Map<String,Object> createMemberModel = new LinkedHashMap<String,Object>();
        createMemberModel.put("security_parameters", responseModel.getSecurity_parameters().toString());
        createMemberModel.put("refresh_expires_in", responseModel.getRefresh_expires_in());
        createMemberModel.put("expires_in", responseModel.getExpires_in());
        createMemberModel.put("token_type", responseModel.getToken_type());
        createMemberModel.put("refresh_token", responseModel.getRefresh_token());
        createMemberModel.put("access_token", responseModel.getAccess_token());
        createMemberModel.put("Address", responseModel.getAddress().getId());
        return createMemberModel;
    }
    
    private static Object createCustomMap(HashMap<String, String> cpMap) {
        Map<String,Object> customProfileMap = new LinkedHashMap<String,Object>();
       
        if(!MapUtils.isEmpty(cpMap)){
            Iterator<Entry<String, String>> it = cpMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> custom = (Map.Entry<String, String>)it.next();
                customProfileMap.put(custom.getKey(), custom.getValue());                
            }
            
        }        
        return customProfileMap;
    }
}
