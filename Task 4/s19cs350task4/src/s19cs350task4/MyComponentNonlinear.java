package s19cs350task4;

public class MyComponentNonlinear implements I_Component {

	private String id;
	private double stateStart;
	private double stateEnd;
	private double step;
	private double stepAcceleration;
	
	public MyComponentNonlinear(String id, double stateStart, double stateEnd, double step, double stepAcceleration){
		this.id = id;
		this.stateStart = stateStart;
		this.stateEnd = stateEnd;
		this.step = step;
		this.stepAcceleration = stepAcceleration;
	}
	
	@Override
	public String getID_() {
		return this.id;
	}

	@Override
	public double getState_() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStateStart_() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStateEnd_() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStep_() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateState_() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cancel_() {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate_() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDying_() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDead_() {
		// TODO Auto-generated method stub
		return false;
	}

}
