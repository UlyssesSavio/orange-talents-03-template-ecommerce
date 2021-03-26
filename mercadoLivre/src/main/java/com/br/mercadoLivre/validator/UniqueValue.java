package com.br.mercadoLivre.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {
	String message() default "Campo duplicado";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
	
	String fieldName();
	
	Class<?> domainClass();

}
