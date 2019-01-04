package com.r.w.readwriteseparation.service;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
import com.r.w.readwriteseparation.entity.User;
import com.r.w.readwriteseparation.enums.ReadDataSource;
import com.r.w.readwriteseparation.enums.WriteDataSource;
import com.r.w.readwriteseparation.mapper.UserMapper;
 
@Service
public class UserService {
 
	@Autowired UserMapper userMapper;
	
	@WriteDataSource(description="WRITE")
	public int writeUser(User user){
		return userMapper.insert(user);
	}
	
	@ReadDataSource(description="READ")
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=true)
	public List<User> readUser(){
		return userMapper.selectAllUser();
	}
}
