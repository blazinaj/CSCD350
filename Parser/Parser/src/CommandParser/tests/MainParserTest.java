package CommandParser.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CommandParser.FieldFactory;
import CommandParser.MainParser;

class MainParserTest {

	@Test
	void main_parse_initializes_CREATE_success() throws Exception {
		MainParser testParser = new MainParser();
		testParser.parseLine("CREATE RUDDER test123 WITH LIMIT 15.0 SPEED 85.0 ACCELERATION 12.6");
	}
	
	// TODO implement DECLARE code
	@Test
	void main_parse_initializes_DECLARE_success() throws Exception {
		MainParser testParser = new MainParser();
		testParser.parseLine("DECLARE RUDDER test123 WITH LIMIT 15.0 SPEED 85.0 ACCELERATION 12.6");
	}
	
	// TODO implement DO code
	@Test
	void main_parse_initializes_DO_success() throws Exception {
		MainParser testParser = new MainParser();
		testParser.parseLine("DECLARE RUDDER test123 WITH LIMIT 15.0 SPEED 85.0 ACCELERATION 12.6");
	}
	
	@Test
	void main_parse_invalid_command() {
		MainParser testParser = new MainParser();
		
		// DESTROY is invalid
		assertThrows(Exception.class, () -> {
			testParser.parseLine("DESTROY RUDDER test123 WITH LIMIT 15.0 SPEED 85.0 ACCELERATION 12.6");
		});
	}

}
