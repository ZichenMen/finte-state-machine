import java.util.*;

public class FSMDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            FiniteStateMachine fsm = new FiniteStateMachine();

            int numberOfStates = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < numberOfStates; i++) {
                fsm.addState(i); // Add states to FSM
            }

            String alphabet = scanner.nextLine().trim();
            for (char symbol : alphabet.toCharArray()) {
                fsm.addAlphabet(symbol); // Add alphabet symbols to FSM
            }

            int numberOfTransitions = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < numberOfTransitions; i++) {
                String[] parts = scanner.nextLine().trim().split(",");
                int fromState = Integer.parseInt(parts[0].trim());
                Character input = parts[1].isEmpty() ? null : parts[1].charAt(0);
                int toState = Integer.parseInt(parts[2].trim());

                fsm.addTransition(fromState, input, toState); // Add transitions to FSM
            }

            fsm.setStartState(Integer.parseInt(scanner.nextLine().trim())); // Set start state

            String[] finalStates = scanner.nextLine().trim().split(",");
            for (String state : finalStates) {
                fsm.addFinalState(Integer.parseInt(state.trim())); // Add final states
            }

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            System.out.println("Finite state machine is " + (fsm.IsDFA() ? "DFA" : "NFA"));

            // Process test strings
            while (scanner.hasNextLine()) {
                String testString = scanner.nextLine().trim();
                
                if (testString.isEmpty()) {
                    break;
                }

                boolean accepted = fsm.Execute(testString);
                int[] path = {};
                if (accepted) {
                    path = fsm.Path(testString);
                }
                System.out.println("Input String: \"" + testString + "\" is " + (accepted ? "Accepted and the path is " + Arrays.toString(path) : "Not accepted"));
            }

            System.out.println();
            
        }

        scanner.close();
    }
}
