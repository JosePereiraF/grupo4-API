package br.com.serratec.exceptions;

public class VendedorNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public VendedorNotFoundException(String message) {
		super(message);
	}
    
}
