package com.rakuten.idc.arc.helper;

import javax.servlet.http.HttpServletRequest;

import com.rakuten.idc.arc.constants.ArcConstants;

public class ActionHelper {
 
	/**
	 * This method gets the site name for the current request
	 * @param request
	 * @return
	 */
	public static String getSiteFromRequest(HttpServletRequest request){
		String siteName = null;
		if(request.getParameter(ArcConstants.SITE) != null){
			siteName = request.getParameter(ArcConstants.SITE);
		}else if(request.getAttribute(ArcConstants.SITE) != null){
			siteName = (String)request.getAttribute(ArcConstants.SITE);
		}else{
			siteName = ArcConstants.DEFAULT_SITE_NAME;
		}
		return siteName;
	}

}
