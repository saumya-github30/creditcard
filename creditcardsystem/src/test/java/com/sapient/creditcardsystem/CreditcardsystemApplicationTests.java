package com.sapient.creditcardsystem;

import com.sapient.creditcardsystem.controller.CreditCardController;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CreditcardsystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CreditCardController creditCardController;

	@Test
	void contextLoads() {
		assertThat(creditCardController).isNotNull();
	}

	@Test
	public void whenPostRequestToAddValidCard_thenCorrectResponse() throws Exception {
		String card = "{\"customerName\": \"Saumya\", \"cardNumber\" : \"4444 3333 2222 1111\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/creditcard/add")
						.content(card)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void whenPostRequestToAddInValidCard_thenCorrectResponse() throws Exception {
		String card = "{\"customerName\": \"\", \"cardNumber\" : \"4444 3333 2222 1111\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/creditcard/add")
						.content(card)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName", Is.is("Name is mandatory")))
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void whenPostRequestToAddInValidCardNumber_thenCorrectResponse() throws Exception {
		String card = "{\"customerName\": \"\", \"cardNumber\" : \"4444 3333 2222 1111 1111233\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/creditcard/add")
						.content(card)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.cardNumber", Is.is("Card number is not valid")))
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON));
	}

}

