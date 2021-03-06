/*   
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.crazy.singleweb.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.crazy.singleweb.entity.BaseEntity;

public class JsonUtil {

	// private static final ObjectMapper mapper = new ObjectMapper();
	//
	// public static <T> T deserializeFromBytes(byte[] srcBytes, Class<T> t)
	// throws Exception {
	// if (srcBytes == null) {
	// throw new IllegalArgumentException("srcBytes should not be null");
	// }
	// return mapper.readValue(srcBytes, 0, srcBytes.length, t);
	// }

	// public static <T> T deserialize(String src, Class<T> t) throws Exception
	// {
	// if (src == null) {
	// throw new IllegalArgumentException("src should not be null");
	// }
	// return mapper.readValue(src, t);
	// }
	//
	// public static byte[] serializeToBytes(Object obj) throws Exception {
	// return mapper.writeValueAsBytes(obj);
	// }
	//
	// public static String serialize(Object obj) throws Exception {
	// return mapper.writeValueAsString(obj);
	// }

	/**
	 * 
	 * @param data
	 *            json data
	 * @param msg
	 *            message
	 * @param ret
	 *            0--true, not 0--false
	 * @return
	 */
	public static String toJSON(String data, String msg, int ret) {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("\"data\":");
		sb.append(data);
		sb.append(",\"msg\":\"");
		sb.append(msg);
		sb.append("\",\"ret\":");
		sb.append(ret);
		sb.append('}');
		return sb.toString();
	}

	public static String toJSON(String msg, int ret) {
		return toJSON("null", msg, ret);
	}

	public static String toJSON(int ret) {
		String msg = "Error data!";
		if (ret == 0) {
			msg = "ok";
		}
		return toJSON("null", msg, ret);
	}

	public static <T extends BaseEntity> String toJSON(List<T> entities,
			String msg, int ret) {
		return toJSON(toJSON(entities), msg, ret);
	}

	public static <T extends BaseEntity> String toJSON(int page, int total,
			List<T> entities) {
		return toJSON(page, total, entities, null);
	}

	public static <T extends BaseEntity> String toJSON(int page, int total,
			List<T> entities, Map<String, String> extParams) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"page\":" + page + ",\"total\":" + total + ",\"rows\":");
		sb.append(toJSON(entities));

		if (extParams != null && !extParams.isEmpty()) {
			sb.append(",");
			for (Entry<String, String> entry : extParams.entrySet()) {
				sb.append("\"");
				sb.append(entry.getKey());
				sb.append("\":");
				sb.append(entry.getValue()).append(',');
			}
			sb.deleteCharAt(sb.toString().length() - 1);
		}
		sb.append("}");
		return toJSON(sb.toString(), "ok", 0);
	}

	public static <T extends BaseEntity> String toPageJSON(int page, int total,
			List<T> entities) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"page\":" + page + ",\"total\":" + total + ",\"rows\":");
		sb.append(toJSON(entities));
		return sb.toString();
	}

	public static <T extends BaseEntity> String toJSON(List<T> entities) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < entities.size(); i++) {
			sb.append(i == 0 ? "" : ",").append(entities.get(i).toJSON());
		}
		sb.append("]");
		return sb.toString();
	}

}
