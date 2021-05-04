package com.cg.phonebook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.exception.TicketNotFoundException;
import com.cg.phonebook.repository.TicketRepository;
import com.cg.phonebook.repository.UsersupportRepository;

@ExtendWith(SpringExtension.class)
public class UserSupportServiceTest {

	@Mock
	UsersupportRepository supportRepo;
	@Mock
	TicketRepository ticketRepo;
	@InjectMocks
	UserSupportService supportService;
	
	@Test
	void test1_findAllTickets() {
		
		List<RaiseUserTicket> ticketList = new ArrayList<>();
		RaiseUserTicket ticket1 = new RaiseUserTicket("US01","title","description");
		ticketList.add(ticket1);
		Mockito.when(ticketRepo.findAll()).thenReturn(ticketList);
		Iterable<RaiseUserTicket> ticket = supportService.findAllTickets();
		assertNotNull(ticket);
		
	}
	
	@Test
    public void test2_getTicketUserById() throws TicketNotFoundException
    {
		RaiseUserTicket ticket1 = new RaiseUserTicket("US01","title","description");
        Mockito.when(ticketRepo.findByUserIdentifier("US01")).thenReturn(ticket1);
        String user = supportService.viewTicketByuserIdentifier("US01");
        assertNotNull(user);
    }

}