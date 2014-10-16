package com.crazy.singleweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;

/**
 * @Description:
 * @Author <a href="liujiawen@139130.net">Jiawen.Liu</a>
 * @Date 2014-10-15
 * @Version 1.0.0
 */
@Controller
public class MainController {

	private SingleService singleService;

	@RequestMapping(value = "/")
	public String rootPath() {
		return "/index";
	}

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request,
			@RequestParam(value = "name") String name) {
		User user = singleService.findUserByName(name);
		model.addAttribute("user", user);
		return "/index";
	}

	public SingleService getSingleService() {
		return singleService;
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

}
