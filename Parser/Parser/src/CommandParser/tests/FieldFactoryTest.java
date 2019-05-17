package CommandParser.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CommandParser.FieldFactory;

class FieldFactoryTest {

	@Test
	void fieldFactory_initializes_properly() throws Exception {
		FieldFactory testFactory;
		testFactory = new FieldFactory("test123 WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
		assertEquals("test123", testFactory.getIdentifier());
		assertEquals(1.0, testFactory.getUpAngle(), 0.001);
		assertEquals(1.0, testFactory.getDownAngle(), 0.001);
		assertEquals(125.0, testFactory.getSpeed(), 0.001);
		assertEquals(0.2, testFactory.getAcceleration(), 0.001);
	}
	
	@Test
	void fieldFactory_initializes_invalid_limit() {

		// limit is negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT -1.0 SPEED 125.0 ACCELERATION 0.2");
		});
		
	}
	
	@Test
	void fieldFactory_initializes_invalid_acceleration() {

		// Acceleration is negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT 1.0 SPEED 125.0 ACCELERATION -0.2");
		});
		
	}
	
	@Test
	void fieldFactory_initializes_invalid_speed() {

		// Speed is 0
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT 1.0 SPEED 0 ACCELERATION 0.2");
		});
		
		// Speed is negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT 1.0 SPEED -1.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void fieldFactory_initializes_invalid_identifier() {

		// Identifier is missing
		assertThrows(Exception.class, () -> {
			new FieldFactory("WITH LIMIT 1.0 SPEED 0 ACCELERATION 0.2");
		});
		
		// Identifier is whitespace, need to verify with Tappan
		assertThrows(Exception.class, () -> {
			new FieldFactory(" WITH LIMIT 1.0 SPEED -1.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void fieldFactory_initializes_valid_up_down_angles() {
		FieldFactory testFactory;
		try {
			testFactory = new FieldFactory("test123 WITH LIMIT UP 1.0 DOWN 2.0 SPEED 125.0 ACCELERATION 0.2");
			assertEquals("test123", testFactory.getIdentifier());
			assertEquals(1.0, testFactory.getUpAngle(), 0.001);
			assertEquals(2.0, testFactory.getDownAngle(), 0.001);
			assertEquals(125.0, testFactory.getSpeed(), 0.001);
			assertEquals(0.2, testFactory.getAcceleration(), 0.001);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void fieldFactory_initializes_invalid_up_down_angles() {

		// Up is negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT UP -1.0 DOWN 2.0 SPEED 125.0 ACCELERATION 0.2");
		});
		
		// Down is negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT UP 1.0 DOWN -2.0 SPEED 125.0 ACCELERATION 0.2");
		});
		
		// Both are negative
		assertThrows(Exception.class, () -> {
			new FieldFactory("test123 WITH LIMIT UP -1.0 DOWN -2.0 SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	
}
