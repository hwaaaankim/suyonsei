package com.dev.SuYonsei.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable{

	private static final long serialVersionUID = 2023L;

	@JsonIgnore
	@Id
	@Column(name = "MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(name = "MEMBER_USERNAME", length = 500, unique = true)
	private String username;

	@Column(name = "MEMBER_PASSWORD", length = 500)
	private String password;

	@Column(name = "MEMBER_ROLE", length = 500)
	private String memberRole;

}
