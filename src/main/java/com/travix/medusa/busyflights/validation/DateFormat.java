package com.travix.medusa.busyflights.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormat {

	String message() default "invalid date format";

	String pattern() default "yyyy-MM-dd";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
