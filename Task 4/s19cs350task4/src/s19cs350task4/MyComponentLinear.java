package s19cs350task4;

public class MyComponentLinear implements I_Component {

	private String id;
	private double stateCurrent;
	private double stateStart;
	private double stateEnd;
	private double step;
	private boolean dead = false;
	private boolean dying = false;
	private int polarity = 1;
	private int terminateCounter = 3;
	
	public MyComponentLinear(String id, double stateStart, double stateEnd, double step){
		// check is id is null or empty
		this.id = id;
		this.stateCurrent = stateStart;
		this.stateStart = stateStart;
		this.stateEnd = stateEnd;
		this.step = step;
		this.dead = false;
		
		// If negative direction, reverse step polarity
		if (stateEnd - stateStart < 0) {
			this.polarity = -1;
		}
	}
	
	@Override
	public String getID_() {
		return this.id;
	}

	@Override
	public double getState_() {
		return this.stateCurrent;
	}

	@Override
	public double getStateStart_() {
		return this.stateStart;
	}

	@Override
	public double getStateEnd_() {
		return this.stateEnd;
	}

	@Override
	public double getStep_() {
		return this.step;
	}

	@Override
	public boolean updateState_() {
		
		// Check if it is dying and decrement counter
		if (this.dying && this.terminateCounter > 0) {
			this.terminateCounter--;
		}
		
		// If terminateCounter is 0, set dead to true
		if (this.dying && this.terminateCounter < 0) {
			this.dead = true;
		}
		
		// Already at end
		if (this.stateCurrent == this.stateEnd) {
			return true;
		}
		
		// If cancelled, return if end has been reached
		if (this.dead) {
			return this.stateCurrent == this.stateEnd;
		}
		
		// Over steps end state, clamp current to end
		if (this.stateCurrent + this.polarity * this.step > this.stateEnd) {
			this.stateCurrent = this.stateEnd;
			return true;
		}
		
		// Increment current state my 1 step
		this.stateCurrent += this.polarity * this.step;

		return false;
		
	}

	@Override
	public void cancel_() {
		// deactivate updates
		this.dead = true;
	}

	@Override
	public void terminate_() {
		// sets dying to true and reverses step polarity
		this.dying = true;
		this.polarity = this.polarity * -1;
	}

	@Override
	public boolean isDying_() {
		return this.dying;
	}

	@Override
	public boolean isDead_() {
		return this.dead;
	}

}
