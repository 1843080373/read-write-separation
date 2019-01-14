package com.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:weixin4j.properties")// 用来指定配置文件的位置
public class DemoWechat4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWechat4jApplication.class, args);
	}

}

