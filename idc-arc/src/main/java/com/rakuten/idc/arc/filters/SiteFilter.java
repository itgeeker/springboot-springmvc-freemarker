package com.rakuten.idc.arc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.helper.ActionHelper;

/**
 * SiteFilter process all the request and based on site_id on request it returns
 * the path for headers, footers and CSS for the ARC freemarker files.
 */
@Component
public class SiteFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String siteName = ActionHelper.getSiteFromRequest(request);
        request.setAttribute(ArcConstants.SITE_NAME, siteName);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
