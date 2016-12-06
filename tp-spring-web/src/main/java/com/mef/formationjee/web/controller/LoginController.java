package com.mef.formationjee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(final ModelMap pModel, 
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		if (error != null) {
			pModel.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			pModel.addAttribute("logout", "You've been logged out successfully.");
		}

		return "login";

	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		
		return "deniedpage";
	}
}