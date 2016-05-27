package de.seerobben.be.aufg26.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import de.seerobben.be.aufg26.dynamicBinding.DynamicBinder;
import de.seerobben.be.aufg26.dynamicBinding.Request;

public class BookFlightService implements JavaDelegate {
	
	private static final Logger l = Logger.getLogger("BookFlightService");

	DynamicBinder binder = new DynamicBinder();
	
	public void execute(DelegateExecution execution) throws Exception {
		String from = (String) execution.getVariable("fromCity");
		String to = (String) execution.getVariable("toCity");
		String postCondition = (String) execution.getVariable("postCondition");
		
		l.info("Von: " + from);
		l.info("Bis: " + to);
		l.info("Mit: " + postCondition);
		
		l.info("");
		
		Request request = new Request(from, to, postCondition);
		boolean result = binder.executeTarget(request);
		
		execution.setVariable("result", result);
	}

}
