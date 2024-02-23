package com.dev.SuYonsei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JapanController {

	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "administration/login";
	}
	
	@GetMapping({"/", "/jp", "/jp/index"})
	public String jpIndex() {
		
		return "front/jp/index";
	}
	
	@GetMapping("/jp/equipments")
	public String equipments() {
		
		return "front/jp/equipments";
	}
	
	@GetMapping("/jp/choice")
	public String choice() {
		
		return "front/jp/choice";
	}
	
	@GetMapping("/jp/keratoconus")
	public String keratoconus() {
		
		return "front/jp/keratoconus";
	}
	
	@GetMapping("/jp/lasek")
	public String lasek() {
		
		return "front/jp/lasek";
	}
	
	@GetMapping("/jp/lasik")
	public String lasik() {
		
		return "front/jp/lasik";
	}
	
	@GetMapping("/jp/lens")
	public String lens() {
		
		return "front/jp/lens";
	}
	
	@GetMapping("/jp/smile")
	public String smile() {
		
		return "front/jp/smile";
	}
	
	@GetMapping("/jp/smilePro")
	public String smilePro() {
		
		return "front/jp/smilePro";
	}
}
