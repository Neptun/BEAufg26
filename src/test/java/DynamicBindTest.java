import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import de.seerobben.be.aufg26.dynamicBinding.DynamicBinder;
import de.seerobben.be.aufg26.dynamicBinding.FehlerImPrologFileException;
import de.seerobben.be.aufg26.dynamicBinding.KeinDienstException;
import de.seerobben.be.aufg26.dynamicBinding.Request;
import gnu.prolog.vm.PrologException;

public class DynamicBindTest {

	@Test
	public void test() throws KeinDienstException, FehlerImPrologFileException, IOException, PrologException {
		
		DynamicBinder binder = new DynamicBinder();
			Request r1 = new Request("Hannover", "Muenchen",
					"trip(result,flight),nonstop(result)");
			assertTrue(binder.executeTarget(r1));

			Request r2 = new Request("Hannover", "Muenchen",
					"trip(result,trip),nonstop(result)");
			assertTrue(binder.executeTarget(r2));

			Request r3 = new Request("Hannover", "Muenchen",
					"trip(result,trip)");
			assertTrue(binder.executeTarget(r3));
		

	}

}
