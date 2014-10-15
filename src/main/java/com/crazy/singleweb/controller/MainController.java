package com.crazy.singleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:Ö÷¿ØÖÆÆ÷
 * @Author <a href="liujiawen@139130.net">Jiawen.Liu</a>
 * @Date 2014-10-15
 * @Version 1.0.0
 */
@Controller
public class MainController {

	@RequestMapping(value = "/")
	public String rootPath() {
		return "/index";
	}

	// @RequestMapping(value = "/index")
	// public String index(Model model, HttpServletRequest request) {
	// System.out.println("hello index!");
	// return "redirect:/index";
	// }

}
