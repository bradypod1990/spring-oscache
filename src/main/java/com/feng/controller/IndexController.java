package com.feng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feng.dao.UserDao;
import com.feng.model.User;

@Controller
public class IndexController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/index")
	public String index() {
		List<User> list = userDao.getAll();

		if (list != null) {
			for (User u : list) {
				System.out.println(u.getName() + "----" + u.getPassword());
			}
		}
		System.out.println("Hello World ----------------------");
		return "index";
	}
}
