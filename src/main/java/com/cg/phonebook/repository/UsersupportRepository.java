package com.cg.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.phonebook.domain.Usersupport;

@Repository
public interface UsersupportRepository extends JpaRepository<Usersupport,Long>{
	
}
