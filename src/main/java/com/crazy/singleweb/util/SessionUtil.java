package com.crazy.singleweb.util;

import org.apache.shiro.SecurityUtils;

import com.crazy.singleweb.entity.User;

public class SessionUtil {

	public static void setCurUser(User user) {
		SecurityUtils.getSubject().getSession()
				.setAttribute(WebConstants.KEY_USER, user);
	}

	public static User getCurUser() {
		return (User) SecurityUtils.getSubject().getSession(true)
				.getAttribute(WebConstants.KEY_USER);
	}

	/**
	 * 是否有权限
	 * 
	 * @param url
	 * @return
	 */
	public static boolean hasPermission(String url) {
		return SecurityUtils.getSubject().isPermitted(url);
	}

	// public static DataScope getDataScope(String url) {
	// RolePermission perm = new RolePermission();
	// perm.setBaseUrl(url);
	// SecurityUtils.getSubject().isPermitted(perm);
	// return perm.getDataScope();
	// }
}
