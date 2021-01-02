package br.com.srconsultoria.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	// Implementar Construtores
	
	public ObjectNotFoundException(String msg) {
		
		super(msg);
	}
	// Sobrecarga de métodos construtores para identificar exceção e causa
	
	public ObjectNotFoundException(String msg, Throwable cause) {
	
		super(msg, cause);
	}
}
