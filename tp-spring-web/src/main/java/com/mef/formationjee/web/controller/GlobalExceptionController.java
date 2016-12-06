package com.mef.formationjee.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mef.formationjee.core.exception.FormationGenericException;


@ControllerAdvice
public class GlobalExceptionController {

	final static Logger logger = Logger.getLogger(GlobalExceptionController.class);
    
	@ExceptionHandler(FormationGenericException.class)
	public ModelAndView handleCustomException(FormationGenericException ex) {
	
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getErrCode());
	
		return model;
	
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
	
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", "error.msgTechnique");
	
		return model;
	
	}
}
