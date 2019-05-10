package s19cs350task4;
/*
* Jacob Blazina
*/
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

    /**
     * Constructor
     * @param id
     * @param stateStart
     * @param stateEnd
     * @param step
     */
    public MyComponentLinear(String id, double stateStart, double stateEnd, double step){
        // check is id is null or empty
        if (id.equals(null) || id.isEmpty()) {
            throw new RuntimeException("Error: id must be a valid non-empty string");
        }

        // check if step is not positive
        if (step < 1){
            throw new RuntimeException("Error: step must be a positive number");
        }

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

    /**
     * Gets the arbitrary nonempty identifier of this component.
     * @return
     */
    @Override
    public String getID_() {
        return this.id;
    }

    /**
     * Gets the current state, which is always between the start and end states.
     * @return
     */
    @Override
    public double getState_() {
        return this.stateCurrent;
    }

    /**
     * Gets the current state, which is always between the start and end states.
     * @return
     */
    @Override
    public double getStateStart_() {
        return this.stateStart;
    }

    /**
     * Gets the final state that this state can assume.
     * @return
     */
    @Override
    public double getStateEnd_() {
        return this.stateEnd;
    }

    /**
     * Gets the value of the transition step between the start and end states. It is always positive.
     * @return
     */
    @Override
    public double getStep_() {
        return this.step;
    }

    /**
     * Updates the state from its current state to its next state based on the current step.
     * If the next state exceeds the end state, then the former is clamped to the latter.
     * This returns whether the end state has been reached.
     * @return
     */
    @Override
    public boolean updateState_() {

        // Check if it is dying and decrement counter
        if (this.dying && this.terminateCounter > 0) {
            this.terminateCounter--;
        }

        // Already at end
        if (this.stateCurrent == this.stateEnd) {
            return true;
        }

        // If cancelled, return if end has been reached
        if (this.dead) {
            return this.stateCurrent == this.stateEnd;
        }

        // If original polarity is negative
        if (this.stateEnd - this.stateStart > 0){
            if (this.stateCurrent + (this.polarity * this.step) >= this.stateEnd) {
                this.stateCurrent = this.stateEnd;
                this.dead = true;
                return true;
            }
        }
        // If original polarity is positive
        else if (this.stateEnd - this.stateStart < 0){
            if (this.stateCurrent + (this.polarity * this.step) <= this.stateEnd) {
                this.stateCurrent = this.stateEnd;
                this.dead = true;
                return true;
            }
        }

        // Increment current state by 1 step
        this.stateCurrent += this.polarity * this.step;

        if (this.terminateCounter == 0){
            this.dead = true;
        }

        // Updated fine, and not at endState, return false
        return false;

    }

    /**
     * Immediately stops the component from servicing calls to updateState_().
     */
    @Override
    public void cancel_() {
        // deactivate updates
        this.dead = true;
    }

    /**
     * Stops the component from servicing calls to updateState_() with a notional gradual shutdown.
     * For linear components, reverse the step and cease servicing three calls after receipt of this terminate signal.
     * For nonlinear components, also reduce the step by half each time.
     */
    @Override
    public void terminate_() {
        // sets dying to true and reverses step polarity
        this.dying = true;
        this.polarity = this.polarity * -1;
    }

    /**
     * Returns whether the component is being terminated.
     * @return
     */
    @Override
    public boolean isDying_() {
        return this.dying;
    }

    /**
     * Returns whether the component has been canceled or terminated.
     * @return
     */
    @Override
    public boolean isDead_() {
        return this.dead;
    }
}
