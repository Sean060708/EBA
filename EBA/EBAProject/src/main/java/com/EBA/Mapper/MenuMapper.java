package com.EBA.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.EBA.Model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MenuMapper extends BaseMapper<Menu>{
	
	@Select("select perms from sys_menu t1\r\n"
			+ "inner join sys_role_menu t2 on t2.menu_id = t1.id\r\n"
			+ "inner join sys_role t3 on t3.id = t2.role_id\r\n"
			+ "inner join sys_user_role t4 on t4.role_id = t3.id\r\n"
			+ "inner join sys_users t5 on t5.id = t4.user_id\r\n"
			+ "where t5.id =#{id}")
	List<String> getMenuByUserId(Integer integer); 
}
