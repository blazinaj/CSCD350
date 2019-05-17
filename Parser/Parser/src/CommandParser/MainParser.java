package CommandParser;

import java.util.Scanner;

public class MainParser {
	
	public MainParser(){
		
	}
	
	public void parseLine() throws Exception {
		
		// TODO, only dealing with NewLines, not dealing with Semi-Colons yet!

			Scanner scan = new Scanner(System.in);
			String inputLine = scan.nextLine();
			
			parseLine(inputLine);

	}
	
	public void parseLine(String inputLine) throws Exception {
		boolean commit = false;
		
		while (!commit) {
			
			try {
				String[] parsed = inputLine.split(" ");
				
				switch (parsed[0]) {
					case "CREATE":
						int startIndex = parsed[0].length() + 1;
						String restOfLine = inputLine.substring(startIndex);
						CreationalFactory creationalFactory = new CreationalFactory(restOfLine);
						break;
						
					default:
						throw new Exception("Error: " + parsed[0] + " is an invalid command");
					}
				// TODO check for COMMIT, right now hard coded to not infinite loop
				commit = true;
			} catch (Exception e) {
				throw new Exception("Error: At Main Parser, " + e.getMessage(), e);
			}
		}
	}
}