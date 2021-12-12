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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.qlnh.constant.ConstUrl;
import com.qlnh.dao.OrderDetailDAO;
import com.qlnh.dao.ProductDAO;
import com.qlnh.dto.Report;
import com.qlnh.entity.Category;
import com.qlnh.entity.Product;
import com.qlnh.service.CategoryService;
import com.qlnh.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OrderDetailDAO orderdetailDao;
	@Autowired
	ProductDAO pdao;
	
	@RequestMapping(value= {ConstUrl.PRODUCT})
	public ModelAndView product(@RequestParam("page")Optional<Integer> page){
		Pageable pageable1 = PageRequest.of(0, 7);
		ModelAndView mav = new ModelAndView("restaurant/product");
		Pageable pageable =  PageRequest.of(page.orElse(0),12);
		Page<Product> list = productService.pageAll(pageable);
		mav.addObject("products", list);
		mav.addObject("currentPage", page);
		mav.addObject("totalPage", list.getTotalPages());
		//
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
	
	@RequestMapping(value= {ConstUrl.PRODUCT +"/{productName}"})
	public ModelAndView search(@RequestParam("productName") Optional<String> name, @RequestParam("page")Optional<Integer> page) {
		Pageable pageable1 = PageRequest.of(0, 7);
		ModelAndView mav = new ModelAndView("restaurant/product");
		Pageable pageable =  PageRequest.of(page.orElse(0),12);
		//
		List<Category> categoryList = categoryService.findAll();
		mav.addObject("categories", categoryList);
		// best seller
		List<Report> report1 = orderdetailDao.findAllByASC(pageable1);
		Collections.sort(report1, (p1, p2) -> -p1.getQty().compareTo(p2.getQty()));
		mav.addObject("bestSeller", report1);
		//
		List<Product> product3 = pdao.findByCategory3(pageable1);
		mav.addObject("product3", product3);
		if (name.isPresent()) {
			Page<Product> list = productService.findByName(pageable, "%"+name.get()+"%");
			mav.addObject("products", list);
			mav.addObject("currentPage", page);
			mav.addObject("totalPage", list.getTotalPages());
		} else {
			Page<Product> list = productService.pageAll(pageable);
			mav.addObject("products", list);
			mav.addObject("currentPage", page);
			mav.addObject("totalPage", list.getTotalPages());
		}
		return mav;
	}

	@RequestMapping("/productdetail/{id}")
	public String productDetails(Model model, @PathVariable("id") Integer id) {
		Pageable pageable1 = PageRequest.of(0, 7);
		
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		// best seller
		List<Report> report1 = orderdetailDao.findAllByASC(pageable1);
		Collections.sort(report1, (p1, p2) -> -p1.getQty().compareTo(p2.getQty()));
		model.addAttribute("bestSeller", report1);
		//
		List<Product> product3 = pdao.findByCategory4(pageable1);
		model.addAttribute("product3", product3);
		return "restaurant/detail";
	}
	

}
