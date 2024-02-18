public class UnitTest {
    public static void main(String[] args) {
        FiniteStateMachine fsm = new FiniteStateMachine();
        fsm.addState(0); // Start state
        fsm.addState(1);
        fsm.addFinalState(1); // Final state
        fsm.setStartState(0);
        fsm.addAlphabet('a');
        fsm.addAlphabet('b');
        fsm.addTransition(0, 'a', 1);
        fsm.addTransition(1, 'b', 0);

        // Test cases
        String[] testStrings = {"a", "ab", "aba", "b"};
        boolean[] expectedResults = {true, false, true, false};

        // Execute tests
        for (int i = 0; i < testStrings.length; i++) {
            boolean result = fsm.Execute(testStrings[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test " + (i+1) + " with input \"" + testStrings[i] + "\" passed.");
            } else {
                System.out.println("Test " + (i+1) + " with input \"" + testStrings[i] + "\" failed.");
            }
        }
    }
}
