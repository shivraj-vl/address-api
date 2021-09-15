package com.org.app.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.org.app.models.PostalAddress;
import com.org.app.repositories.AddressRepository;
import com.org.app.services.impl.AddressService;

/**
 * @author Shivraj 
 * This class performs the tests on the API from behavior and
 *         validation aspects
 *
 */
@ExtendWith(MockitoExtension.class)
public class AddressService_UnitTest {

	@InjectMocks
	AddressService addressService;

	// mock the db layer
	@Mock
	AddressRepository addressRepository;

	@Test
	@DisplayName("UT : Service : getPostCode : size | postCode | Suburb")
	public void testGetPostCode() throws Exception {
		// prepare reference data
		PostalAddress address_1 = new PostalAddress("3163", "carnegie");
		// prepare mock service call and response
		Mockito.when(addressRepository.findById("carnegie")).thenReturn(Optional.of(address_1));

		// make the service call
		List<PostalAddress> res_postalAddressList = addressService.getPostCode("carnegie");

		// extract the response values and assert against expected values
		PostalAddress res_address = res_postalAddressList.get(0);
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();
		int res_Listsize = res_postalAddressList.size();

		assertAll(() -> assertEquals(1, res_Listsize), () -> assertEquals("3163", res_postcode),
				() -> assertEquals("carnegie", res_suburb));

	}

	@Test
	@DisplayName("UT : Service : getSuburb : size | postCode | Suburb")
	public void testGetSuburb() throws Exception {
		// prepare reference data
		PostalAddress address_1 = new PostalAddress("3024", "wyndham");
		List<PostalAddress> postalAddressList = Arrays.asList(address_1);
		// prepare mock service call and response
		Mockito.when(addressRepository.findByPostCode("3024")).thenReturn(postalAddressList);

		// make the service call
		List<PostalAddress> res_postalAddressList = addressService.getSuburb("3024");

		// extract the response values and assert against expected values
		PostalAddress res_address = res_postalAddressList.get(0);
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();
		int res_Listsize = res_postalAddressList.size();

		assertAll(() -> assertEquals(1, res_Listsize), () -> assertEquals("3024", res_postcode),
				() -> assertEquals("wyndham", res_suburb));

	}

}