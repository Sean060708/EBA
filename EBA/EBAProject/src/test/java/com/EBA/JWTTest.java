package com.EBA;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.EBA.Mapper.UserMapper;
import com.EBA.Model.Users;
import com.EBA.Utils.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.persistence.Access;

@SpringBootTest
public class JWTTest {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void contextLoads() {
//		String jwt = JwtUtils.creatJWT("10086", 60 * 60 * 1000L);
//		String jwt2 = JwtUtils.creatJWT("16","王武", 60 * 60 * 1000L);
//		System.out.println(jwt);
//		System.out.println(jwt2);
		
		try {
//			Claims claims1 = JwtUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2MzJmODIxMWIxMmY0OTBiOWYxOTYzZGI4YWY5ZDFlOSIsInN1YiI6IjEwMDg2IiwiaXNzIjoieHgiLCJpYXQiOjE3MTg2MzcxMDIsImV4cCI6MTcxODY0MDcwMn0.RxFto9ymbrq0NWTBFTOmoM_3-qe7LBXPg_y4ease0C8");
//			Claims claims2 = JwtUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNiIsInN1YiI6IueOi-atpiIsImlzcyI6Inh4IiwiaWF0IjoxNzE4NjM3MTAyLCJleHAiOjE3MTg2NDA3MDJ9.bf6PVNxlMKKfU0nBMtiNtpnAtSVY1sapcMgtt3f9XJ0");
//			String subject1 = claims1.getSubject();
//			String subject = claims2.getSubject();
//			System.out.println(subject1);
//			System.out.println(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void userMapperTest() {
		List<Users> users = userMapper.selectList(null);
		users.forEach(System.out::print);
	}
	@Test
	void passwordTest() {
		System.out.println("");
		String encode1 = passwordEncoder.encode("123");
		String encode2 = passwordEncoder.encode("123");
		System.out.println(encode1);
		System.out.println(encode2);
		
		boolean boolean1 = passwordEncoder.matches("zo4j4g426","$2a$10$iLe82JWNqsYWmLj.Ys27xO6PL9FsQrwzXnh4jgqRhDPxKnBv3G3US");
		boolean boolean2 = passwordEncoder.matches("123","$2a$10$tGKOdUXmNfGJUX9N7sWLfeRMxkzDPzDMypCjhx6JjOzzkW2h7C1Z6");
		
		System.out.println("boolean1" + boolean1);
		System.out.println(boolean2);
	
	}
}
