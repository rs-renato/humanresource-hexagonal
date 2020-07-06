package br.com.hrs.api.exception;

import br.com.hrs.core.exception.HrsRuntimeException;

public class PatchException extends HrsRuntimeException {

	private static final long serialVersionUID = 501393729698010643L;

	public PatchException(String message) {
		super(message);
	}


	public PatchException(Throwable e) {
		super(e);
	}
}
