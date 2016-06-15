package com.feng.test;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import com.feng.dao.UserDao;
import com.feng.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RedisTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void test1() {
		List<User> list = userDao.getAll();

		if (list != null) {
			for (User u : list) {
				System.out.println(u.getName() + "----" + u.getPassword());
			}
		}
	}


	@Test
	public void test2() {
		List<User> list = userDao.getByName("zoufeng", true);

		if (list != null) {
			for (User u : list) {
				System.out.println(u.getName() + "----" + u.getPassword());
			}
		}
	}
	
}