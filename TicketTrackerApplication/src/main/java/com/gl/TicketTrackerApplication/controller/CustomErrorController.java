package com.gl.TicketTrackerApplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class CustomErrorController  implements ErrorController {
	
	@ResponseBody
	@GetMapping("/error")
	String error(HttpServletRequest request) {
		return "<h1>Page not found Error</h1>";
	}
	
	public String getErrorPath() {
		return "/error";
	}

}
