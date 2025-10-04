package br.com.izilearn.izilearn_application.infrastructure.web.validation.validator;

import br.com.izilearn.izilearn_application.infrastructure.web.validation.annotation.IsAdult;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class IsAdultValidator implements ConstraintValidator<IsAdult, LocalDate> {

    private int age;

    @Override
    public void initialize(IsAdult constraintAnnotation) {
        this.age = constraintAnnotation.age();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDate == null) {
            return true;
        }

        return birthDate.plusYears(age).isBefore(LocalDate.now()) || birthDate.plusYears(age).isEqual(LocalDate.now());
    }

}
