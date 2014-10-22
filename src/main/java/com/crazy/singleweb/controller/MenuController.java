package com.crazy.singleweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.service.InnerMsgService;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.DynamicParam;
import com.crazy.singleweb.util.EntityUtils;
import com.crazy.singleweb.util.JsonUtil;
import com.crazy.singleweb.util.Keys;
import com.crazy.singleweb.util.PageInfo;
import com.crazy.singleweb.util.StringUtil;

@Controller
public class MenuController {

	private static final Logger logger = LoggerFactory
			.getLogger(MenuController.class);

	private SingleService singleService;

	private InnerMsgService innerMsgService;

	@RequestMapping(value = Keys.KEY_ADMIN_MENUIDX)
	public String menuIdx(Model model) {
		return Keys.KEY_ADMIN_MENUIDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_MENULIST)
	public String menulist(Model model, HttpServletRequest request,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rp") int rp,
			@RequestParam(value = "name") String name) {
		String jsonData = JsonUtil.toJSON(page, 0, EntityUtils.EMPTY_ENTITYS);
		try {
			Menu menu = new Menu();
			if (!StringUtil.isBlank(name)) {
				menu.setName(name);
			}
			DynamicParam param = new DynamicParam();
			int total = singleService.findMenusCount(menu, param);
			if (total > 0) {
				param.setPi(new PageInfo(page, rp, total));
				List<Menu> menus = singleService.findMenus(menu, param);
				page = param.getPi().getIndex();
				jsonData = JsonUtil.toJSON(page, total, menus);
			}
		} catch (Exception e) {
			logger.error("menu list failed,cause:{}", e);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_ADDMENU, params = "action=doAddMenu")
	public String addMenu(Model model, Menu menu) {
		if (singleService.addMenu(menu)) {
			innerMsgService.push(InnerMsgService.MENU_UPDATE_FLAG,
					Boolean.valueOf(true));
		}
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEMENU)
	public String updateMenu(Model model, Menu menu) {
		if (singleService.updateMenu(menu)) {
			innerMsgService.push(InnerMsgService.MENU_UPDATE_FLAG,
					Boolean.valueOf(true));
		}
		return Keys.AJAX_JSON;
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
