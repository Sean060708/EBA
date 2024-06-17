package com.EBA.Model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class R {
	private Boolean success;
	
	private Integer code;
	
	private String message;
	
	private Map<String,Object> data = new HashMap<String, Object>();
	
public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	//	把構造方法私有
	private R() {
		
	}
//	成功的靜態方法
	public static R ok() {
		R r = new R();
		r.setSuccess(true);
		r.setCode(ResultCode.SUCCESS);
		r.setMessage("成功");
		return r;
	}
//	失敗的靜態方法
	public static R error() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(ResultCode.ERROR);
		r.setMessage("失敗");
		return r;
	}
//	使用下面四個方法 方便以後使用鏈式編成
	
	public R success(Boolean success) {
		this.setSuccess(success);
		return this;
	}
	public R message(String message) {
		this.setMessage(message);
		return this;
	}
	public R code(Integer code) {
		this.setCode(code);
		return this;
	}
	public R data(String key,Object value) {
		this.data.put(key, value);
		return this;
	}
	
	public R data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}
}
