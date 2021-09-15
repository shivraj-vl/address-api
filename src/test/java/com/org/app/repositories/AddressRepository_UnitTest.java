package com.org.app.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.org.app.models.PostalAddress;

/**
 * @author Shivraj 
 * This class performs the tests on the API from behavior and
 *         validation aspects
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepository_UnitTest {

	@Autowired
	AddressRepository addressRepository;

	@Test
	@DisplayName("UT : Repository : findById : postCode | Suburb")
	public void testGetPostCode() throws Exception {

		// make the db call
		PostalAddress res_address = addressRepository.findById("carnegie").get();

		// extract the response values and assert against expected values
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();

		assertAll(() -> assertEquals("3163", res_postcode), () -> assertEquals("carnegie", res_suburb));
	}

	@Test
	@DisplayName("UT : Repository : findByPostCode : postCode | Suburb")
	public void testGetSuburb() throws Exception {

		// make the db call
		List<PostalAddress> res_postalAddressList = addressRepository.findByPostCode("3024");

		// extract the response values and assert against expected values
		PostalAddress res_address = res_postalAddressList.get(0);
		String res_postcode = res_address.getPostCode();
		String res_suburb = res_address.getSuburb();
		int res_Listsize = res_postalAddressList.size();

		assertAll(() -> assertEquals(1, res_Listsize), () -> assertEquals("3024", res_postcode),
				() -> assertEquals("wyndham", res_suburb));
	}

}