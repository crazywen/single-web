package com.crazy.singleweb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.crazy.singleweb.dao.CommonDao;
import com.crazy.singleweb.dao.mapper.SingleMapper;
import com.crazy.singleweb.entity.User;

@Component("singleDao")
public class SingleDaoImpl extends CommonDao{

	public User findUserByName(String name){
		SqlSession session = sessionFactory.openSession();
		SingleMapper mapper = session.getMapper(SingleMapper.class);
		try{
			return mapper.findUserByName(name);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
