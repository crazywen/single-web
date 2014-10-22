package com.crazy.singleweb.service;

import java.util.List;

import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.util.DynamicParam;

public interface SingleService {

	public User findUserByName(String name);

	public List<Menu> findMenus(Menu menu, DynamicParam param);
	
	public int findMenusCount(Menu menu, DynamicParam param);

	public List<Entity> findEntitys(Entity entity, DynamicParam param);
	
	public int findEntitysCount(Entity entity, DynamicParam param);

	public List<User> findUsers(User user, DynamicParam param);

	public boolean updatePwd(int id, String pwd);

	public boolean updateUser(User user);

	public boolean addMenu(Menu menu);

	public boolean updateMenu(Menu menu);

	public boolean deleteMenu(int id);

	public boolean addEntity(Entity entity);

	public boolean updateEntity(Entity entity);

	public boolean deleteEntity(int id);

}
