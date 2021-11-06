package com.qlnh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qlnh.constant.ConstUrl;

@Controller
@RequestMapping(value= {ConstUrl.ROOT, ConstUrl.RESTAURANT})
public class HomeController {

	@GetMapping
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("restaurant/home");
		
		//cho nay doi duong dan thanh /restaurant cho dep
		return mav;
	}
	
	@RequestMapping(value= {ConstUrl.LOGIN})
	public String login(){
		return "restaurant/login";
	}
	
	@RequestMapping(value= {ConstUrl.REGISTER})
	public String register(){
		return "restaurant/register";
	}
}
