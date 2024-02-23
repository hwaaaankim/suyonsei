package com.dev.SuYonsei.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="TB_CLIENT")
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CLIENT_ID")
	private Long id;
	
	@Column(name="CLIENT_INQUIRYDATE")
	private Date inquirydate;
	
	@Column(name="CLIENT_CHANGEDATE")
	private Date changeDate;
	
	@Column(name="CLIENT_SUBJECT")
	private String subject;
	
	@Column(name="CLIENT_PHONE")
	private String phone;
	
	@Column(name="CLIENT_NAME")
	private String name;
	
	@Column(name="CLIENT_LAST_NAME")
	private String lastName;
	
	@Column(name="CLIENT_EMAIL")
	private String email;
	
	@Column(name="CLIENT_CODE")
	private String code;
	
	@Column(name="CLIENT_COUNTRY")
	private String country;
	
	@Column(name="CLIENT_WEAR")
	private String wear;
	
	@Column(name="CLIENT_SORT")
	private String sort;
	
	@Column(name="CLIENT_DURATION")
	private String duration;
	
	@Column(name="CLIENT_COMMENTS")
	private String comments;
	
	@Column(name="CLIENT_SIGN")
	private Boolean sign;
}