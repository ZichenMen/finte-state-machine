import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FiniteStateMachineTest {

    private FiniteStateMachine fsm;

    @BeforeEach
    void setUp() {
        fsm = new FiniteStateMachine();
        fsm.addState(0);
        fsm.addState(1);
        fsm.addState(2);
        fsm.addAlphabet('a');
        fsm.addAlphabet('b');
        fsm.setStartState(0);
        fsm.addFinalState(2);
        fsm.addTransition(0, 'a', 1);
        fsm.addTransition(1, 'b', 2);
    }

    @Test
    void testExecuteAcceptedString() {
        assertTrue(fsm.Execute("ab"), "FSM should accept the string 'ab'.");
    }

    @Test
    void testExecuteRejectedString() {
        assertFalse(fsm.Execute("ba"), "FSM should reject the string 'ba'.");
    }

    @Test
    void testPathForAcceptedString() {
        int[] expectedPath = {0, 1, 2};
        assertArrayEquals(expectedPath, fsm.Path("ab"), "Path should be 0->1->2 for the string 'ab'.");
    }

    @Test
    void testPathForRejectedString() {
        assertNull(fsm.Path("ba"), "Path should be null for the rejected string 'ba'.");
    }

    @Test
    void testIsDFAWithDFAConfiguration() {
        assertTrue(fsm.IsDFA(), "FSM should be identified as a DFA with the given configuration.");
    }

    @Test
    void testIsDFAWithNFAConfiguration() {
        fsm.addTransition(0, 'a', 2);
        assertFalse(fsm.IsDFA(), "FSM should not be identified as a DFA after adding an NFA transition.");
    }
}
