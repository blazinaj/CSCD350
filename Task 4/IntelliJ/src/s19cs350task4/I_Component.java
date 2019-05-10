package s19cs350task4;
/*
* Jacob Blazina
*/
public interface I_Component {
    /**
     * Gets the arbitrary nonempty identifier of this component.
     * @return
     */
    String getID_();

    /**
     * Gets the current state, which is always between
     * the start and end states.
     * @return
     */
    double getState_();

    /**
     * Gets the initial state that this state assumes.
     * @return
     */
    double getStateStart_();

    /**
     * Gets the final state that this state can assume.
     * @return
     */
    double getStateEnd_();

    /**
     * Gets the value of the transition step between
     * the start and end states. It is always positive.
     * @return
     */
    double getStep_();

    /**
     * Updates the state from its current state to its next
     * state based on the current step. If the next state
     * exceeds the end state, then the former is clamped to the latter.
     * This returns whether the end state has been reached.
     * @return
     */
    boolean updateState_();

    /**
     * Immediately stops the component from servicing calls to updateState_().
     */
    void cancel_();

    /**
     * Stops the component from servicing calls to updateState_()
     * with a notional gradual shutdown. For linear components,
     * reverse the step and cease servicing three calls after receipt
     * of this terminate signal.
     * For nonlinear components, also reduce the step by half each time.
     */
    void terminate_();

    /**
     * Returns whether the component is being terminated.
     * @return
     */
    boolean isDying_();

    /**
     * Returns whether the component has been canceled or terminated.
     * @return
     */
    boolean isDead_();
}
