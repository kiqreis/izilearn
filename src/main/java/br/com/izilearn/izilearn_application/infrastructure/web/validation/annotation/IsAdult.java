package br.com.izilearn.izilearn_application.infrastructure.web.validation.annotation;

import br.com.izilearn.izilearn_application.infrastructure.web.validation.validator.IsAdultValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsAdultValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAdult {

    String message() default "The user must be at least {age} years";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int age() default 18;

}
