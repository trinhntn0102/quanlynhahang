package com.qlnh.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qlnh.dao.AccountDAO;
import com.qlnh.entity.Account;

@Controller
public class SecurityController {
	@Autowired
	AccountDAO accdao;
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "restaurant/login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "restaurant/login";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "restaurant/login";
	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất");
		return "restaurant/login";
	}

	@RequestMapping("security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "restaurant/login";
	}

	@RequestMapping("register/save")
	public String save(Model model, @ModelAttribute("acc") @Validated Account acc,
			@RequestParam("attach") MultipartFile attach) {
		String filename = attach.getOriginalFilename();
		acc.setPhoto(filename);
		accdao.save(acc);
		//sessionService.set("username", acc.getUsername());
		return "redirect:/restaurant/index";
	}

	@ModelAttribute("gender")
	public Map<Boolean, String> getGenders() {
		Map<Boolean, String> map = new HashMap<>();
		map.put(true, "Male");
		map.put(false, "Female");
		return map;
	}
}
