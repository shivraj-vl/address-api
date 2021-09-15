package com.org.app.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.app.exceptions.InvalidInputException;
import com.org.app.models.PostalAddress;
import com.org.app.services.IAddressService;

/**
 * @author Shivraj This rest controller exposes the API interfaces to - pull
 *         address details given : postcode or, suburb
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {
	/**
	 * Logger instance to log messages
	 */
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	/**
	 * Business service to trigger business functionalities
	 */
	@Autowired
	@Qualifier("addressService")
	IAddressService addressService;

	/**
	 * @param postCode
	 * @param suburb
	 * @return This method services requests with param postcode / suburb
	 * @throws InvalidInputException
	 */
	@GetMapping(consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<List<PostalAddress>> getAddress(
			@RequestParam("postCode") Optional<String> postCode, @RequestParam("suburb") Optional<String> suburb) {
		logger.info("<method entry> : Inside AddressController getAddress, provided postCode::" + postCode
				+ " and suburb::" + suburb);

		List<PostalAddress> addressList = null;
		// check if the input parameters are present and valid
		if (postCode.isPresent() && !postCode.get().isEmpty()) {
			// call get suburb service
			addressList = addressService.getSuburb(postCode.get());
		} else if (suburb.isPresent() && !suburb.get().isEmpty()) {
			// call get postcode service
			addressList = addressService.getPostCode(suburb.get());
		} else {
			// error scenario
			// throw new InvalidInputException("Request parameters should contain postCode
			// or suburb");
		}
		logger.info("<method exit> : Inside AddressController getAddress, response::" + addressList);
		return new ResponseEntity<>(addressList, HttpStatus.OK);
	}

}
