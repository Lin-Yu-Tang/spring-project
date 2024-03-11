package com.tom;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();

		return "Hello, " + a.getName() + "!";
	}

	@GetMapping("/auth")
	public String hello(Authentication a) {
		return "Hello, " + a.getName() + "!";
	}

	@GetMapping("/bye")
	@Async
	public void goodbye() {
		SecurityContext context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();

		// do something with the username
		String s = "Hello, " + username + "!";
		/*
		 * java.lang.NullPointerException: Cannot invoke
		 * "org.springframework.security.core.Authentication.getName()" because the
		 * return value of
		 * "org.springframework.security.core.context.SecurityContext.getAuthentication()"
		 * is null
		 */

		System.out.println(s);
	}

	@GetMapping("/ciao")
	public String ciao() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};
		ExecutorService e = Executors.newCachedThreadPool();
		try {
			var contextTask = new DelegatingSecurityContextCallable<>(task);
		    return "Ciao, " + e.submit(contextTask).get() + "!";
		} finally {
			e.shutdown();
		}
	}
	
	@GetMapping("/hola")
	public String hola() throws Exception {
	  Callable<String> task = () -> {
	    SecurityContext context = SecurityContextHolder.getContext();
	    return context.getAuthentication().getName();
	  };
	 
	  ExecutorService e = Executors.newCachedThreadPool();
	  e = new DelegatingSecurityContextExecutorService(e);
	  try {
	    return "Hola, " + e.submit(task).get() + "!";
	  } finally {
	    e.shutdown();
	  }
	}
	
	
	@GetMapping("download")
	public void manualDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) 
			throws IOException {
		String rootPath = "";
		
		File file = Paths.get(rootPath + "/" + fileName).normalize().toFile();
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file.getCanonicalPath()));
		        OutputStream os = response.getOutputStream()) {
			response.setHeader("Content-Disposition", "attachment; filename=\""
			        + UriUtils.encode(file.getName().toString(), "UTF-8") + "\"");
//			IOUtils.copyLarge(is, os);
		} catch (IOException e) {
//			log.error("找不到指定檔案: {" + file.getName() + "}", e);
			throw e;
		}
	}

}
