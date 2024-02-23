package com.dev.SuYonsei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.SuYonsei.model.Member;
import com.dev.SuYonsei.service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/memberInsert")
	public String memberInsert(Member member) {
		memberService.insertAdmin(member);
		return "success";
	}
}