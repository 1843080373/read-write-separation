package com.rd.sharding.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rd.sharding.entity.UserInfo;
import com.rd.sharding.mapper.UserInfoMapper;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public class DemoService {

	@Resource
	UserInfoMapper userInfoMapper;

	public static Long userId = 190L;

	public void demo() {
		System.out.println("Insert--------------");

		for (int i = 1; i <= 100; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			userInfo.setAccount("Account" + i);
			userInfo.setPassword("pass" + i);
			userInfo.setUserName("name" + i);
			userId++;
			userInfoMapper.insert(userInfo);
		}
		System.out.println("over..........");
	}

	public UserInfo getUserInfoByUserId(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}
	
	public List<UserInfo> selectAll() {
		return userInfoMapper.selectAll();
	}
}