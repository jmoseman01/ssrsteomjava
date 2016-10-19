import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SsrsteomTreeTest {
    SsrsteomTree ssrsteomtree;

	@Before
	public void setUp() throws Exception {
		double[] initialOutputDataArr={9,42,119,258,477};
		ArrayList<Double> initialRowList = new ArrayList<Double>();
		for(double data: initialOutputDataArr)
		{
                initialRowList.add(data);
		}
		ssrsteomtree = new SsrsteomTree(initialRowList);
	}

	@Test
	public void ConstructSsrstoemTreeTest() {
		System.out.println(ssrsteomtree);
	}
	@Test
	public void TerminationEntity() {
		System.out.println(ssrsteomtree.getTermintationEntity());
		assertEquals(ssrsteomtree.getTermintationEntity(),18.0, 1e-15);
	}
	@Test
	public void solveTest() throws Exception {
		ssrsteomtree.solve();
		System.out.println(ssrsteomtree.getCoeffecientList());
	}


}
