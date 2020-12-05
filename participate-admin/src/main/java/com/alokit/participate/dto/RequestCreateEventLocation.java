package com.alokit.participate.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestCreateEventLocation {
	private String locationName;

	@NotNull(message = "addressLine1 cannot be null")
	@NotEmpty(message = "addressLine1 cannot be empty")
	private String addressLine1;

	private String addressLine2;

	@NotNull(message = "zipCode cannot be null")
	@NotEmpty(message = "zipCode cannot be empty")
	private String zipCode;

	@NotNull(message = "city cannot be null")
	@NotEmpty(message = "city cannot be empty")
	private String city;

	@NotNull(message = "state cannot be null")
	@NotEmpty(message = "state cannot be empty")
	private String state;

	@NotNull(message = "country cannot be null")
	@NotEmpty(message = "country cannot be empty")
	private String country;

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getLocationName() {
		return locationName;
	}

}
