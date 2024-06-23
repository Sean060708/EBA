package com.EBA.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomerAuthenticationException extends AuthenticationException {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public CustomerAuthenticationException(String msg) {
			super(msg);
		}
}
