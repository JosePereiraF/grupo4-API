package br.com.serratec.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.serratec.validator.SalarioMinimoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SalarioMinimoValidator.class)
public @interface SalarioMinimo {

double valorSalario() default 1412.00;
String message() default "O valor não pode ser inferior ao Salário Mínimo";

Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};



}