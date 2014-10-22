package com.crazy.singleweb.util;

import org.springframework.stereotype.Component;

@Component
public class Config {

	private static String contextPath;

	public static void setContextPath(String contextPath) {
		Config.contextPath = contextPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

}
