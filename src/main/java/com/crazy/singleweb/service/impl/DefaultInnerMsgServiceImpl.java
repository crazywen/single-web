package com.crazy.singleweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.crazy.singleweb.service.InnerMsgService;

@Component
public class DefaultInnerMsgServiceImpl implements InnerMsgService {

	// private SingleDaoImpl singleDao;


	private Map<String, Object> map = new HashMap<String, Object>();

	public Object getTargetObject(String key) {
		return map.get(key);
	}

	public void push(String key, Object value) {
		map.put(key, value);
	}

	// @Autowired
	// public void setSingleDao(SingleDaoImpl singleDao) {
	// this.singleDao = singleDao;
	// }
}
