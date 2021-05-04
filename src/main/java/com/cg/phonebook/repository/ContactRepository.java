package com.cg.phonebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.phonebook.domain.AdminEntity;
import com.cg.phonebook.domain.Contact;
import com.cg.phonebook.domain.UserEntity;

@Repository 
public interface ContactRepository extends CrudRepository<Contact, Long> {

	public Contact findByContactNumber(String phoneNumber);
	
	@Query(value="select t from UserEntity t where t.email=:email")
	UserEntity findByMailID(@Param("email") String email);
	
	@Query(value="select t from UserEntity t where t.email=:email and t.password=:password")
	UserEntity loginUser(String email,String password);
}
  