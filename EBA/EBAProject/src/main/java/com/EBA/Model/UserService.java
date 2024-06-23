package com.EBA.Model;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<Users>{

	Map<String, Object> login(Users users);
	
}
