package com.tom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@RestController
public class BookController {

	@GetMapping("testBook")
	public ModelAndView openFormLogin() {
		ModelAndView model = new ModelAndView("hi");

		return model;
	}

	@PostMapping("HelloWithId")
	public ModelAndView processForm(@ModelAttribute("id") String id, HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
		session.setAttribute("loginId", id);
		return model;
	}
}
