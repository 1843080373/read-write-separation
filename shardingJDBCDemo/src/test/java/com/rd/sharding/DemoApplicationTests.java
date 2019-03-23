package com.rd.sharding;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rd.sharding.entity.UserInfo;
import com.rd.sharding.mapper.UserInfoMapper;
import com.rd.sharding.service.DemoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	UserInfoMapper userInfoMaper;

	@Resource
	DemoService demoService;

	@Test
	public void contextLoads() {
		demoService.demo();
	}

	@Test
	public void getUserInfoByUserId(){
		UserInfo userInfoByUserId = demoService.getUserInfoByUserId(192l);
		System.out.println("得到的结果为："+userInfoByUserId);
	}
	
	@Test
	public void selectAll(){
		List<UserInfo> all = demoService.selectAll();
		System.out.println("得到的结果为："+all.size());
		System.out.println(all);
	}

}