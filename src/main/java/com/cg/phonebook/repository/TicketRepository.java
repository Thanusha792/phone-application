package com.cg.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.phonebook.domain.RaiseUserTicket;

@Repository
public  interface TicketRepository extends JpaRepository<RaiseUserTicket,Long> {
	
	RaiseUserTicket findByUserIdentifier(String userIdentifier);
}