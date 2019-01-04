package com.r.w.readwriteseparation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.r.w.readwriteseparation.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class ReadWriteSeparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadWriteSeparationApplication.class, args);
	}

}

