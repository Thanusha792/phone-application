package com.cg.phonebook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.repository.TicketRepository;

@Service
public class UserSupportService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserSupportService.class);
	
	@Autowired
	TicketRepository ticketRepo;
	
	/*
	 * View all the tickets
	 */
	public Iterable<RaiseUserTicket> findAllTickets(){
		return ticketRepo.findAll();
	}
	
	/*
	 * View Ticket by ID
	 */
	public String viewTicketByuserIdentifier(String userIdentifier) throws TicketNotFoundException {
		String ticket = null;
		RaiseUserTicket t = ticketRepo.findByUserIdentifier(userIdentifier);
		if(t.getUserIdentifier().equalsIgnoreCase(userIdentifier)) {
			logger.info("Ticket details found");
			ticket = t.getUserIdentifier()+" : "+t.getTitleType()+","+t.getDescription();
		}
		else {
			logger.error(userIdentifier+" not exist");
			throw new TicketNotFoundException(userIdentifier+" not exist");
		}
		return ticket;
	}
}
