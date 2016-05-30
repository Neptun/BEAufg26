package de.seerobben.be.aufg26.service;

import gnu.prolog.vm.PrologException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import de.seerobben.be.aufg26.dynamicBinding.DynamicBinder;
import de.seerobben.be.aufg26.dynamicBinding.FehlerImPrologFileException;
import de.seerobben.be.aufg26.dynamicBinding.KeinDienstException;
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
		String result;
		try {
			result = binder.executeTarget(request);
		} catch (KeinDienstException | FehlerImPrologFileException e) {
			result = "Fehler: " + e.getMessage();
		} catch (PrologException e) {
			result = "Prolog Fehler: " + e.getMessage();
			l.log(Level.INFO, "ups", e);
		} catch (Exception e) {
			result = "Unbekannter Fehler: " + e.getMessage();
			l.log(Level.INFO, "ohoh", e);
		}
		execution.setVariable("result", result);
	}

}
