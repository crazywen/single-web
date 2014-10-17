/*
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.crazy.singleweb.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.crazy.singleweb.entity.RolePermission;
import com.crazy.singleweb.util.WebConstants;

/**
 * 
 * @Description:权限过滤器，对所有的URL进行过滤，对于没有授权的访问，将拒绝
 * @Author <a href="liujiawen@139130.net">Jiawen.Liu</a>
 * @Date 2014-10-17
 * @Version 1.0.0
 */
public class ShiroUrlBasedFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp,
			Object mappedValue) throws Exception {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		Subject subject = SecurityUtils.getSubject();
		boolean isPermitted = false;
		if (subject.isAuthenticated()) {
			RolePermission perm = new RolePermission();
			String url = hreq.getServletPath();
			if (StringUtils.isBlank(url)) {
				url = hreq.getPathInfo();// fixed for IBM WebSphere
			}
			perm.setBaseUrl(url);
			isPermitted = subject.isPermitted(perm);
			isPermitted = true;
			// if(perm.getDataScope() != null){
			// hreq.setAttribute(WebConstants.KEY_DATA_SCOPE,
			// perm.getDataScope());
			// hreq.setAttribute(WebConstants.KEY_PERM_ID,
			// perm.getPermissionId());
			// }
			// perm = null;
			// if(!isPermitted){
			// hresp.setHeader(WebConstants.HEADER_ACCESS_STATE,
			// "unauthorized");
			// }
		} else {
			hresp.setHeader(WebConstants.HEADER_ACCESS_STATE, "login");
		}
		return isPermitted;
	}

	@Override
	protected void postHandle(ServletRequest req, ServletResponse resp)
			throws Exception {
		// HttpServletRequest hreq = (HttpServletRequest) req;
	}

}
