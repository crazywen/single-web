package com.crazy.singleweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.InnerMsgService;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.Keys;
import com.crazy.singleweb.util.SessionUtil;

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
		User user = SessionUtil.getCurUser();
		model.addAttribute("user", user);
		return Keys.KEY_ADMIN_IDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_LOGIN)
	public String preLogin(Model model) {
		User user = SessionUtil.getCurUser();
		if (null != user) {
			return "redirect:" + Keys.KEY_ADMIN_IDX;
		}
		return Keys.KEY_ADMIN_LOGIN;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_LOGIN, params = "action=doLogin")
	public String login(Model model, HttpServletRequest hreq, String username,
			String password) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String md5Pwd = DigestUtils.md5Hex(password);
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					md5Pwd);
			subject.login(token);
			hreq.getSession().setAttribute("fromLogin", true);
			return "redirect:" + Keys.KEY_ADMIN_IDX;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + Keys.KEY_ADMIN_LOGIN;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEPWD)
	public String updatePwd(Model model, String oldPwd, String newPwd) {
		User user = SessionUtil.getCurUser();
		singleService.updatePwd(user.getId(), genSecretPwd(newPwd));
		return Keys.KEY_ADMIN_UPDATEPWD;
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
		if (StringUtils.isBlank(pwd)) {
			return null;
		}
		return DigestUtils.md5Hex(pwd);
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

	@Autowired
	public void setInnerMsgService(InnerMsgService innerMsgService) {
		this.innerMsgService = innerMsgService;
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}
