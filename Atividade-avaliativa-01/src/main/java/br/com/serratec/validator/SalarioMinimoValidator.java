package br.com.serratec.validator;

import br.com.serratec.annotation.SalarioMinimo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalarioMinimoValidator  implements ConstraintValidator<SalarioMinimo, Double>{
	
	Double valorSalario;
	
	@Override
	public void initialize (SalarioMinimo constantAnnotation)
	{
		this.valorSalario = constantAnnotation.valorSalario();
	}
	
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context)
	{
		return value != null && value >= valorSalario; 
	}


}