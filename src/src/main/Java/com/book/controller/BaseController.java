package com.book.controller;

import com.book.entity.Page;
import com.book.util.Logger;
import com.book.util.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    public PageData getPageData() {return new PageData(this.getRequest());}

    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public Page getPage() {return new Page();}

    public static void logBefore(Logger logger, String interfaceName) {
        logger.info("");
        logger.info("start");
        logger.info("interfaceName");
    }

    public static void logAgter(Logger logger) {
        logger.info("end");
        logger.info("");
    }
}
