package br.com.hrs.api.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 501393729698010643L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
