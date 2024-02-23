package com.dev.SuYonsei.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SuYonsei.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	Page<Client> findAllByOrderByIdDesc(Pageable pageable);
	
	Page<Client> findAllByPhoneOrderByIdDesc(Pageable pageable, String phone);
	
	Page<Client> findAllByNameOrderByIdDesc(Pageable pageable, String name);
	
	Page<Client> findAllBySubjectOrderByIdDesc(Pageable pageable, String subject);
	
	Page<Client> findAllByEmailOrderByIdDesc(Pageable pageable, String email);
	
	Page<Client> findAllByInquirydateLessThan(Pageable pageable, Date date);
	
	Page<Client> findAllByInquirydateGreaterThan(Pageable pageable, Date date);
	
	Page<Client> findAllByInquirydateBetween(Pageable pageable, Date startDate, Date endDate);
}
