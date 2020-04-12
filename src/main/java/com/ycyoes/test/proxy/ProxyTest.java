package com.ycyoes.test.proxy;

import com.ycyoes.common.entity.User;
import com.ycyoes.common.mybatis.mapper.UserMapper;
import com.ycyoes.common.proxy.MapperProxy;

public class ProxyTest {
	public static void main(String[] args) {
		MapperProxy proxy = new MapperProxy();
		UserMapper mapper = proxy.newInstance(UserMapper.class);
		User user = mapper.getUserById(1001);
		
		System.out.println("ID: " + user.getId());
		System.out.println("Name: " + user.getName());
		System.out.println("Age: " + user.getAge());
		
		System.out.println(mapper);
	}
}
