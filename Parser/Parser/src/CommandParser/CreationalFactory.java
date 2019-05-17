package CommandParser;

public class CreationalFactory {

	public CreationalFactory(String input) throws Exception {
		try {
			String[] parsed = input.split(" ");
			
			switch (parsed[0]) {
			case "RUDDER":
				int startIndex = parsed[0].length() + 1;
				String restOfLine = input.substring(startIndex);
				
				RudderFactory rudderFactory = new RudderFactory(restOfLine);
				
				break;
			default:
				throw new Exception("Error: " + parsed[0].toString() + "Is an Invalid Type, ");
			}
			
		} catch (Exception e) {
			throw new Exception("Error: At Creational Factory, " + e.getMessage());
		}
	}

}
