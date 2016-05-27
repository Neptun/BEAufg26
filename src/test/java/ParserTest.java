import static org.junit.Assert.*;

import org.junit.Test;

import de.seerobben.be.aufg26.dynamicBinding.ConditionParser;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.CompoundTermTag;
import gnu.prolog.term.Term;


public class ParserTest {

	@Test
	public void test() {
		
		CompoundTerm ct1 = new CompoundTerm(CompoundTermTag.get("p", 0));
		assertEquals(ct1.toString(),ConditionParser.parse("p()").toString());
		
		CompoundTerm ct2 = new CompoundTerm(CompoundTermTag.get("p", 0),new Term[]{});
		assertEquals(ct2.toString(),ConditionParser.parse("p()").toString());
		
		CompoundTerm ct3 = new CompoundTerm(CompoundTermTag.get("p", 1),new Term[]{AtomTerm.get("arg0")});
		assertEquals(ct3.toString(),ConditionParser.parse("p(arg0)").toString());
		
		CompoundTerm ct4 = new CompoundTerm(CompoundTermTag.get("q", 2),new Term[]{AtomTerm.get("arg0"),AtomTerm.get("arg1")});
		CompoundTerm ct3und4 = CompoundTerm.getConjunction(ct3, ct4);
		assertEquals(ct3und4.toString(), ConditionParser.parse("p(arg0),q(arg0,arg1)").toString());
		assertEquals(ct3und4.toString(), ConditionParser.parse("   p ( ar  g0 ) , q  ( arg0 , arg1 )   ").toString());
		
		
	}

}
