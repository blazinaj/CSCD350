package s19cs350task4;

public class MyComponentNonlinear implements I_Component {

    private String id;
    private double stateCurrent;
    private double stateStart;
    private double stateEnd;
    private double step;
    private double stepAcceleration;
    private boolean dead = false;
    private boolean dying = false;
    private int polarity = 1;
    private int terminateCounter = 3;

    public MyComponentNonlinear(String id, double stateStart, double stateEnd, double step, double stepAcceleration) {
        if (id.equals(null) || id.isEmpty()){
            throw new RuntimeException("Error: Id cannot be null or empty");
        }
        this.id = id;
        this.stateStart = stateStart;
        this.stateCurrent = stateStart;
        this.stateEnd = stateStart;
        this.step = step;
        this.stepAcceleration = stepAcceleration;
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
        return false;
    }

    @Override
    public void cancel_() {
        this.dead = true;
    }

    @Override
    public void terminate_() {
        this.dying = true;
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
