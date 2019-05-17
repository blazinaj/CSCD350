package CommandParser.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CommandParser.CreationalFactory;
import CommandParser.FieldFactory;

class CreationalFactoryTest {

	@Test
	void rudder_initialized_properly() throws Exception {
		CreationalFactory testFactory = new CreationalFactory("RUDDER test123 WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
	}
	
	@Test
	void rudder_initialized_invalid_limit() throws Exception {
		// limit is negative
		assertThrows(Exception.class, () -> {
			new CreationalFactory("RUDDER test123 WITH LIMIT -1.0 SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_initialized_invalid_type() throws Exception {
		// Butter doesn't exist
		assertThrows(Exception.class, () -> {
			new CreationalFactory("BUTTER test123 WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_initialized_invalid_id() throws Exception {
		// Id is empty
		assertThrows(Exception.class, () -> {
			new CreationalFactory("RUDDER WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_initialized_invalid_speed() throws Exception {
		// Speed is 0
		assertThrows(Exception.class, () -> {
			new CreationalFactory("RUDDER test123 WITH LIMIT 1.0 SPEED 0.0 ACCELERATION 0.2");
		});
		// Speed is negative
		assertThrows(Exception.class, () -> {
			new CreationalFactory("RUDDER test123 WITH LIMIT 1.0 SPEED -10.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_initialized_invalid_acceleration() throws Exception {
		// Acceleration is negative
		assertThrows(Exception.class, () -> {
			new CreationalFactory("RUDDER test123 WITH LIMIT 1.0 SPEED 0.0 ACCELERATION -2.0");
		});
	}

}
