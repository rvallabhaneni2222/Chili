package info.chili.security.validator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ayalamanchili
 */
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = SQLInjectionSafeConstraintValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInjectionSafe {
    String message() default "{SQLInjectionSafe}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}