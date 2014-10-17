package com.crazy.singleweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazy.singleweb.dao.impl.SingleDaoImpl;
import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.DynamicParam;

@Component
public class DefaultSingleServiceImpl implements SingleService {

	private SingleDaoImpl singleDao;

	public User findUserByName(String name) {
		return singleDao.findUserByName(name);
	}

	public List<User> findUsers(User user, DynamicParam param) {
		return singleDao.findUsers(user, param);
	}

	public List<Menu> findMenus(Menu menu, DynamicParam param) {
		return singleDao.findMenus(menu, param);
	}

	public List<Entity> findEntitys(Entity entity, DynamicParam param) {
		return singleDao.findEntitys(entity, param);
	}

	public boolean updatePwd(int id, String pwd) {
		return singleDao.updatePwd(id, pwd);
	}

	public boolean updateUser(User user) {
		return singleDao.updateUser(user);
	}

	public boolean addMenu(Menu menu) {
		return singleDao.addMenu(menu);
	}

	public boolean updateMenu(Menu menu) {
		return singleDao.updateMenu(menu);
	}

	public boolean deleteMenu(int id) {
		return singleDao.deleteMenu(id);
	}

	public boolean addEntity(Entity entity) {
		return singleDao.addEntity(entity);
	}

	public boolean updateEntity(Entity entity) {
		return singleDao.updateEntity(entity);
	}

	public boolean deleteEntity(int id) {
		return singleDao.deleteEntity(id);
	}

	@Autowired
	public void setSingleDao(SingleDaoImpl singleDao) {
		this.singleDao = singleDao;
	}

}
