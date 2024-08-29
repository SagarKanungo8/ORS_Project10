package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.CustomerDTO;

public interface CustomerServiceInt extends BaseServiceInt<CustomerDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public CustomerDTO findByName(String name, UserContext userContext);

}
