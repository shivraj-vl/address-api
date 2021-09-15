package com.org.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.org.app.models.PostalAddress;
import com.org.app.repositories.AddressRepository;
import com.org.app.services.IAddressService;

/**
 * @author Shivraj 
 * This class implements Bet business implementation function details.
 */
@Service
@Qualifier("addressService")
public class AddressService implements IAddressService{

	/**
	 * autowire instance to Address repository
	 */
	@Autowired
	private AddressRepository addressRepository;
	
	/* (non-Javadoc)
	 * @see com.org.app.services.IAddressService#getPostCode(java.lang.String)
	 * This method interacts with DB and gets postcode for input suburb
	 */
	@Override
	public List<PostalAddress> getPostCode(String suburb) {
		List<PostalAddress> responseDataList = new ArrayList<PostalAddress>();
		PostalAddress address = addressRepository.findById(suburb).get();
		responseDataList.add(address);
		return responseDataList;
	}

	/* (non-Javadoc)
	 * @see com.org.app.services.IAddressService#getSuburb(java.lang.String)
	 * This method interacts with DB and gets suburb for input postcode
	 */
	@Override
	public List<PostalAddress> getSuburb(String postCode) {
		List<PostalAddress> responseDataList = new ArrayList<PostalAddress>();
		responseDataList = addressRepository.findByPostCode(postCode);
		if(responseDataList == null || responseDataList.isEmpty())
		{
			throw new NoSuchElementException();
		}
		return responseDataList;
	}
}
