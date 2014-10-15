package com.crazy.singleweb.dao.mapper;

import com.crazy.singleweb.entity.User;

public interface SingleMapper {
	
	public User findUserByName(String name);

}
