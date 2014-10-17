package com.crazy.singleweb.util;

import org.apache.shiro.SecurityUtils;

import com.crazy.singleweb.entity.User;

/**
 * @Description 会话相关的工具类
 * @author <a href="mailto:liushuaiying@139130.net">Shuaiying.Liu</a>
 * @Data 2012-9-11
 * @Version 1.0.0
 */
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
