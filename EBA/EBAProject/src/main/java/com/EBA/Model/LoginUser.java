package com.EBA.Model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails{
	
	private Users users;
	
	
	
	public LoginUser(Users users) {
		this.users = users;
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


	//權限列表
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
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
	
}
