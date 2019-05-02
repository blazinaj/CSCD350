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
	
	@Test
	void constructor_id_invalid_string_failure() {
		assertThrows(RuntimeException.class,
				() -> {
					new MyComponentLinear("", 0, 10, 1);
				});
	}
	
	@Test
	void updateState_increments_one_step_positive_success() {
		MyComponentLinear test = new MyComponentLinear("Tester", 0.0, 10.0, 1);
		
		assertEquals(0, test.getState_());
		
		test.updateState_();
		
		assertEquals(1, test.getState_());
	}
	
	@Test
	void updateState_increments_one_step_negative_success() {
		MyComponentLinear test = new MyComponentLinear("Tester", 10.0, 0.0, 1);
		
		assertEquals(10.0, test.getState_());
		
		test.updateState_();
		
		assertEquals(9.0, test.getState_());
	}

}
