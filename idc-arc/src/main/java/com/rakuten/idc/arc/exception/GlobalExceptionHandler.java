package com.rakuten.idc.arc.exception;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.idc.arc.constants.ArcConstants;

@ControllerAdvice
public class GlobalExceptionHandler{

    Logger logger = LoggerFactory.getLogger(getClass());
    private String errorMessage ;
    private String localizedMessage ;
    private HttpStatus errorCode ;
    
    @ExceptionHandler(CustomApiClientException.class)
    public ModelAndView handleCustomApiClientException(Exception ex){
        logger.error("CustomApiClientException Happened in GlobalExceptionHandler !",ex);
        errorMessage = ex.getMessage();
        localizedMessage=ex.getLocalizedMessage();
        errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        return setView();
    }
    
    @ExceptionHandler(ApiClientException.class)
    public ModelAndView handleApiClientException(Exception ex){
        logger.error("ApiClientException Happened in GlobalExceptionHandler !",ex);
        errorMessage = ex.getMessage();
        localizedMessage=ex.getLocalizedMessage();
        errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        return setView();
    }
    
    private ModelAndView setView(){
        ModelAndView mav = new ModelAndView(ArcConstants.ERROR_VIEW);
        mav.addObject("errorMessage", errorMessage);
        mav.addObject("localizedMessage", localizedMessage);
        mav.addObject("errorCode", errorCode);
        return mav;
    }
}
