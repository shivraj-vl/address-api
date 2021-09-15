package com.org.app.services;

import java.util.List;

import com.org.app.models.PostalAddress;

/**
 * @author Shivraj 
 * This class represents business functionalities required for address api.
 */
public interface IAddressService {
	
	/**
	 * @param suburb
	 * @return List<PostalAddress>
	 * This getter returns post code for given suburb
	 */
	public List<PostalAddress> getPostCode(String suburb);
	
	/**
	 * @param postCode
	 * @return List<PostalAddress>
	 * This getter returns post code for given postcode
	 */
	public List<PostalAddress> getSuburb(String postCode);
}
