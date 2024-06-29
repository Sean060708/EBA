package com.EBA.Model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("SYS_USERS")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private String username;
	
	private String nickname;
	@JSONField(serialize = false)
	private String password;
	
	private Integer status;
	
	private String email;
	
	private String phonenumber;
	
	private Integer sex;
	
	private String avatar;
	
	private Integer usertype;
	
	private Integer createby;
	
	private Date createtime;
	
	private Integer updateby;
	
	private Date updatetime;
	
	private Integer deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Integer updateby) {
		this.updateby = updateby;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Users(String username, String nickname, String password, Integer status, String email, String phonenumber,
			Integer sex, String avatar, Integer usertype, Date createtime, Integer deleted) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.status = status;
		this.email = email;
		this.phonenumber = phonenumber;
		this.sex = sex;
		this.avatar = avatar;
		this.usertype = usertype;
		this.createtime = createtime;
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", status=" + status + ", email=" + email + ", phonenumber=" + phonenumber + ", sex=" + sex
				+ ", avatar=" + avatar + ", usertype=" + usertype + ", createby=" + createby + ", createtime="
				+ createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", deleted=" + deleted + "]";
	}

	
	
}
