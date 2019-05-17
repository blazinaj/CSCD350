package CommandParser;

public class RudderFactory {
	// These should be changed to the classes defined in sbw.architecture.datatype
	String identifier;
	Double limit;
	Double speed;
	Double acceleration;
	
	public RudderFactory(String input) throws Exception {
		try {
			
			FieldFactory fieldFactory = new FieldFactory(input);
			
			this.identifier = fieldFactory.getIdentifier();
			this.limit = fieldFactory.getUpAngle();
			this.speed = fieldFactory.getSpeed();
			this.acceleration = fieldFactory.getAcceleration();
			
			if (this.identifier.equals(null) || this.limit.equals(null) || this.speed.equals(null) || this.acceleration.equals(null)) {
				throw new Exception("Error: Rudder Not Initialized Properly");
			}
			
			// This is where we would call doCreateRudder()
			Init_Rudder();	
			
		} catch (Exception e) {
			throw new Exception("Error: In RudderFactory, " + e.getMessage(), e);
		}
	}
	
	private void Init_Rudder() {
		// doCreateRudder();
	}
}
