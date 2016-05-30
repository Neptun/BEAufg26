import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

		DynamicBinder binder = new DynamicBinder();
		Request r1 = new Request("Hannover", "Muenchen", "type(result, flight),nonStop(result)");
		assertEquals("LH201 from Hannover to Muenchen", binder.executeTarget(r1));

		Request r2 = new Request("Hannover", "Muenchen", "type(result,trip),nonStop(result)");
		assertEquals("LH201 from Hannover to Muenchen", binder.executeTarget(r2));

		Request r3 = new Request("Hannover", "Muenchen", "type(result,trip)");
		assertEquals("LH201 from Hannover to Muenchen", binder.executeTarget(r3));

	}

}
