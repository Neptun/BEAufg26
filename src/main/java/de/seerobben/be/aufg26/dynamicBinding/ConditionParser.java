package de.seerobben.be.aufg26.dynamicBinding;

import java.util.Iterator;
import java.util.Vector;

import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.CompoundTermTag;
import gnu.prolog.term.Term;

public class ConditionParser {

	public static CompoundTerm parse(String request) {

		Vector<CompoundTerm> terms = new Vector<CompoundTerm>();
		for (String term : request.replace(" ", "").replace("),", ");").split(";")) {
			String substr = term.substring(term.indexOf("(") + 1, term.indexOf(")"));
			int arity = 0 ;
			if (!substr.equals("")) {
				arity = countChar(term, ',')+1;	
			}
			
			CompoundTermTag tt = CompoundTermTag.get(term.substring(0, term.indexOf("(")), arity);

			
			if (!substr.equals("")) {
				String[] args = substr.split(",");

				Term[] argTerms = new Term[args.length];

				for (int i = 0; i < args.length; i++) {
					argTerms[i] = AtomTerm.get(args[i]);
				}
				terms.add(new CompoundTerm(tt, argTerms));
			} else {
				terms.add(new CompoundTerm(tt, new Term[0]));
			}
		}

		return conjunctTerms(terms);
	}

	private static CompoundTerm conjunctTerms(Vector<CompoundTerm> terms) {
		Iterator<CompoundTerm> iter = terms.iterator();
		CompoundTerm result = iter.next();
		while (iter.hasNext()) {
			result = CompoundTerm.getConjunction(result, iter.next());
		}
		return result;
	}

	public static Integer countChar(String s, char c) {
		int result = 0;
		for (char c2 : s.toCharArray()) {
			if (c == c2)
				result++;
		}
		return result;
	}

}
