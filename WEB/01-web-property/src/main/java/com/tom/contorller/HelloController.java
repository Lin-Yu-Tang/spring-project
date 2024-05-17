package com.tom.contorller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.Book;
import com.tom.bean.Books;
import com.tom.bean.LoginDTO;
import com.tom.config.SpringProperty;

@RestController
public class HelloController {

	@Autowired
	private SpringProperty property;
	
	
	@GetMapping("hello")
	public String hello() {
		
		String active = property.getProfiles().getActive();
		System.out.println(active);
		
//		String active = property.profiles().active();
//		System.out.println(active);
		
		Books books = new Books("hello");
		Book book = new Book("test");
		
		return "";
	}
	
	@PostMapping("record")
	public String test(@RequestBody LoginDTO.TempPwdDTO body) {
		
		System.out.println(body.force());
		
		return "";
	}
}
