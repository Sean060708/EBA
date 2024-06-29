package com.EBA.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginUser implements UserDetails {
	
	private Users users;

	private List<String> list;

	public LoginUser(Users users, List<String> list) {
		this.users = users;
		this.list = list;
	}

	public LoginUser() {
		super();
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	// 自訂義權限列表的集合 中轉操作 不做序列化
	@JSONField(serialize = false)
	List<SimpleGrantedAuthority> authorities; // 子類
	// 權限列表

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 父類
		if (authorities != null) {
			return authorities;
		}
		authorities = new ArrayList<>();
		for (String item : list) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(item);
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getUsername();
	}

//	帳號是否未過期
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

//	帳號是否沒有鎖定
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

//	帳號是否沒有超時
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

//	帳號是否可用
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return users.toString();
	}

}
