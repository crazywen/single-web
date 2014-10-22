package com.crazy.singleweb.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.crazy.singleweb.dao.CommonDao;
import com.crazy.singleweb.dao.mapper.SingleMapper;
import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.util.DynamicParam;

@Component("singleDao")
public class SingleDaoImpl extends CommonDao {

	public User findUserByName(String name) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findUserByName(name);
		} finally {
			session.close();
		}
	}

	public boolean updatePwd(int id, String pwd) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.updatePwd(id, pwd);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public boolean updateUser(User user) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.updateUser(user);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public List<User> findUsers(User user, DynamicParam param) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findUsers(user, param);
		} finally {
			session.close();
		}
	}

	public List<Menu> findMenus(Menu menu, DynamicParam param) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findMenus(menu, param);
		} finally {
			session.close();
		}
	}

	public boolean addMenu(Menu menu) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.addMenu(menu);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public boolean updateMenu(Menu menu) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.updateMenu(menu);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public boolean deleteMenu(int id) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.deleteMenu(id);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public List<Entity> findEntitys(Entity entity, DynamicParam param) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findEntitys(entity, param);
		} finally {
			session.close();
		}
	}

	public boolean addEntity(Entity entity) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.addEntity(entity);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public boolean updateEntity(Entity entity) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.updateEntity(entity);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public boolean deleteEntity(int id) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			mapper.deleteEntity(id);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public int findEntitysCount(Entity entity, DynamicParam param) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findEntitysCount(entity, param);
		} finally {
			session.close();
		}
	}

	public int findMenusCount(Menu menu, DynamicParam param) {
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try {
			return mapper.findMenusCount(menu, param);
		} finally {
			session.close();
		}
	}
}
