package code;

import java.util.*;

public class TownSolver {

    // Define the state of the town


    /**
     * Define the search strategy interface.
     */
    interface SearchStrategy {
        /**
         * Search for a sequence of steps to help the town achieve prosperity.
         *
         * @param initialState The initial state of the town.
         * @return A list of steps to achieve prosperity, or null if no sequence is
         *         found.
         */
        List<String> search(TownState initialState);
    }

    // TODO: Implement BFS search strategy
    static class BFS implements SearchStrategy {
        @Override
        public List<String> search(TownState initialState) {
            // TODO: Implement BFS search algorithm here
            // ...
            return null;
        }
    }

    // TODO: Implement DFS search strategy
    static class DFS implements SearchStrategy {
        @Override
        public List<String> search(TownState initialState) {
            // TODO: Implement DFS search algorithm here
            // ...
            return null;
        }
    }

    // TODO: Implement ID search strategy
    static class ID implements SearchStrategy {
        @Override
        public List<String> search(TownState initialState) {
            // TODO: Implement iterative deepening search algorithm here
            // ...
            return null; // Replace with the actual sequence of steps
        }
    }

    // TODO: Implement UC search strategy
    static class UC implements SearchStrategy {
        @Override
        public List<String> search(TownState initialState) {
            // TODO: Implement uniform cost search algorithm here
            // ...
            return null; // Replace with the actual sequence of steps
        }
    }

    // TODO: Implement Greedy search strategy (GR1 and GR2)
    static class Greedy implements SearchStrategy {
        private int heuristicChoice;

        Greedy(int heuristicChoice) {
            this.heuristicChoice = heuristicChoice;
        }

        @Override
        public List<String> search(TownState initialState) {
            // TODO:Implement greedy search algorithm with specified heuristic
            // ...
            return null; // Replace with the actual sequence of steps
        }
    }

    // TODO: Implement A* search strategy (AS1 and AS2)
    static class AStar implements SearchStrategy {
        private int heuristicChoice;

        AStar(int heuristicChoice) {
            this.heuristicChoice = heuristicChoice;
        }

        @Override
        public List<String> search(TownState initialState) {
            // TODO : Implement A* search algorithm with specified heuristic
            // ...
            return null;
        }
    }

    /**
     * Instantiate a search strategy based on the input string.
     *
     * @param strategy The chosen search strategy (e.g., "BF" for BFS).
     * @return An instance of the search strategy.
     * @throws IllegalArgumentException If an invalid search strategy is provided.
     */
    public static SearchStrategy getStrategy(String strategy) {

        SearchStrategy searchStrategy;

        switch (strategy) {
            case "BF":
                searchStrategy = new BFS();
                break;
            case "DF":
                searchStrategy = new DFS();
                break;
            case "ID":
                searchStrategy = new ID();
                break;
            case "UC":
                searchStrategy = new UC();
                break;
            case "GR1":
                searchStrategy = new Greedy(1);
                break;
            case "GR2":
                searchStrategy = new Greedy(2);
                break;
            case "AS1":
                searchStrategy = new AStar(1);
                break;
            case "AS2":
                searchStrategy = new AStar(2);
                break;
            default:
                throw new IllegalArgumentException("Invalid search strategy: " + strategy);
        }

        return searchStrategy;
    }

    /**
     * Solve the problem of helping the town achieve prosperity.
     *
     * @param initialStateStr The initial state of the town in string format.
     * @param strategy        The chosen search strategy (e.g., "BF" for BFS).
     * @param visualize       Whether to enable visualization (true/false).
     * @return A list of steps to achieve prosperity, or null if no sequence is
     *         found.
     * @throws IllegalArgumentException If an invalid search strategy is provided.
     */

    public static List<String> solve(String initialStateStr, String strategy, Boolean visualize) {

        // Parse the initial state string and create the initial town state object
        TownState initialState = null;

        // Create the appropriate search strategy based on the input string
        SearchStrategy searchStrategy = getStrategy(strategy);

        // Use the selected search strategy to find a sequence of steps
        List<String> steps = searchStrategy.search(initialState);

        return steps;
    }

    public static void main(String[] args) {

        String initialStateStr = "40;30,20,25;2,3,4;5,2;3,1;4,2;10,2,3,4,5;12,3,5,6,7;";
        String strategy = "BF";
        boolean visualize = true;

        List<String> sequence = solve(initialStateStr, strategy, visualize);

        if (sequence != null) {
            System.out.println("Sequence of steps to achieve prosperity:");
            for (String step : sequence) {
                System.out.println(step);
            }
        } else {
            System.out.println("No sequence found to achieve prosperity.");
        }
    }
}
