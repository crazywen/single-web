package com.crazy.singleweb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.DynamicParam;
import com.crazy.singleweb.util.EntityUtils;
import com.crazy.singleweb.util.JsonUtil;
import com.crazy.singleweb.util.Keys;
import com.crazy.singleweb.util.PageInfo;
import com.crazy.singleweb.util.SessionUtil;
import com.crazy.singleweb.util.StringUtil;

@Controller
public class EntityController {

	private static final Logger logger = LoggerFactory
			.getLogger(EntityController.class);

	private SingleService singleService;

	@RequestMapping(value = Keys.KEY_ADMIN_ENTITYIDX)
	public String menuIdx(Model model) {
		User user = SessionUtil.getCurUser();
		model.addAttribute("user", user);
		return Keys.KEY_ADMIN_ENTITYIDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_ENTITYLIST)
	public String menulist(Model model, HttpServletRequest request,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rp") int rp,
			@RequestParam(value = "name") String name) {
		String jsonData = JsonUtil.toJSON(page, 0, EntityUtils.EMPTY_ENTITYS);
		try {
			Entity entity = new Entity();
			if (!StringUtil.isBlank(name)) {
				entity.setName('%' + name + '%');
			}
			DynamicParam param = new DynamicParam();
			int total = singleService.findEntitysCount(entity, param);
			if (total > 0) {
				param.setPi(new PageInfo(page, rp, total));
				List<Entity> entitys = singleService.findEntitys(entity, param);
				page = param.getPi().getIndex();
				jsonData = JsonUtil.toJSON(page, total, entitys);
			}
		} catch (Exception e) {
			logger.error("entity list failed,cause:{}", e);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_ADDENTITY, params = "action=doAddEntity")
	public String addEntity(Model model, Entity entity) {
		String jsonData = JsonUtil.toJSON(-1);
		entity.setCreateTime(new Date());
		if (singleService.addEntity(entity)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEENTITY, params = "action=preUpdate")
	public String preUpdateEntity(Model model,
			@RequestParam(value = "id") int id) {
		Entity entity = singleService.findEntityById(id);
		model.addAttribute("entity", entity);
		return Keys.KEY_ADMIN_UPDATEENTITY;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEENTITY, params = "action=doUpdateEntity")
	public String updateEntity(Model model, Entity entity) {
		String jsonData = JsonUtil.toJSON(-1);
		if (singleService.updateEntity(entity)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_DELEENTITY)
	public String delEntity(Model model, @RequestParam(value = "id") int id) {
		String jsonData = JsonUtil.toJSON(-1);
		if (singleService.deleteEntity(id)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

}
