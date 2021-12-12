package com.qlnh.controller.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qlnh.dao.OrderDetailDAO;
import com.qlnh.dao.ProductDAO;
import com.qlnh.dto.Report;
import com.qlnh.entity.Role;
import com.qlnh.entity.Account;
import com.qlnh.service.ParamService;
import com.qlnh.constant.ConstUrl;
import com.qlnh.entity.*;
import com.qlnh.entity.Category;
import com.qlnh.entity.Product;
import com.qlnh.service.*;
import com.qlnh.service.ProductService;

@Controller
//@RequestMapping(value = { ConstUrl.ROOT, ConstUrl.RESTAURANT })
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailDAO orderdetailDao;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductDAO pdao;
	@Autowired
	ParamService paramService;
	@Autowired
	AccountService accountService;

	@RequestMapping(value = { ConstUrl.HOME })
	public ModelAndView home() {
		Pageable pageable = PageRequest.of(0, 8);
		ModelAndView mav = new ModelAndView("restaurant/index");
		List<Product> productList = productService.findAll();
		mav.addObject("products", productList);
		List<Category> categoryList = categoryService.findAll();
		mav.addObject("categories", categoryList);
		// best seller
		List<Report> report1 = orderdetailDao.findAllByASC(pageable);
		Collections.sort(report1, (p1, p2) -> -p1.getQty().compareTo(p2.getQty()));
		mav.addObject("bestSeller", report1);
		//
		List<Product> product1 = pdao.findByCategory1(pageable);
		mav.addObject("product1", product1);
		List<Product> product2 = pdao.findByCategory2(pageable);
		mav.addObject("product2", product2);
		List<Product> product3 = pdao.findByCategory3(pageable);
		mav.addObject("product3", product3);
		List<Product> product4 = pdao.findByCategory4(pageable);
		mav.addObject("product4", product4);
		List<Product> product5 = pdao.findByCategory5(pageable);
		mav.addObject("product5", product5);
		return mav;
	}



	@RequestMapping(value = { ConstUrl.ABOUT })
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("restaurant/about-us");
		return mav;
	}

	@RequestMapping(value = { ConstUrl.CART })
	public ModelAndView cart() {
		ModelAndView mav = new ModelAndView("restaurant/cart");
		return mav;
	}

	@RequestMapping(value = { ConstUrl.CHECKOUT })
	public ModelAndView checkout() {
		ModelAndView mav = new ModelAndView("restaurant/checkout");
		return mav;
	}

	@RequestMapping(value = { ConstUrl.CONTACT })
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("restaurant/contact-us");
		return mav;
	}

	@RequestMapping(value = { ConstUrl.REGISTER })
	public String register() {
		return "restaurant/register";
	}

//
//	@RequestMapping(value = { ConstUrl.CHANGE_PASSWORD })
//	public String changePassword() {
//		return "restaurant/changepass";
//	}
//	
	@RequestMapping(value = { ConstUrl.ACCOUNT })
	public String account() {
		return "restaurant/profile";
	}
	
	@RequestMapping("account/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "restaurant/login";
	}

}
