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

import code.TownStateParser;

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

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    queue.add(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
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

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    stack.push(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
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

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    stack.add(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
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

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            visited.add(currentState);
            nodesExpanded++;

            for (Actions action : Actions.values()) {

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    stack.push(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }

        return "NOSOLUTION";

    }

    public static String tracePath(Node node, int nodesExpanded) {

        List<String> plan = new ArrayList<>();

        int monetaryCost = 0;

        Node currentNode = node;

        while (currentNode != null) {
            if (currentNode.action != null) {
                plan.add(currentNode.action.toString());
                monetaryCost += currentNode.state.moneySpent;
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

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    queue.add(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
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

                int actionValue = action.getValue();
                if (agent.checkAction(actionValue, currentState)
                        && !visited.contains(agent.preformAction(actionValue, currentState))) {
                    queue.add(new Node(agent.preformAction(actionValue, currentState), currentNode, action,
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
            case "G1":
                return Greedy(initialNode, true);
            case "G2":
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
