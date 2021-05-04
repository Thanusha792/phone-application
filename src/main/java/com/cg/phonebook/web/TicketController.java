package com.cg.phonebook.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.service.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/phonebook")
@CrossOrigin

public class TicketController {
	
	@Autowired
	TicketService ticketService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Add the new ticket
	 */
	@PostMapping("/addTicket/{userIdentifier}")
	public ResponseEntity<?> createTicket(@Valid @PathVariable String userIdentifier, @RequestBody RaiseUserTicket ticket,BindingResult result) {
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
	        RaiseUserTicket newticket=ticketService.saveTickets(userIdentifier,ticket);
	        if(newticket!=null) {
	        	return new ResponseEntity<RaiseUserTicket>(newticket, HttpStatus.CREATED);
	        }
	        else {
	        	return new ResponseEntity<String>(ticket.getUserIdentifier()+" already exist", HttpStatus.BAD_REQUEST);
	        }
		
	}
	
	/*
	 * View ticket by user ID
	 */
	@GetMapping("/viewTicket/{userIdentifier}")
	public ResponseEntity<?> viewTicketById(@PathVariable String userIdentifier) throws TicketNotFoundException {
		String ticket=null;
		try {
			ticket=ticketService.viewTicket(userIdentifier.toUpperCase());
		}
		catch(NullPointerException e) {
			return new ResponseEntity<String>("Ticket Not Found",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(ticket, HttpStatus.OK);
	}
	
	/*
	 * Delete ticket by user ID
	 */
	@DeleteMapping("/deleteTicket/{userIdentifier}")
	public ResponseEntity<?> deleteTicketById(@PathVariable String userIdentifier){
		boolean ticket=ticketService.deleteTicket(userIdentifier.toUpperCase());
		if(ticket==false) {
			return new ResponseEntity<String>(userIdentifier+" Ticket Not Found",HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<String>("ticket deleted",HttpStatus.OK);
		}
		
	}

}
