package com.dcc;

import com.dcc.Exception.ApiException;

public class ApiResponse {

	public ApiResponse(String message) throws ApiException
	{
		throw new ApiException(message);
	}
}
