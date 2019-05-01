package s19cs350task4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import s19cs350task4.MyComponentLinear;

class MyComponentLinearTests {

	@Test
	void component_initializes_correctly() {
		MyComponentLinear test = new MyComponentLinear("Test ID", 0, 10, 1);
	
		assertEquals("Test ID", test.getID_());
		assertEquals(0, test.getStateStart_());
		assertEquals(10, test.getStateEnd_());
		assertEquals(1, test.getStep_());
	}

}
