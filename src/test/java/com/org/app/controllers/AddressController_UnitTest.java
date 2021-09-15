package com.org.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.org.app.models.PostalAddress;
import com.org.app.services.impl.AddressService;

/**
 * @author Shivraj 
 * This class performs the tests on the API from behavior and
 *         validation aspects
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
public class AddressController_UnitTest {
	
	// mock the service layer
	@MockBean
	AddressService addressService;

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("UT : Controller : GET /address?suburb=carnegie")
	public void testGetPostCode() throws Exception {
		// prepare reference data
		PostalAddress address_1 = new PostalAddress("3163", "carnegie");
		List<PostalAddress> postalAddressList = Arrays.asList(address_1);
		
		// prepare mock service call and response
		Mockito.when(addressService.getPostCode("carnegie")).thenReturn(postalAddressList);

		// call the service and assert the expected values
		mockMvc.perform(get("/address").param("suburb", "carnegie").contentType("application/json"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].suburb", Matchers.is("carnegie")))
				.andExpect(jsonPath("$[0].postCode", Matchers.is("3163")));
	}

	@Test
	@DisplayName("UT : Controller : GET /address?postCode=3008")
	public void testGetSuburb() throws Exception {
		// prepare reference data
		PostalAddress address_1 = new PostalAddress("3008", "docklands");
		List<PostalAddress> postalAddressList = Arrays.asList(address_1);

		// prepare mock service call and response
		Mockito.when(addressService.getSuburb("3008")).thenReturn(postalAddressList);

		// call the service and assert the expected values
		mockMvc.perform(get("/address").param("postCode", "3008").contentType("application/json"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].suburb", Matchers.is("docklands")))
				.andExpect(jsonPath("$[0].postCode", Matchers.is("3008")));
	}

}