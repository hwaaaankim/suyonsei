package com.dev.SuYonsei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnglishController {

	
	@GetMapping({"/en", "/en/index"})
	public String enIndex() {
		
		return "front/en/index";
	}
	
	@GetMapping("/en/equipments")
	public String equipments() {
		
		return "front/en/equipments";
	}
	
	@GetMapping("/en/choice")
	public String choice() {
		
		return "front/en/choice";
	}
	
	@GetMapping("/en/keratoconus")
	public String keratoconus() {
		
		return "front/en/keratoconus";
	}
	
	@GetMapping("/en/lasek")
	public String lasek() {
		
		return "front/en/lasek";
	}
	
	@GetMapping("/en/lasik")
	public String lasik() {
		
		return "front/en/lasik";
	}
	
	@GetMapping("/en/lens")
	public String lens() {
		
		return "front/en/lens";
	}
	
	@GetMapping("/en/smile")
	public String smile() {
		
		return "front/en/smile";
	}
	
	@GetMapping("/en/smilePro")
	public String smilePro() {
		
		return "front/en/smilePro";
	}
}
