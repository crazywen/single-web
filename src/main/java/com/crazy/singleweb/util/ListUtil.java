package com.crazy.singleweb.util;

import java.util.List;

public class ListUtil {

	public static <T> boolean isBlank(List<T> list) {
		return (list == null || list.isEmpty());
	}

}
