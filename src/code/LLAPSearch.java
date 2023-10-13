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

public class LLAPSearch extends GenericSearch {
    /**
     * @param initialStateStr
     * @param strategy
     * @param visualize
     * @return
     */

     static TownNodeAction transitioner;
     static boolean visualize;

    public String UC(Node initial) {

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
                if (transitioner.checkAction(actionValue, currentState)
                        && !visited.contains(transitioner.preformAction(actionValue, currentState))) {
                    queue.add(new Node(transitioner.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }
        }
        return "NOSOLUTION";
    }

    public String ID(Node initial) {
        int depth = 0;
        while (true) {
            String result = DLS(initial, depth);
            if (!result.equals("NOSOLUTION")) {
                return result;
            }
            depth++;
        }
    }

    public String DLS(Node initial, int depth) {

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
                if (transitioner.checkAction(actionValue, currentState)
                        && !visited.contains(transitioner.preformAction(actionValue, currentState))) {
                    stack.push(new Node(transitioner.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }

        return "NOSOLUTION";

    }

    public String BFS(Node initial) {

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
                if (transitioner.checkAction(actionValue, currentState)
                        && !visited.contains(transitioner.preformAction(actionValue, currentState))) {
                    stack.add(new Node(transitioner.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }
        return "NOSOLUTION";
    }

    public String DFS(Node initial) {

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
                if (transitioner.checkAction(actionValue, currentState)
                        && !visited.contains(transitioner.preformAction(actionValue, currentState))) {
                    stack.push(new Node(transitioner.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }

        return "NOSOLUTION";

    }

    public String tracePath(Node node, int nodesExpanded) {

        List<String> plan = new ArrayList<>();

        double monetaryCost = 0.0;

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

    public String Solver(String strategy, Node initialNode){
        switch (strategy) {
            case "BF":
                return BFS(initialNode);
            case "DF":
                return DFS(initialNode);
            case "ID":
                return ID(initialNode);
            case "UC":
                return UC(initialNode);
            // case "AS1":
            //     return AStar(initialNode, transitioner);
            // case "AS2":
            //     return AStar(initialNode, transitioner);
            // case "G1":
            //     return Greedy(initialNode, transitioner);
            // case "G2":
            //     return Greedy(initialNode, transitioner);
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);
        }
    }
   
    @Override
    public String solve(String initialStateStr, String strategy, Boolean visualizein) {
        TownConstants constants = TownStateParser.parseInitialState(initialStateStr);
        TownSearchNode initialState = constants.getInitialState();
        transitioner = new TownNodeAction(constants);
        Node initialNode = new Node(initialState, null, null, 0, 0);
        visualize = visualizein;
        System.out.println(visualize);

        return Solver(strategy, initialNode);
    }
}

class UCComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        if (node1.pathCost < node2.pathCost) {
            return -1;
        } else if (node1.pathCost > node2.pathCost) {
            return 1;
        }
        return 0;
    }
}
