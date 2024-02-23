package com.dev.SuYonsei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SuYonsei.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByUsername(String username);
}
