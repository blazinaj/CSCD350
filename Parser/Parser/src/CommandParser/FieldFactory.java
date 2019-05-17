package CommandParser;

public class FieldFactory {
	// These should be changed to the classes defined in sbw.architecture.datatype
	Double acceleration;
	Double upAngle;
	Double downAngle;
	String identifier;
	Double percent;
	String[] position;
	Double power;
	Integer rate;
	Double speed;
	
	public FieldFactory(String input) throws Exception {
		
		try {
			
			// Split the input string
			String[] parsed = input.split(" ");
			
			// Tries to create the ID first, throws if invalid ID
			String IDToken = parsed[0];
			createIdentifier(IDToken);
			
			// TODO if ID is not present, it makes the 'WITH' the ID, need to fix
			// TODO add other Fields?
			// Starts looping through the rest of split string, starting after ID
			int i;
			boolean earlyExit;
			for (i = 1, earlyExit = false; i < parsed.length || earlyExit; i++) {
				
				String currentToken = parsed[i];
				
				switch (currentToken) {
					// LIMIT <angle> ...
					// LIMIT UP <angle> ...
					case "LIMIT":
						// Checks if it is LIMIT UP or just LIMIT
						switch (parsed[i + 1]) {
						case "UP":
							// If LIMIT UP, skip the UP token (currentToken)
							createLimit("UP", parsed[i + 2]);
							break;
						default:
							// If just LIMIT, 
							createLimit("BOTH", parsed[i + 1]);
							break;
						}
						break;
					case "DOWN":
						createLimit("DOWN", parsed[i + 1]);
						break;
					case "SPEED":
						createSpeed(parsed[i + 1]);
						break;
					case "ACCELERATION":
						createAcceleration(parsed[i + 1]);
						break;
					case "\n":
						earlyExit = true;
				}
			}
		} catch (Exception e) {
			//System.out.println("Error: From FieldFactory " + e.getMessage());
			throw new Exception("Error: From FieldFactory, " + e.getMessage(), e);
		}
		
	}

	private void createAcceleration(String string) throws Exception {
		try {
			Double acceleration = Double.parseDouble(string);
			
			if (acceleration < 0) {
				throw new Exception("Error: Acceleration must be non-negative");
			}
			
			this.acceleration = acceleration;
			
		} catch (Exception e) {
			throw new Exception("Error: From createAcceleration, " + e.getMessage(), e);
		}
	}
	
	public Double getAcceleration() {
		return this.acceleration;
	}

	private void createSpeed(String string) throws Exception {
		try {
			//this.speed = new Speed(string);
			Double speed = Double.parseDouble(string);
			
			if (speed <= 0) {
				throw new Exception("Error: Speed must be positive");
			}
			
			this.speed = speed;
			
		} catch (Exception e) {
			throw new Exception("Error: From createSpeed, " + e.getMessage(), e);
		}
	}
	
	public Double getSpeed() {
		return this.speed;
	}
	

	private void createLimit(String direction, String string) throws Exception {
		try {
			//this.angle = new Angle(string);
			// LIMIT UP <angle1> DOWN <angle2>
			
			Double limit = Double.parseDouble(string);
			
			if (limit < 0) {
				throw new Exception("Error: Limit must be non-negative");
			}
			
			switch (direction) {
				case "UP":
					this.upAngle = limit;
					break;
				case "DOWN":
					this.downAngle = limit;
					break;
				case "BOTH":
					this.upAngle = limit;
					this.downAngle = limit;
					break;
			}
			
		} catch (Exception e) {
			throw new Exception("Error: From createLimit, " + e.getMessage(), e);
		}
		
	}
	
	public Double getUpAngle() {
		return this.upAngle;
	}
	
	public Double getDownAngle() {
		return this.downAngle;
	}

	private void createIdentifier(String string) throws Exception {
		try {
			if (string.isEmpty()) {
				throw new Exception("Error: ID String Is Empty");
			}
			//this.identifier = new Identifier(string);
			this.identifier = string;
			
		} catch (Exception e) {
			throw new Exception("Error: From createIdentifier, " + e.getMessage(), e);
		}
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	// For Testing only
	public void printFields() {
		System.out.println("Test printing the fields");
		System.out.println("ID: " + this.identifier);
		System.out.println("Acceleration: " + this.acceleration);
		System.out.println("Speed: " + this.speed);
		System.out.println("Up Angle: " + this.upAngle);
		System.out.println("Down Angle: " + this.downAngle);
	}
	
}
