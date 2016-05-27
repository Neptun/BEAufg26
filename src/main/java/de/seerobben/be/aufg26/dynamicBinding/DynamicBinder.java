package de.seerobben.be.aufg26.dynamicBinding;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import de.seerobben.be.aufg26.dynamicBinding.directory.CapabilityAnnotation;
import de.seerobben.be.aufg26.dynamicBinding.directory.Directory.DynamicBindingTravels;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.PrologException;

public class DynamicBinder {

	public DynamicBinder() {

	}

	public boolean executeTarget(Request r)
			throws KeinDienstException, FehlerImPrologFileException, IOException, PrologException {

		CompoundTerm t = ConditionParser.parse(r.getPostCondition());

		Collection<Method> syntacticalMatches = new Vector<Method>();

		for (Method method : DynamicBindingTravels.class.getDeclaredMethods()) {

			if (method.getParameterTypes().length == 2) {
				syntacticalMatches.add(method);
			}
		}

		if (syntacticalMatches.size() < 1)
			throw new KeinDienstException(KeinDienstException.EXCEPTION_MESSAGE);

		boolean result = false;

		Iterator<Method> iterator = syntacticalMatches.iterator();

		while (!result && iterator.hasNext()) {
			Method current = iterator.next();

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
				break;
			case 1:
				result = true;
				break;
			case -1:
				result = result || false;
				break;
			default:
				throw new FehlerImPrologFileException(FehlerImPrologFileException.EXCEPTION_MESSAGE);
			}

		}
		return result;
	}

	private String getResource(String input) throws IOException {
		File file = new File(input);
		return file.toURI().toURL().getFile();
	}
}
