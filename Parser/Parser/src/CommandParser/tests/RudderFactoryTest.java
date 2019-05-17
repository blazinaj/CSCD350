package CommandParser.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CommandParser.FieldFactory;
import CommandParser.RudderFactory;

class RudderFactoryTest {

	@Test
	void rudder_factory_initialized_properly() throws Exception {
		RudderFactory success = new RudderFactory("test123 WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
	}
	
	@Test
	void rudder_factory_initialized_id_is_null() {
		// id is null
		// TODO need to fix so that ID doesn't take in the "WITH"
		assertThrows(Exception.class, () -> {
			new RudderFactory("WITH LIMIT 1.0 SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_factory_initialized_limit_is_null() {
		// Doesn't receive a LIMIT
		assertThrows(Exception.class, () -> {
			new RudderFactory("test123 WITH SPEED 125.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_factory_initialized_speed_is_null() {
		// Doesn't receive a SPEED
		assertThrows(Exception.class, () -> {
			new RudderFactory("test123 WITH LIMIT 1.0 ACCELERATION 0.2");
		});
	}
	
	@Test
	void rudder_factory_initialized_acceleration_is_null() {
		// Doesn't receive an ACCELERATION
		assertThrows(Exception.class, () -> {
			new RudderFactory("test123 WITH LIMIT 1.0 SPEED 125.0");
		});
	}

}
