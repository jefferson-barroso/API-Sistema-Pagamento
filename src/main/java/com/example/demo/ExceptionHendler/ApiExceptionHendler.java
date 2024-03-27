package com.example.demo.ExceptionHendler;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.NegocioException;

@RestControllerAdvice //<-Capitura das exceptions dos controllers
//colocaque todos os exceptions anteriores aqui
public class ApiExceptionHendler extends ResponseEntityExceptionHandler{
	//capturador de excessões, para dar respostas mais tecnicas
	//sem estourar o stacktrace

	private final MessageSource messageSource;
	
	public ApiExceptionHendler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(status);
		problemDetail.setTitle("Um ou mais campos estão inválidos");
		problemDetail.setType(URI.create(""));
		
		var fields = ex.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(error -> ((FieldError)error).getField(), error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())));
		
		problemDetail.setProperty("fields", fields);
		return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}
	
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
