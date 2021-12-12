package com.qlnh.controller.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qlnh.constant.ConstUrl;
import com.qlnh.dao.OrderDetailDAO;
import com.qlnh.dao.ProductDAO;
import com.qlnh.dto.Report;
import com.qlnh.entity.Category;
import com.qlnh.entity.CategoryType;
import com.qlnh.entity.Product;
import com.qlnh.service.CategoryService;
import com.qlnh.service.CategoryTypeService;
import com.qlnh.service.ProductService;

@Controller
@RequestMapping(value= {ConstUrl.CATEGORY})
public class CategoryController {
	@Autowired
	CategoryTypeService categoryTypeService;
	
	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailDAO orderdetailDao;
	@Autowired
	ProductDAO pdao;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/{id}") //path variable
	public ModelAndView category(@PathVariable("id") Integer id, @RequestParam("page")Optional<Integer> page){
		ModelAndView mav = new ModelAndView("restaurant/product");
		Pageable pageable1 = PageRequest.of(0, 7);
		
		Pageable pageable =  PageRequest.of(page.orElse(0),12);
		Page<Product> list = productService.pageCateAll(pageable, id);
		mav.addObject("products", list);
		mav.addObject("currentPage", page);
		mav.addObject("totalPage", list.getTotalPages());
		
		List<Category> categoryList = categoryService.findAll();
		mav.addObject("categories", categoryList);
		// best seller
		List<Report> report1 = orderdetailDao.findAllByASC(pageable1);
		Collections.sort(report1, (p1, p2) -> -p1.getQty().compareTo(p2.getQty()));
		mav.addObject("bestSeller", report1);
		//
		List<Product> product3 = pdao.findByCategory3(pageable1);
		mav.addObject("product3", product3);
		return mav;
	}
}
