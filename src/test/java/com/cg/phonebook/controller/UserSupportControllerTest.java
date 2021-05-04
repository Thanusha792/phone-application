package com.cg.phonebook.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.service.UserSupportService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserSupportControllerTest {

	@Mock
	private UserSupportService supportService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test1_findAllTickets() throws Exception {
		
		List<RaiseUserTicket> ticketList = new ArrayList<>();
		RaiseUserTicket ticket1 = new RaiseUserTicket("US01","title","description");
		ticketList.add(ticket1);
		BDDMockito.given(supportService.findAllTickets()).willReturn(ticketList);
		this.mockMvc.perform(get("/api/phonebook/allTickets"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test2_findTicketByUserId() throws Exception {
		RaiseUserTicket newTicket = new RaiseUserTicket();
		newTicket.setTitleType("US01");
		newTicket.setTitleType("title");
		newTicket.setDescription("description");
		Mockito.when(supportService.viewTicketByuserIdentifier(Mockito.any())).thenReturn("US01 : title,description");
		this.mockMvc.perform(get("/api/phonebook/viewTicketByIdentifier/US01"));
		assertThat("US01 : title,description").isEqualTo(supportService.viewTicketByuserIdentifier(Mockito.any()));
	}
	
	@Test
	void test3_findTicketByUserId() throws Exception {
		
		Mockito.when(supportService.viewTicketByuserIdentifier(Mockito.any())).thenReturn(null);
		this.mockMvc.perform(get("/api/phonebook/viewTicketByIdentifier/US01"))
		.andExpect(status().isBadRequest());
	}
}
