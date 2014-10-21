package com.crazy.singleweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.service.InnerMsgService;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.DynamicParam;
import com.crazy.singleweb.util.Keys;
import com.crazy.singleweb.util.ListUtil;

/**
 * @Description:
 * @Author <a href="xnxxljw@163.com">Jiawen.Liu</a>
 * @Date 2014-10-15
 * @Version 1.0.0
 */
@Controller
public class MainController {

	private SingleService singleService;

	private InnerMsgService innerMsgService;

	private List<Menu> menus;

	private List<Menu> getMenus() {
		if (ListUtil.isBlank(menus)
				|| null == innerMsgService
						.getTargetObject(InnerMsgService.MENU_UPDATE_FLAG)
				|| (Boolean) (innerMsgService
						.getTargetObject(InnerMsgService.MENU_UPDATE_FLAG))) {
			Menu menu = new Menu();
			menus = singleService.findMenus(menu, null);
			innerMsgService.push(InnerMsgService.MENU_UPDATE_FLAG,
					Boolean.valueOf(false));
		}
		return menus;
	}

	@RequestMapping(value = { Keys.KEY_ROOT, Keys.KEY_IDX })
	public String rootPath(Model model) {
		List<Menu> menus = getMenus();
		model.addAttribute("menus", menus);
		return /* "redirect:"+ */Keys.KEY_IDX;
	}

	@RequestMapping(value = Keys.KEY_SHOW_CASE)
	public String showCase(Model model, HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name) {
		Entity entity = new Entity();
		DynamicParam param = null;
		List<Entity> entitys = singleService.findEntitys(entity, param);
		model.addAttribute("entitys", entitys);
		return Keys.KEY_IDX;
	}

	@RequestMapping(value = Keys.KEY_ABOUT_US)
	public String about(Model model) {
		return Keys.KEY_ABOUT_US;
	}

	@RequestMapping(value = Keys.KEY_CONTACT)
	public String contact(Model model) {
		return Keys.KEY_CONTACT;
	}

	public SingleService getSingleService() {
		return singleService;
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

	@Autowired
	public void setInnerMsgService(InnerMsgService innerMsgService) {
		this.innerMsgService = innerMsgService;
	}

}
