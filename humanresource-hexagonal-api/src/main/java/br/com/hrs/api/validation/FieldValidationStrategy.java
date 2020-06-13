package br.com.hrs.api.validation;

import javax.validation.groups.Default;

public class FieldValidationStrategy {
	public interface Patch extends Default{}
	public interface Update extends Default{}
	public interface Create extends Default{}
}
