package com.cg.phonebook.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.phonebook.domain.RaiseUserTicket;
import com.cg.phonebook.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

	@MockBean
	private TicketService ticketService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void test1_createNewTicket() throws Exception {
		
		RaiseUserTicket newTicket = new RaiseUserTicket();
		newTicket.setTitleType("US01");
		newTicket.setTitleType("title");
		newTicket.setDescription("description");
		String inputJson = this.mapToJson(newTicket);
		Mockito.when(ticketService.saveTickets(Mockito.any(),Mockito.any(RaiseUserTicket.class))).thenReturn(newTicket);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/phonebook/addTicket/US01")
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.CREATED.value(),response.getStatus());

	}
	
	@Test
	void test2_findTicket() throws Exception {
		
		RaiseUserTicket newTicket = new RaiseUserTicket();
		newTicket.setTitleType("US01");
		newTicket.setTitleType("title");
		newTicket.setDescription("description");
		Mockito.when(ticketService.viewTicket(Mockito.any())).thenReturn(newTicket.getDescription());
		this.mockMvc.perform(get("/api/phonebook/viewTicket/US01"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test3_findTicket() throws Exception {

		Mockito.when(ticketService.viewTicket("US02")).thenReturn("Ticket Not Found");
		this.mockMvc.perform(get("/api/phonebook/viewTicket/US02"));
		assertThat("Ticket Not Found").isEqualTo(ticketService.viewTicket("US02"));
	}
	
	@Test
	void test4_deleteByUserId() throws Exception{
		
		Mockito.when(ticketService.deleteTicket("US01")).thenReturn(true);
		this.mockMvc.perform(delete("/api/phonebook/deleteTicket/US01"))
		.andExpect(status().isOk());
		
	}
	
	@Test
	void test5_deleteByUserId() throws Exception{

		BDDMockito.given(ticketService.deleteTicket("US01")).willReturn(false);
		this.mockMvc.perform(delete("/api/phonebook/deleteTicket/US01"))
		.andExpect(status().isBadRequest());
		
	}
	 private String mapToJson(Object object) throws JsonProcessingException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 return objectMapper.writeValueAsString(object);
	 }
	
}
