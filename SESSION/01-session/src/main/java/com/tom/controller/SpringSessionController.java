package com.tom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SpringSessionController {

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);

		return "index";
	}

	@PostMapping("/persistMessage")
	public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> msgs = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (msgs == null) {
			msgs = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
		}
		msgs.add(msg);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
		return "redirect:/";
	}

	/**
	 * session 失效
	 * @param request
	 * @return
	 */
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
