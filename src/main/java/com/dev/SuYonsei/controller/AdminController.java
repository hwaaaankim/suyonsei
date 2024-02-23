package com.dev.SuYonsei.controller;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.SuYonsei.model.Client;
import com.dev.SuYonsei.repository.ClientRepository;
import com.dev.SuYonsei.service.ClientService;
import com.dev.SuYonsei.service.EmailService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/clientDetail/{id}")
	public String clientDetail(
			@PathVariable Long id,
			Model model
			) {
		Client client = clientRepository.findById(id).get();
		if(!client.getSign()) {
			clientService.changeSign(id);
			clientService.changeDate(id);
		}
		model.addAttribute("client", client);
		return "administration/clientDetail";
	}
	
	@GetMapping("/clientDelete/{id}")
	public String clientDelete(
			@PathVariable Long id,
			Model model
			) {
		clientRepository.deleteById(id);
		return "redirect:/admin/clientManager";
	}
	
	@PostMapping("/sendEmail")
	public String sendEmail(
			Long id,
			String email,
			String message,
			String title
			
			) {
		System.out.println(email);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
        // 작업1 (스레드)
        executorService.submit(() -> {
        	try {
        		emailService.sendEmail(new String[]{email}, title, message);
        	}catch(MailSendException e) {
        		System.out.println(e);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
    		
        });

        executorService.shutdown();
		return "redirect:/admin/clientDetail/" + id;
	}
	
	@GetMapping({"/clientManager",""})
	public String clientManager(
			Model model,
			@PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(required = false) String subjectText,
			@RequestParam(required = false) String startDate, 
			@RequestParam(required = false) String endDate
			) throws ParseException {
		
		Page<Client> clients = null;
		
		if(searchType == null || "none".equals(searchType)) {
			clients = clientRepository.findAllByOrderByIdDesc(pageable);
		}else if("name".equals(searchType)) {
			if("".equals(searchWord)) {
				clients = clientRepository.findAllByOrderByIdDesc(pageable);
			}else {
				clients = clientRepository.findAllByNameOrderByIdDesc(pageable, searchWord);
			}
		}else if("phone".equals(searchType)) {
			if("".equals(searchWord)) {
				clients = clientRepository.findAllByOrderByIdDesc(pageable);
			}else {
				clients = clientRepository.findAllByPhoneOrderByIdDesc(pageable, searchWord);
			}
		}else if("email".equals(searchType)) {
			if("".equals(searchWord)) {
				clients = clientRepository.findAllByOrderByIdDesc(pageable);
			}else {
				clients = clientRepository.findAllByEmailOrderByIdDesc(pageable, searchWord);
			}
		}else if("period".equals(searchType)) {
			clients = clientService.findByDate(pageable, startDate, endDate);
		}else if("subject".equals(searchType)){
			clients = clientRepository.findAllBySubjectOrderByIdDesc(pageable, searchWord);
		}else {
			clients = clientRepository.findAllByOrderByIdDesc(pageable);
		}
		
		int startPage = Math.max(1, clients.getPageable().getPageNumber() - 4);
		int endPage = Math.min(clients.getTotalPages(), clients.getPageable().getPageNumber() + 4);
		model.addAttribute("clients", clients);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("subjectText", subjectText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "administration/clientManager";
	}
}
