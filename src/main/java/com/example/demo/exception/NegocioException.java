package com.example.demo.exception;

//extends RuntimeExceptiom
public class NegocioException extends RuntimeException {
	
	//cria construtor específico, nesse caso, com mensagem
	public NegocioException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
