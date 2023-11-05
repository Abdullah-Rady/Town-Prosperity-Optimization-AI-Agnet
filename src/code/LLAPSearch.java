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
    static boolean isDFS = false;

    public static String UC(Node initial) {
        isDFS = false;
        PriorityQueue<Node> queue = new PriorityQueue<>(new UCComparator());
        Set<String> visited = new HashSet<>();
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

            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    queue.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
                }

            }
        }
        return "NOSOLUTION";
    }

    public static String ID(Node initial) {
        isDFS = false;
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
        isDFS = false;
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
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

            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    stack.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
                }

            }

        }

        return "NOSOLUTION";

    }

    public static String BFS(Node initial) {
        isDFS = false;
        Queue<Node> stack = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int nodesExpanded = 0;

        stack.add(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.poll();

            TownSearchNode currentState = currentNode.state;

            if (visualize) {
                System.out.println(tracePath(currentNode, nodesExpanded));
                System.out.println(currentState);
            }

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    stack.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
                }

            }

        }
        return "NOSOLUTION";
    }

    public static String DFS(Node initial) {
        isDFS = true;
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        int nodesExpanded = 0;

        stack.push(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.pop();
            TownSearchNode currentState = currentNode.state;

            if (visualize) {
                System.out.println(tracePath(currentNode, nodesExpanded));
                System.out.println(currentState);
            }

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            nodesExpanded++;

            for (Actions action : Actions.valuesReversed()) {
                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    stack.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
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
        isDFS = false;
        PriorityQueue<Node> queue = new PriorityQueue<>(new AstarComparator(firstHeuristic));
        Set<String> visited = new HashSet<>();
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

            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    queue.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
                }

            }
        }
        return "NOSOLUTION";
    }

    public static String Greedy(Node initial, boolean firstHeuristic) {
        isDFS = false;
        PriorityQueue<Node> queue = new PriorityQueue<>(new GreedyComparator(firstHeuristic));
        Set<String> visited = new HashSet<>();
        int nodesExpanded = 0;

        queue.add(initial);

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                System.out.println("Depth " + currentNode.depth);
                return tracePath(currentNode, nodesExpanded);
            }

            nodesExpanded++;

            for (Actions action : Actions.values()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    queue.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
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
