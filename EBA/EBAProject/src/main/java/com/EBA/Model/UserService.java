package com.EBA.Model;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<Users>{

	String login(Users users);
	
}
