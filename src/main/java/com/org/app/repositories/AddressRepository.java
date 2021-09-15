package com.org.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.org.app.models.PostalAddress;

/**
 * @author Shivraj 
 * This class represents db elicitation during runtime for Spring
 */
public interface AddressRepository extends CrudRepository<PostalAddress, String>
{
	/**
	 * @param postCode
	 * @return PostalAddress
	 * This method returns available address with postcode criteria
	 */
	public List<PostalAddress> findByPostCode(String postCode);

}
