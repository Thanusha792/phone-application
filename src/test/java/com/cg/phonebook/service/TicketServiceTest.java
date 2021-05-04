package com.cg.phonebook.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.repository.PhonebookUsersRepository;
import com.cg.phonebook.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
public class TicketServiceTest {

	@Mock
	TicketRepository ticketRepo;
	@InjectMocks
	PhonebookUsersService userService;
	@Mock
	PhonebookUsersRepository userRepo;
	@InjectMocks
	TicketService ticketService;
	
	@Test
	void test1_addTicket() {
		
		UserEntity user1 = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		Mockito.when(userRepo.save(user1)).thenReturn(user1);
		UserEntity user = userService.addNewUser(user1);
		RaiseUserTicket newTicket = new RaiseUserTicket("US01","title","description");
		for(UserEntity e :userRepo.findAll()) {
			if(e.getUserIdentifier().equals(newTicket.getUserIdentifier())) {
				Mockito.when(ticketRepo.save(newTicket)).thenReturn(newTicket);
				RaiseUserTicket a = ticketService.saveTickets(user.getUserIdentifier(), newTicket);
				assertNotNull(a);
				assertEquals("title", a.getTitleType());
				assertEquals("description", a.getDescription());
			}
		}
	}
	
	@Test
    public void test2_findTicket() throws TicketNotFoundException
    {
		
        Mockito.when(ticketRepo.findByUserIdentifier("US01")).thenReturn(new RaiseUserTicket("US01","title","description"));
        String description = ticketService.viewTicket("US01");
        assertEquals("description", description);
        
    }
	
	@Test
	void test3_deleteTicket() {
		
		Mockito.when(ticketRepo.findByUserIdentifier("US01")).thenReturn(new RaiseUserTicket("US01","title","description"));
		assertTrue(ticketService.deleteTicket("US01"));
	}
	
	@Test
	void test4_deleteTicket() {
		
		Mockito.when(ticketRepo.findByUserIdentifier("US01")).thenReturn(new RaiseUserTicket("US01","title","description"));
		assertFalse(ticketService.deleteTicket("US02"));
	}
	
}
