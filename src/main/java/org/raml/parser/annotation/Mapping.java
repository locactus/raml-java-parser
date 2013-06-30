package org.raml.parser.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.raml.parser.builder.TupleBuilder;
import org.raml.parser.resolver.TupleHandler;
import org.raml.parser.rule.ITupleRule;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Mapping
{

    boolean required() default false;

    boolean implicit() default false;

    Class<? extends ITupleRule> rule() default ITupleRule.class;

    Class<? extends TupleBuilder> builder() default TupleBuilder.class;

    Class<? extends TupleHandler> handler() default TupleHandler.class;

    String alias() default "";

}