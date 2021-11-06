package com.qlnh.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qlnh.constant.ConstUrl;
import com.qlnh.entity.Category;
import com.qlnh.entity.CategoryType;
import com.qlnh.entity.Product;
import com.qlnh.service.CategoryService;
import com.qlnh.service.CategoryTypeService;
import com.qlnh.service.ProductService;

@Controller
@RequestMapping(value= {ConstUrl.ROOT, ConstUrl.RESTAURANT + ConstUrl.CATEGORY})
public class CategoryController {
	@Autowired
	CategoryTypeService categoryTypeService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/{id}") //path variable
	public ModelAndView category(@PathVariable("id") Integer id){
		ModelAndView mav = new ModelAndView("restaurant/product");
		List<CategoryType> categoryTypeList = categoryTypeService.findByCategoryId(id);
		mav.addObject("categoryTypeList", categoryTypeList);
		
		//tam
		List<Product> productList = productService.findAll();
		mav.addObject("products", productList);
		List<Category> categoryList = categoryService.findAll();
		mav.addObject("categories", categoryList);
		return mav;
	}
}
