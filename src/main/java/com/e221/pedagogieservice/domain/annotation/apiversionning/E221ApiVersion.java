package com.e221.pedagogieservice.domain.annotation.apiversionning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface E221ApiVersion {
    String value() default "1";       // Numéro de version (par défaut : v1)
    boolean open() default false;     // Si true, ajoute le préfixe "/public"
}
