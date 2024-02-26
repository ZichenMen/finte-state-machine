import java.util.*;

public class FiniteStateMachine {
    private Set<Integer> states;
    private Set<Character> alphabet;
    private Map<Integer, Map<Character, Set<Integer>>> transitionTable;
    private int startState;
    private Set<Integer> finalStates;

    public FiniteStateMachine() {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.transitionTable = new HashMap<>();
        this.finalStates = new HashSet<>();
    }

    public void addState(int state) {
        states.add(state);
    }

    public void addAlphabet(char symbol) {
        alphabet.add(symbol);
    }

    public void addTransition(int fromState, char input, int toState) {
        transitionTable.putIfAbsent(fromState, new HashMap<>());
        Map<Character, Set<Integer>> transitions = transitionTable.get(fromState);
        transitions.putIfAbsent(input, new HashSet<>());
        transitions.get(input).add(toState);
    }

    public void setStartState(int startState) {
        this.startState = startState;
    }

    public void addFinalState(int state) {
        finalStates.add(state);
    }

    public boolean Execute(String s) {
        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(startState);
        for (char symbol : s.toCharArray()) { // iterate every chars of the string
            Set<Integer> nextStates = new HashSet<>();
            for (int state : currentStates) {
                Set<Integer> transitions = transitionTable.getOrDefault(state, new HashMap<>()).get(symbol);
                if (transitions != null) {
                    nextStates.addAll(transitions);
                }
            }
            if (nextStates.isEmpty()) return false; // No valid transition
            currentStates = nextStates;
        }
        // If any of the current states is a final state
        for (int state : currentStates) {
            if (finalStates.contains(state)) return true;
        }
        return false;
    }

    public int[] Path(String s) {
        List<Integer> path = new ArrayList<>();
        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(startState);
        path.add(startState);

        for (char symbol : s.toCharArray()) {
            Set<Integer> nextStates = new HashSet<>();
            boolean transitionMade = false;
            for (int state : currentStates) {
                Set<Integer> transitions = transitionTable.getOrDefault(state, new HashMap<>()).get(symbol);
                if (transitions != null && !transitions.isEmpty()) {
                    int nextState = transitions.iterator().next();
                    nextStates.add(nextState);
                    if (!transitionMade) {
                        path.add(nextState);
                        transitionMade = true;
                    }
                }
            }
            if (nextStates.isEmpty()) break; // Stop if no valid transitions
            currentStates = nextStates;
        }
        return path.stream().mapToInt(i -> i).toArray();
    }

    public boolean IsDFA() {
        for (Map.Entry<Integer, Map<Character, Set<Integer>>> entry : transitionTable.entrySet()) {
            for (Set<Integer> transitions : entry.getValue().values()) {
                if (transitions.size() > 1) { // if NFA, return false
                    return false;
                }
            }
        }
        return true;
    }
}
