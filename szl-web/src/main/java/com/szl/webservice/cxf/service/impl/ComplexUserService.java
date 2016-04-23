package com.szl.webservice.cxf.service.impl;

import com.szl.webservice.cxf.service.IComplexUserService;
import com.szl.webservice.model.User;


public class ComplexUserService implements IComplexUserService {

	public User getUserByName(String name) {
		User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName(name);
        user.setAddress("xxxxxxxxxx");
        user.setEmail(name + "===========@yinker.com");
        return user;
	}

	public void setUser(User user) {
		System.out.println("############Server setUser###########");
        System.out.println("setUser:" + user);
	}


}
