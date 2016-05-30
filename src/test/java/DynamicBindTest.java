import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Vector;

import org.junit.Test;

import de.seerobben.be.aufg26.dynamicBinding.DynamicBinder;
import de.seerobben.be.aufg26.dynamicBinding.FehlerImPrologFileException;
import de.seerobben.be.aufg26.dynamicBinding.KeinDienstException;
import de.seerobben.be.aufg26.dynamicBinding.Request;
import gnu.prolog.vm.PrologException;

public class DynamicBindTest {

	@Test
	public void test() throws KeinDienstException, FehlerImPrologFileException, IOException, PrologException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String preString = "Ihre heutige Verbindung von Hannover nach Muenchen als ";
		String doubleDotBreak = ":\n";
		String linebreak = "\n";

		DynamicBinder binder = new DynamicBinder();

		String reiseart = "direkter Flug";

		Request r1 = new Request("Hannover", "Muenchen", "type(result,flight),nonStop(result)");
		Collection<String> possibleMatchForR1 = new Vector<String>();
		possibleMatchForR1.add("LH201 from Hannover to Muenchen");
		String resultR1 = binder.executeTarget(r1);
		System.out.println(preString + reiseart + doubleDotBreak + resultR1 + linebreak);
		assertTrue(possibleMatchForR1.contains(resultR1));

		reiseart = "direkte Reise";

		Request r2 = new Request("Hannover", "Muenchen", "type(result,trip),nonStop(result)");
		Collection<String> possibleMatchForR2 = new Vector<String>();
		possibleMatchForR2.add("LH201 from Hannover to Muenchen");
		possibleMatchForR2.add("ICE508 from Hannover to Muenchen");
		possibleMatchForR2.add("Per pedes from Hannover to Muenchen");
		String resultR2 = binder.executeTarget(r2);
		System.out.println(preString + reiseart + doubleDotBreak + resultR2 + linebreak);
		assertTrue(possibleMatchForR2.contains(resultR2));

		reiseart = "Reise";

		for (int i = 0; i < 10; i++) {

			Request r3 = new Request("Hannover", "Muenchen", "type(result,trip)");
			Collection<String> possibleMatchForR3 = new Vector<String>();
			possibleMatchForR3.add("LH201 from Hannover to Muenchen");
			possibleMatchForR3.add("ICE508 from Hannover to Muenchen");
			possibleMatchForR3.add("Per pedes from Hannover to Muenchen");
			possibleMatchForR3.add("LH207 from Hannover to Muenchen via Frankfurt");
			String resultR3 = binder.executeTarget(r3);
			System.out.println(preString + reiseart + doubleDotBreak + resultR3 + linebreak);
			assertTrue(possibleMatchForR3.contains(resultR3));

		}

		reiseart = "Zugfahrt";

		Request r4 = new Request("Hannover", "Muenchen", "type(result,trainRide)");
		Collection<String> possibleMatchForR4 = new Vector<String>();
		possibleMatchForR4.add("ICE508 from Hannover to Muenchen");
		String resultR4 = binder.executeTarget(r4);
		System.out.println(preString + reiseart + doubleDotBreak + resultR4 + linebreak);
		assertTrue(possibleMatchForR4.contains(resultR4));

	}

}
