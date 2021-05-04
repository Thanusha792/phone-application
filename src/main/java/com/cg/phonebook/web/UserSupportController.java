package com.cg.phonebook.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.service.UserSupportService;

@RestController
@RequestMapping("/api/phonebook")
@CrossOrigin
public class UserSupportController {
	

	@Autowired
	UserSupportService supportService;
	
	/*
	 * View all the tickets
	 */
	@GetMapping("/allTickets")
	public Iterable<RaiseUserTicket> findAll(){
		return supportService.findAllTickets();
	}
	
	/*
	 * View ticket by ID
	 */
	@GetMapping("/viewTicketByIdentifier/{userIdentifier}")
	public ResponseEntity<?> getTicketByUserIdentifier(@PathVariable String userIdentifier) throws TicketNotFoundException{
		String ticket = null;
		try {
			 ticket = supportService.viewTicketByuserIdentifier(userIdentifier.toUpperCase());
		}
		catch(NullPointerException e) {
			return new ResponseEntity<String>("No Tickets",HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<String>( ticket,HttpStatus.OK);
	}
}
