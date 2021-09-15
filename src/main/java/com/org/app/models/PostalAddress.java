package com.org.app.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Shivraj 
 * This bean class holds the business attributes for Address representation
 */
@Entity
@Table(name = "POSTAL_ADDRESS")
public class PostalAddress {
	
	/**
	 * default constructor
	 */
	public PostalAddress() {
		super();
	}

	/**
	 * @param postCode
	 * @param suburb
	 * parameterised constructor
	 */
	public PostalAddress(String postCode, String suburb) {
		super();
		this.postCode = postCode;
		this.suburb = suburb;
	}

	/**
	 * represents postcode in address
	 */
	private String postCode;

	/**
	 * represents suburb in address
	 */
	@Id
	private String suburb;

	/**
	 * @return String
	 * This getter returns post code
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param String
	 * This setter sets postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return String
	 * This getter returns suburb
	 */
	public String getSuburb() {
		return suburb;
	}

	/**
	 * @param String
	 * This setter sets suburb
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * This method spills the object details.
	 */
	@Override
	public String toString() {
		return "[" + "Post Code :" + this.getPostCode() + "||" + 
				"Suburb :" + this.getSuburb() + "]";
	}

}
