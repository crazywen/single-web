package com.crazy.singleweb.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.Menu;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.util.DynamicParam;

public interface SingleMapper {

	public User findUserByName(String name);

	public List<User> findUsers(@Param("user") User user,
			@Param("param") DynamicParam param);

	public List<Menu> findMenus(@Param("menu") Menu menu,
			@Param("param") DynamicParam param);

	public List<Entity> findEntitys(@Param("entity") Entity entity,
			@Param("param") DynamicParam param);

	public int updatePwd(@Param("id") int id, @Param("pwd") String pwd);

	public int updateUser(User user);

	public int addMenu(Menu menu);

	public int updateMenu(Menu menu);

	public int deleteMenu(int id);

	public int addEntity(Entity entity);

	public int updateEntity(Entity entity);

	public int deleteEntity(int id);

	public int findEntitysCount(@Param("entity") Entity entity,
			@Param("param") DynamicParam param);

	public int findMenusCount(@Param("menu") Menu menu,
			@Param("param") DynamicParam param);
}
