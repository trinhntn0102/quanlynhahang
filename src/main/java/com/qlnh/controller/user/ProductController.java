package com.qlnh.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qlnh.constant.ConstUrl;
import com.qlnh.entity.Category;
import com.qlnh.entity.Product;
import com.qlnh.service.CategoryService;
import com.qlnh.service.ProductService;

@Controller
@RequestMapping(value= {ConstUrl.ROOT, ConstUrl.RESTAURANT + ConstUrl.PRODUCT})
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public ModelAndView product(){
		ModelAndView mav = new ModelAndView("restaurant/product");
		List<Product> productList = productService.findAll();
		mav.addObject("products", productList);
		List<Category> categoryList = categoryService.findAll();
		mav.addObject("categories", categoryList);
		return mav;
	}
	
//	@RequestMapping(value = "/{id}") //path variable
//	public ModelAndView detail(){
//		ModelAndView mav = new ModelAndView("restaurant/product");
//		return mav;
//	}
}
