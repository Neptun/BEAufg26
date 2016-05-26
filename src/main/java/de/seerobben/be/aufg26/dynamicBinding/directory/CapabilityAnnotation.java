package de.seerobben.be.aufg26.dynamicBinding.directory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CapabilityAnnotation {
	public String precondition();
	public String postcondition();
}