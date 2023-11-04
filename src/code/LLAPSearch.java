package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static code.LLAPSearch.townConstants;

// there is a problem when preforming/checking actions because objects are passed by refrence rather than value and 
// in actions we alter the state so this could have an unexpected side effect
// we can fix this by creating a new state in the preform action method and returing the new state

// after preforming actions there is a difference of 1 between the actual value and the value that shouldve been returned 
// ex. if food after build should be 5,6 is found instead

// when running the case in main the code adds unreachable states this could be due to the check action method
// when something is printed in a preform action it is printed twice for some reason

// based on the issues above i think the problem is in the either the agent class or the solving strategies themselves

// i checked all of the other code and it seems to be working fine apart from the issues above

public class LLAPSearch extends GenericSearch {
    /**
     * @param initialStateStr
     * @param strategy
     * @param visualize
     * @return
     */

    static TownAgent agent;
    static TownConstants townConstants;
    static boolean visualize;

    public static String UC(Node initial) {

        PriorityQueue<Node> queue = new PriorityQueue<>(new UCComparator());
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        queue.add(initial);

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    queue.add(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }
        }
        return "NOSOLUTION";
    }

    public static String ID(Node initial) {
        int depth = 0;
        while (true) {
            String result = DLS(initial, depth);
            if (!result.equals("NOSOLUTION")) {
                return result;
            }
            depth++;
        }
    }

    public static String DLS(Node initial, int depth) {

        Stack<Node> stack = new Stack<>();
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        stack.push(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.pop();

            if (currentNode.depth > depth) {
                continue;
            }

            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    stack.push(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }

        return "NOSOLUTION";

    }

    public static String BFS(Node initial) {

        Queue<Node> stack = new LinkedList<>();
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        stack.add(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.poll();
            TownSearchNode currentState = currentNode.state;

            
            if (visualize){
                System.out.println(currentNode.depth);
                System.out.println(tracePath(currentNode, nodesExpanded));
                System.out.println(currentNode.action);
                System.out.println(currentState);
            }

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    stack.add(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }
        return "NOSOLUTION";
    }

    public static String DFS(Node initial) {

        Stack<Node> stack = new Stack<>();
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        stack.push(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.pop();
            TownSearchNode currentState = currentNode.state;

            if (visualize){
                System.out.println(currentNode.depth);
                System.out.println(tracePath(currentNode, nodesExpanded));
                System.out.println(currentNode.action);
                System.out.println(currentState);
            }

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {
              System.out.println("action "+action + " " + agent.checkAction(action, currentState));
                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    stack.push(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                           
                }

            }

        }

        return "NOSOLUTION";

    }

    public static String tracePath(Node node, int nodesExpanded) {

        List<String> plan = new ArrayList<>();

        int monetaryCost = node.state.moneySpent;

        Node currentNode = node;

        while (currentNode != null) {
            if (currentNode.action != null) {
                plan.add(currentNode.action.toString());
            }
            currentNode = currentNode.parentNode;
        }

        Collections.reverse(plan);

        return String.join(",", plan) + ";" + monetaryCost + ";" + nodesExpanded;
    }

    public static String AStar(Node initial, boolean firstHeuristic) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new AstarComparator(firstHeuristic));
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        queue.add(initial);

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    queue.add(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }
        }
        return "NOSOLUTION";
    }

    public static String Greedy(Node initial, boolean firstHeuristic) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new GreedyComparator(firstHeuristic));
        Set<TownSearchNode> visited = new HashSet<>();
        int nodesExpanded = 0;

        queue.add(initial);

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)
                        && !visited.contains(agent.preformAction(action, currentState))) {
                    queue.add(new Node(agent.preformAction(action, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }
        }
        return "NOSOLUTION";
    }

    public static String Solver(String strategy, Node initialNode) {
        switch (strategy) {
            case "BF":
                return BFS(initialNode);
            case "DF":
                return DFS(initialNode);
            case "ID":
                return ID(initialNode);
            case "UC":
                return UC(initialNode);
            case "AS1":
                return AStar(initialNode, true);
            case "AS2":
                return AStar(initialNode, false);
            case "GR1":
                return Greedy(initialNode, true);
            case "GR2":
                return Greedy(initialNode, false);
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);
        }
    }

    
    public static String solve(String initialStateStr, String strategy, Boolean visualizein) {
        townConstants = TownStateParser.parseInitialState(initialStateStr);
        TownSearchNode initialState = townConstants.getInitialState();
        agent = new TownAgent(townConstants);
        Node initialNode = new Node(initialState, null, null, 0, 0);
        visualize = visualizein;

        return Solver(strategy, initialNode);
    }
}

class UCComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node1.pathCost - node2.pathCost;
    }
}

class AstarComparator implements Comparator<Node> {
    boolean firstHeuristic;

    public AstarComparator(boolean firstHeuristic) {
        this.firstHeuristic = firstHeuristic;
    }

    @Override
    public int compare(Node node1, Node node2) {

        return new Transitioner(firstHeuristic).calculateHeuristic(node1.action.getValue(), townConstants)
                - new Transitioner(firstHeuristic).calculateHeuristic(node2.action.getValue(), townConstants)
                + (int) (node1.pathCost - node2.pathCost);
    }
}

class GreedyComparator implements Comparator<Node> {
    boolean firstHeuristic;

    public GreedyComparator(boolean firstHeuristic) {
        this.firstHeuristic = firstHeuristic;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return new Transitioner(firstHeuristic).calculateHeuristic(node1.action.getValue(), townConstants)
                - new Transitioner(firstHeuristic).calculateHeuristic(node2.action.getValue(), townConstants);

    }
}
