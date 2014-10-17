package com.crazy.singleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.service.InnerMsgService;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.Keys;

/**
 * 
 * @Description:后台管理控制器
 * @Author <a href="liujiawen@139130.net">Jiawen.Liu</a>
 * @Date 2014-10-17
 * @Version 1.0.0
 */
@Controller
public class AdminController {

	private SingleService singleService;

	private InnerMsgService innerMsgService;

	@RequestMapping(value = Keys.KEY_ADMIN_IDX)
	public String adminIdx(Model model) {
		return Keys.KEY_ADMIN_IDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_IDX)
	public String updatePwd(Model model, String oldPwd, String newPwd) {
		singleService.updatePwd(1, genSecretPwd(newPwd));
		return Keys.KEY_ADMIN_IDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEMENU)
	public String updateMenu(Model model, Menu menu) {
		if (singleService.updateMenu(menu)) {
			innerMsgService.push(InnerMsgService.MENU_UPDATE_FLAG,
					Boolean.valueOf(true));
		}
		return "";
	}

	public String genSecretPwd(String pwd) {
		return pwd;
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
