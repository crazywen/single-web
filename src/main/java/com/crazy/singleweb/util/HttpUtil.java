package com.crazy.singleweb.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: HttpUtil
 * @Author <a href="jiji@javawind.com">Xuefang.Xu</a>
 * @Date 2013-09-17
 * @Version 1.0.0
 */
public class HttpUtil {

	/**
	 * 获取应用的访问地址,必须使用80端口,故不做端口判断
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String baseUrl = "http://" + request.getServerName();
		String context = request.getContextPath();
		if (!"".equals(context) && !"/".equals(context)) {
			baseUrl += context;
		}
		return baseUrl;
	}

}
