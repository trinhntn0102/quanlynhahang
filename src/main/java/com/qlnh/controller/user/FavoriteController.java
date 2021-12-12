package com.qlnh.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlnh.constant.ConstUrl;
import com.qlnh.dao.FavoriteDAO;
import com.qlnh.dao.ProductDAO;
import com.qlnh.entity.Account;
import com.qlnh.entity.Favorite;
import com.qlnh.entity.Order;
import com.qlnh.entity.Product;
import com.qlnh.service.AccountService;
import com.qlnh.service.SessionService;

@Controller
public class FavoriteController {
	@Autowired
	FavoriteDAO fdao;
	@Autowired
	ProductDAO pdao;
	@Autowired
	AccountService accountService;
	@RequestMapping(value = { ConstUrl.WISHLIST + "/{id}"})
	public ModelAndView wishlist(@PathVariable("id") String username, @RequestParam("page")Optional<Integer> page) {
		ModelAndView mav = new ModelAndView("restaurant/wishlist");
		List<Favorite> list = fdao.findByUsername(username);
		List<Favorite> list2 = fdao.findAll();
		System.out.println(list);
		mav.addObject("products", list2);
		return mav;
	}
	
	@RequestMapping(value = { ConstUrl.DELFAV + "/{id}"})
	public ModelAndView delfav(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView("/");
		fdao.deleteById(id);
		List<Favorite> list2 = fdao.findAll();
		mav.addObject("products", list2);
		return mav;
		//return "redirect:/favorite/Anh02";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = { ConstUrl.ADDFAV + "/{id}"})
	public ModelAndView addfav( @PathVariable("id") Integer id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/");
		String a = request.getRemoteUser().toString();
		System.out.println(id+"\n"+request.getRemoteUser().toString());
		try {
			fdao.insert(id,a);
		} catch (ArithmeticException e) {
		} finally {
			List<Favorite> list2 = fdao.findAll();
			mav.addObject("products", list2);
			return mav;
		}
	}
}
