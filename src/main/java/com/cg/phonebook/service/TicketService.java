package com.cg.phonebook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.exception.UserException;
import com.cg.phonebook.repository.PhonebookUsersRepository;
//import com.cg.phonebook.repository.TicketRepository;
import com.cg.phonebook.repository.TicketRepository;

@Service
public class TicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	PhonebookUsersRepository userRepo;
	
	/*
	 * To save Raised a ticket
	 */
	public RaiseUserTicket saveTickets(String userIdentifier, RaiseUserTicket ticket) {
		boolean flag =false;
		for(UserEntity c:userRepo.findAll()) {
			System.out.println(c.getUserIdentifier());
			if(c.getUserIdentifier().equalsIgnoreCase(userIdentifier)) {
				flag =true;
				break;
				}
			else {
				continue;
			}
		}
		if(flag ==true) {
			logger.info("Ticket details saved");
			return ticketRepository.save(ticket);
		}
		else {
			logger.error("User not found");
			throw new UserException("User not found");
			
		}
	}
	/*
	 * To view the ticket by user ID
	 */
	public String viewTicket(String userIdentifier) throws TicketNotFoundException {
		RaiseUserTicket ticket=ticketRepository.findByUserIdentifier(userIdentifier);
		String ticketDescription=ticket.getDescription();
		if(ticketDescription==null) {
			logger.error("Ticket not found");
			throw new TicketNotFoundException("Ticket Not Found");
		}
		return ticketDescription;
	}
	/*
	 * Delete ticket by user ID
	 */
	public boolean deleteTicket(String userIdentifier) {
		RaiseUserTicket ticket=ticketRepository.findByUserIdentifier(userIdentifier);
		if(ticket!=null) {
			logger.info("Ticket is deleted");
			ticketRepository.delete(ticket);
			return true;
		}
		return false;
	}
}
		
