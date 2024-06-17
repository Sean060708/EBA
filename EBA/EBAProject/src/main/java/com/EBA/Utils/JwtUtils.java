package com.EBA.Utils;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
	
//	有效期 一小時
	public static final Long JWT_TTL = 60*60*1000L;
//  設置秘鑰
	public static final String JWT_KEY = "sean";
	
//	生成令牌
	public static String getUUID() {
		String token = UUID.randomUUID().toString().replaceAll("-","");
		return token;
	}
	
//	生成jwt
//	@param subject token中要存放的數據(json格式)
//	@param ttlMillis token超時時間
//	@return
	
	public static String creatJWT(String subject, Long ttlMillis) {
		JwtBuilder builder = getJwtBuilder(subject,ttlMillis,getUUID());
		return builder.compact();
	}
	
	private static JwtBuilder getJwtBuilder(String subject,Long ttlMillis,String uuid) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis(); // 獲取當前時間戳
		Date now = new Date(nowMillis);
		if(ttlMillis == null) {
			ttlMillis = JwtUtils.JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);
		return Jwts.builder()
				.setId(uuid) //唯一ID
				.setSubject(subject) //主題 可以式JSON數據
				.setIssuer("xx") //簽發者
				.setIssuedAt(now) //簽發時間
				.signWith(signatureAlgorithm, secretKey) //使用HS256對稱家祕法 第二個參數為密鑰
				.setExpiration(expDate);
	}
	public static String creatJWT(String id, String  subject, Long ttlMillis) {
		JwtBuilder builder = getJwtBuilder(subject,ttlMillis,id);
		return builder.compact();
	}
//	生成加密後的密鑰
	public static SecretKey generalKey() {
		byte [] encodedKey = java.util.Base64.getDecoder().decode(JwtUtils.JWT_KEY);
		SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
		return key;
	}
//	解析
	public static Claims parseJWT(String jwt) throws Exception{
		SecretKey secretKey = generalKey();
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(jwt)
				.getBody();
	}
}
