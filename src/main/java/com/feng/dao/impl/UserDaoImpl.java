package com.feng.dao.impl;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.feng.dao.UserDao;
import com.feng.model.User;

@Repository("userDao")
public class UserDaoImpl  extends HibernateDaoSupport implements UserDao {

	
	@Override
	public void addUser(User user) {
//		this.getHibernateTemplate().save(user);
//		hibernateTemplate.save(user);
		this.getHibernateTemplate().save(user);
	}


	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="all_user@prod")
	public List<User> getAll() {
		System.out.println("----------------all_user");
		return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			@Override
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("from User").list();
			}
		});
//		List<User> list = new ArrayList<User>();
//		list.add(new User());
//		System.out.println("-------------------------------hello");
//		return list;
//		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getByName@name", key="#name")
	public List<User> getByName(String name) {
		System.out.println("----------------getByName");
		return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			@Override
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("from User u").list();
			}
		});
	}

	@Autowired  
	@Qualifier("sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	}

	@Override
//	@Cacheable(value={"redis1"}, key="'-'+#name+'-isRefresh'")
	public List<User> getByName(String name, boolean isRefresh) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			@Override
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("from User u").list();
			}
		});
	} 


}
