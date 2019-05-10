package s19cs350task4.tests;

import org.junit.jupiter.api.Test;
import s19cs350task4.MyComponentNonlinear;

import static org.junit.jupiter.api.Assertions.*;

class MyComponentNonlinearTest {

    @Test
    void getID_() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals("Test ID", test.getID_());
    }

    @Test
    void getState_() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals(0, test.getState_());
    }

    @Test
    void getStateStart_() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals(0, test.getStateStart_());
    }

    @Test
    void getStateEnd_() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals(10, test.getStateEnd_());
    }

    @Test
    void getStep_() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals(1, test.getStep_());
    }

    @Test
    void component_initializes_correctly() {
        MyComponentNonlinear test = new MyComponentNonlinear("Test ID", 0, 10, 1, 0.25);

        assertEquals("Test ID", test.getID_());
        assertEquals(0, test.getStateStart_());
        assertEquals(10, test.getStateEnd_());
        assertEquals(1, test.getStep_());
    }

    @Test
    void constructor_id_invalid_string_empty_failure() {
        assertThrows(RuntimeException.class,
                () -> {
                    new MyComponentNonlinear("", 0, 10, 1, 0.25);
                });
    }

    @Test
    void constructor_id_invalid_string_null_failure() {
        assertThrows(RuntimeException.class,
                () -> {
                    new MyComponentNonlinear(null, 0, 10, 1, 0.25);
                });
    }

    @Test
    void updateState_increments_one_step_positive_success() {
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 0.0, 10.0, 1, 0.25);

        assertEquals(0, test.getState_());

        test.updateState_();

        assertEquals(1, test.getState_());
    }

    @Test
    void updateState_increments_one_step_negative_success() {
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 10.0, 0.0, 1, 0.25);

        assertEquals(10.0, test.getState_());

        test.updateState_();

        assertEquals(9.0, test.getState_());
    }

    @Test
    void updateState_does_not_reach_stateEnd_returns_false(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 10.0, 0.0, 1, 0.25);

        assertEquals(false, test.updateState_());
    }

    @Test
    void updateState_reaches_stateEnd_returns_true(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 0.0, 1.0, 1, 0.25);

        assertEquals(true, test.updateState_());
    }

    @Test
    void cancel_stops_servicing_updateState_not_at_stateEnd_success(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 0.0, 3.0, 1, 0.25);

        test.updateState_();

        assertEquals(1, test.getState_());

        test.updateState_();

        assertEquals(2.25, test.getState_());

        test.cancel_();

        test.updateState_();
        test.updateState_();

        assertEquals(2.25, test.getState_());
        assertEquals(false, test.updateState_());
    }

    @Test
    void cancel_stops_servicing_updateState_at_stateEnd_success(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 2.25, 0.0, 1, 0.25);

        test.updateState_();
        assertEquals(1.25, test.getState_());

        assertTrue(test.updateState_());
        assertEquals(0.0, test.getState_());

        assertEquals(0.0, test.getState_());

        assertFalse(test.isDead_());

        test.cancel_();

        assertTrue(test.isDead_());

        assertTrue(test.updateState_());
        assertTrue(test.updateState_());

        assertEquals(0.0, test.getState_());
    }

    @Test
    void updateState_does_not_overstep_endState_success(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 0.0, 5.0, 6, 0.25);

        assertTrue(test.updateState_());
        assertEquals(5, test.getState_());
    }

    @Test
    void terminate_gradually_shuts_down_positive_polarity_success(){
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 0.0, 10.0, 1, 0.25);

        //4 ticks, then terminate, then should go backwards three more ticks
        test.updateState_();
        assertEquals(1, test.getState_());

        test.updateState_();
        assertEquals(2.25, test.getState_());

        test.updateState_();
        assertEquals(3.75, test.getState_());

        test.updateState_();
        assertEquals(5.50, test.getState_());

        // Call terminate
        test.terminate_();
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 1
        test.updateState_();
        assertEquals(3.5, test.getState_());
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 2
        test.updateState_();
        assertEquals(2.5, test.getState_());
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 3, now is dead
        test.updateState_();
        assertEquals(2.0, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());

        // Counter is up, now component is Dead
        test.updateState_();
        assertEquals(2.0, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());

        test.updateState_();
        assertEquals(2.0, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());
    }

    @Test
    void terminate_gradually_shuts_down_negative_polarity_success() {
        MyComponentNonlinear test = new MyComponentNonlinear("Tester", 10.0, 0.0, 1, 0.25);

        //4 ticks, then terminate, then should go backwards three more ticks
        test.updateState_();
        assertEquals(9, test.getState_());

        test.updateState_();
        assertEquals(7.75, test.getState_());

        test.updateState_();
        assertEquals(6.25, test.getState_());

        test.updateState_();
        assertEquals(4.5, test.getState_());

        // Call terminate
        test.terminate_();
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 1
        test.updateState_();
        assertEquals(6.5, test.getState_());
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 2
        test.updateState_();
        assertEquals(7.5, test.getState_());
        assertTrue(test.isDying_());
        assertFalse(test.isDead_());

        // Reverse 3, then sets to dead
        test.updateState_();
        assertEquals(8, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());

        // Counter is up, now component is Dead
        test.updateState_();
        assertEquals(8, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());

        test.updateState_();
        assertEquals(8, test.getState_());
        assertTrue(test.isDying_());
        assertTrue(test.isDead_());
    }
}