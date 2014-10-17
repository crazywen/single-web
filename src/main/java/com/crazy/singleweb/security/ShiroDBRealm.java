/*
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.crazy.singleweb.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.SessionUtil;

/**
 * 
 * @Description:
 * @Author <a href="liujiawen@139130.net">Jiawen.Liu</a>
 * @Date 2014-10-17
 * @Version 1.0.0
 */
public class ShiroDBRealm extends AuthorizingRealm {

	// private static RolePermission adminIdxPerm = new RolePermission(
	// Keys.KEY_ADMIN_IDX);
	// private static RolePermission adminUpdateMenuPerm = new RolePermission(
	// Keys.KEY_ADMIN_UPDATEMENU);

	private SingleService singleService;

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		// ShiroAuthorizationInfo info = (ShiroAuthorizationInfo)
		// getAuthorizationInfo(principals);
		// if (info.getPermission(permission) != null) {
		// return true;
		// }
		return true;
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals,
			org.apache.shiro.authz.Permission permission) {
		// ShiroAuthorizationInfo info = (ShiroAuthorizationInfo)
		// getAuthorizationInfo(principals);
		// RolePermission targetPerm = (RolePermission) permission;
		// RolePermission perm = info.getPermission(targetPerm.getBaseUrl());
		// if (perm != null) {
		// // targetPerm.setDataScope(perm.getDataScope());
		// targetPerm.setPermissionId(perm.getPermissionId());
		// return true;
		// }
		return true;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		checkNotNull(username, "Null username are not allowed by this realm.");
		User user = singleService.findUserByName(username);
		checkNotNull(user, "No account found for user [" + username + "]");
		SessionUtil.setCurUser(user);
		// user.setUsername(username);
		return new SimpleAuthenticationInfo(username, user.getPwd()
				.toLowerCase().toCharArray(), getName());
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return SecurityUtils.getSubject().getSession().getId();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		checkNotNull(principals,
				"PrincipalCollection method argument cannot be null.");
		// User user = SessionUtil.getCurUser();
		ShiroAuthorizationInfo info = new ShiroAuthorizationInfo();
		// info.addPermission(adminIdxPerm); // main page
		// info.addPermission(adminUpdateMenuPerm); // root path "/"
		// List<Integer> roleIds = userService.findRoleIds(user.getId());
		// if (ListUtil.isBlank(roleIds)) {
		// return info;
		// }
		// a set for left navigation menus
		// List<Permission> menus = new ArrayList<Permission>();
		// List<RolePermission> perms = null;
		// Permission temp = null;
		// HashSet<Integer> tempSet = new HashSet<Integer>();
		// for (Integer roleId : roleIds) {
		// perms = bizDataService.getRolePermissions(roleId,
		// user.getPlatform());
		// if (ListUtil.isBlank(perms)) {
		// continue;
		// }
		// for (RolePermission perm : perms) {
		// info.addPermission(perm);
		// if (!tempSet.add(perm.getPermissionId())) {// 去重
		// continue;
		// }
		// temp = bizDataService.getPermission(perm.getPermissionId());
		// if (temp != null && temp.isMenu()) {
		// menus.add(temp);
		// }
		// }
		// }
		// Collections.sort(menus, orderComparator);
		// set to the session
		// SecurityUtils.getSubject().getSession()
		// .setAttribute(WebConstants.KEY_LEFT_NAV, menus);
		return info;
	}

	private void checkNotNull(Object reference, String message) {
		if (reference == null) {
			throw new AuthenticationException(message);
		}
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

}
