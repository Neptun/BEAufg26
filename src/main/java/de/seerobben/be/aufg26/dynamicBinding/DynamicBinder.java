package de.seerobben.be.aufg26.dynamicBinding;

import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.PrologException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Vector;

import de.seerobben.be.aufg26.dynamicBinding.directory.CapabilityAnnotation;
import de.seerobben.be.aufg26.dynamicBinding.directory.Directory.DynamicBindingTravels;

public class DynamicBinder {

	public DynamicBinder() {

	}

	public String executeTarget(Request r) throws KeinDienstException, FehlerImPrologFileException, IOException,
			PrologException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		CompoundTerm t = ConditionParser.parse(r.getPostCondition());

		Vector<Method> syntacticalMatches = new Vector<Method>();

		for (Method method : DynamicBindingTravels.class.getDeclaredMethods()) {

			if (method.getParameterTypes().length == 2) {
				syntacticalMatches.add(method);
			}
		}

		if (syntacticalMatches.size() < 1)
			throw new KeinDienstException(KeinDienstException.EXCEPTION_MESSAGE);

		boolean result = false;

		Method match = null;

		while (!result && syntacticalMatches.size() > 0) {

			int actualSize = syntacticalMatches.size();
			Random rand = new Random();
			int randomInt = rand.nextInt(actualSize);

			Method current = syntacticalMatches.get(randomInt);
			syntacticalMatches.remove(randomInt);

			String prePath = "src/main/resources/prolog/";
			Environment e = new Environment();
			e.ensureLoaded(AtomTerm.get(this.getResource(prePath + "knowledgeBase.pl")));
			e.ensureLoaded(AtomTerm.get(
					this.getResource(prePath + current.getAnnotation(CapabilityAnnotation.class).postcondition())));

			Interpreter i = e.createInterpreter();
			e.runInitialization(i);

			switch (i.execute(i.prepareGoal(t))) {
			case 0:
				result = true;
				match = current;
				break;
			case 1:
				result = true;
				match = current;
				break;
			case -1:
				result = result || false;
				break;
			default:
				throw new FehlerImPrologFileException(FehlerImPrologFileException.EXCEPTION_MESSAGE);
			}

		}
		if (result) {
			return (String) match.invoke(new DynamicBindingTravels(), new Object[] { r.getFrom(), r.getTo() });
		}
		return result + " - kein Match gefunden";
	}

	private String getResource(String input) throws IOException {
		File file = new File(input);
		return file.toURI().toURL().getFile();
	}
}
