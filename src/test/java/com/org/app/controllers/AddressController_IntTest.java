package com.org.app.controllers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.org.app.models.PostalAddress;

/**
 * @author Shivraj 
 * This class performs the tests on the API from behavior and
 *         validation aspects
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AddressController_IntTest {
	
	@Autowired
	AddressController addressController;

	@Test
	@DisplayName("IT : API : GET /address?postCode=3163")
	public void testGetPostCode() throws Exception {
		// prepare input data
		Optional<String> postCode = Optional.of("3163");
		Optional<String> suburb = Optional.of("");
	
		// make the controller call
		ResponseEntity<List<PostalAddress>> responseEntity =  addressController.getAddress(postCode, suburb);		
		
		// extract the response values and assert against expected values
		List<PostalAddress> res_postalAddressList = responseEntity.getBody();		
		PostalAddress res_address = res_postalAddressList.get(0);
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();
		int res_Listsize = res_postalAddressList.size();

		assertAll(() -> assertEquals(1, res_Listsize), () -> assertEquals("3163", res_postcode),
				() -> assertEquals("carnegie", res_suburb));
	}

	@Test
	@DisplayName("IT : API : GET /address?suburb=wyndham")
	public void testGetSuburb() throws Exception {
		// prepare input data
		Optional<String> postCode = Optional.of("");
		Optional<String> suburb = Optional.of("wyndham");
	
		// make the controller call
		ResponseEntity<List<PostalAddress>> responseEntity =  addressController.getAddress(postCode, suburb);
		
		// extract the response values and assert against expected values
		List<PostalAddress> res_postalAddressList = responseEntity.getBody();
		PostalAddress res_address = res_postalAddressList.get(0);
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();
		int res_Listsize = res_postalAddressList.size();

		assertAll(() -> assertEquals(1, res_Listsize), () -> assertEquals("3024", res_postcode),
				() -> assertEquals("wyndham", res_suburb));
	}

}