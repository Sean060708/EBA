package com.EBA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.EBA.Mapper")
public class EbaProjectApplication {

	public static void main(String[] args) {
		startRedisServer("C:\\Redis\\redis-server.exe");
		SpringApplication.run(EbaProjectApplication.class, args);
	}
	 private static void startRedisServer(String redisServerPath) {
	        Thread redisThread = new Thread(() -> {
	            ProcessBuilder processBuilder = new ProcessBuilder(redisServerPath);

	            // 设置工作目录，如果需要的话
	            // processBuilder.directory(new File("C:\\Redis"));

	            try {
	                Process process = processBuilder.start();
	                System.out.println("Redis啟動");

	                // 创建一个线程来读取输出流
	                new Thread(() -> {
	                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	                        String line;
	                        while ((line = reader.readLine()) != null) {
	                            System.out.println(line);
	                        }
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }).start();

	                // 创建一个线程来读取错误流
	                new Thread(() -> {
	                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
	                        String line;
	                        while ((line = reader.readLine()) != null) {
	                            System.err.println(line);
	                        }
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }).start();

	                // 不等待 Redis 服务器进程终止
	                // int exitCode = process.waitFor();
	                // System.out.println("Redis 服务器已停止，退出码：" + exitCode);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        });

	        // 设置为守护线程，确保 JVM 退出时能自动关闭
	        redisThread.setDaemon(true);
	        redisThread.start();
	    }
}
