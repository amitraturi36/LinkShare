package com.intelligrape.linkshare.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEqualityValidator.class)
@Documented
public @interface CheckEquality {
	 String message() default "{org.hibernate.validator.referenceguide.chapter06.CheckCase." +
	            "message}";
	  Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { };
	
	 @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
	    @Retention(RetentionPolicy.RUNTIME)
	    @Documented
	    @interface List {
		 CheckEquality[] value();
	    }

}
