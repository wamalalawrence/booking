package com.goeuro.model;

/**
 * @author wamalalawrence
 * Model for custom error handling
 */

import org.springframework.web.client.HttpStatusCodeException;

public class CustomAplicationException extends Exception {

	private static final long serialVersionUID = -3978420182945080397L;

	private HttpStatusCodeException exception;
    private String svcErrorMessageID;
    private String message;

    public CustomAplicationException(String message) {
        super(message);
    }

    public CustomAplicationException(String svcErrorMessageID, HttpStatusCodeException exception, String message) {
        super(message);
        this.exception = exception;
        this.svcErrorMessageID = svcErrorMessageID;
        this.message = message;
    }
    
    public CustomAplicationException(){}

	public HttpStatusCodeException getException() {
		return exception;
	}

	public void setException(HttpStatusCodeException exception) {
		this.exception = exception;
	}

	public String getSvcErrorMessageID() {
		return svcErrorMessageID;
	}

	public void setSvcErrorMessageID(String svcErrorMessageID) {
		this.svcErrorMessageID = svcErrorMessageID;
	}

	@Override
	public String toString() {
		return "CustomAplicationException [exception=" + exception + ", svcErrorMessageID=" + svcErrorMessageID
				+ ", message=" + message + "]";
	}
    
    
}
