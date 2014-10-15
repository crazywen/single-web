package com.crazy.singleweb.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CommonDao {

	protected SqlSessionFactory sessionFactory;

	@Autowired
	@Qualifier("singleSessionFactory")
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return sessionFactory
	 */
	public SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
