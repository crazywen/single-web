package com.crazy.singleweb.service;

/**
 * 
 * @Description:内部通信服务
 * @Author <a href="xnxxljw@163.com">Jiawen.Liu</a>
 * @Date 2014-10-17
 * @Version 1.0.0
 */
public interface InnerMsgService {

	public static final String MENU_UPDATE_FLAG = "flag_menu_update";

	public Object getTargetObject(String key);

	public void push(String key, Object value);

}
