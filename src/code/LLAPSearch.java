package code;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class LLAPSearch extends GenericSearch {
    /**
     * @param initialStateStr
     * @param strategy
     * @param visualize
     * @return
     */

    static TownAgent agent;
    public static TownConstants townConstants;
    static boolean visualize;

    public static String UC(Node initial) {
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

    public static String AStar(Node initial, boolean firstHeuristic) {
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

}
