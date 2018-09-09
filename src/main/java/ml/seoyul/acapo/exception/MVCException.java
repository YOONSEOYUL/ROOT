package ml.seoyul.acapo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MVCException {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler() {
		return "error";
	}
	
}
