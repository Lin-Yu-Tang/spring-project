package com.tom.aes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			
			
			String secretKey = "12345678901234567890123456789012";
			
			String passwd = "test123";
			
			
			String encPwd = AesEncryptDecryptUtils.encryptAes256(secretKey, passwd);
			System.out.println("encrypt pwd: " + encPwd);
			
			
			String decodePwd = AesEncryptDecryptUtils.decryptAes256(secretKey, encPwd);
			System.out.println("decrypt pwd: " + decodePwd);
			
			if (decodePwd != null) {
				
				boolean equals = decodePwd.equals(passwd);
				
				System.out.println("is Equal:" + equals);
				
			}
			
			
			
			
			
			
		};
	}
}
