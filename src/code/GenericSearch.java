package code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class GenericSearch {
    static TownAgent agent;
    static TownConstants townConstants;
    static boolean visualize;
    static int IdDepth = -1;

    public static String solve(String initialStateStr, String strategy, Boolean visualizein) {
        townConstants = TownStateParser.parseInitialState(initialStateStr);
        TownSearchNode initialState = townConstants.getInitialState();
        agent = new TownAgent(townConstants);

        Collection<Node> abstractDT = createCorrespondingADT(strategy);
        Node initialNode = new Node(initialState, null, null, 0, 0);
        visualize = visualizein;
        if (strategy == "ID")
            return ID(initialNode, abstractDT);
        return genericSearch(initialNode, abstractDT);
    }

    private static Collection<Node> createCorrespondingADT(String strategy) {
        switch (strategy) {
            case "BF":
                return new LinkedList<Node>();
            case "DF":
                return new Stack<Node>();
            case "ID":
                return new Stack<Node>();
            case "UC":
                return new PriorityQueue<Node>(new UCComparator());
            case "AS1":
                return new PriorityQueue<Node>(new AstarComparator(true));
            case "AS2":
                return new PriorityQueue<Node>(new AstarComparator(false));
            case "GR1":
                return new PriorityQueue<Node>(new GreedyComparator(true));
            case "GR2":
                return new PriorityQueue<Node>(new GreedyComparator(false));
        }
        return new Stack<Node>();
    }

    public static String genericSearch(Node initial, Collection<Node> abstractDT) {
        Set<String> visited = new HashSet<>();
        int nodesExpanded = 0;
        abstractDT.add(initial);

        while (!abstractDT.isEmpty()) {

            Node currentNode = getCurrentNode(abstractDT);
            if (currentNode.depth > IdDepth && IdDepth != -1) {
                continue;
            }
            TownSearchNode currentState = currentNode.state;

            if (visualize)
                System.out.println(currentState);

            if (currentNode.isGoal()) {
                return tracePath(currentNode, nodesExpanded);
            }

            nodesExpanded++;

            for (Actions action : Actions.valuesReversed()) {

                if (agent.checkAction(action, currentState)) {
                    TownSearchNode newState = agent.preformAction(action, currentState);
                    if (visited.contains(newState.getHashString()))
                        continue;
                    abstractDT.add(new Node(newState, currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                    visited.add(newState.getHashString());
                }

            }
        }

        return "NOSOLUTION";
    }

    public static String ID(Node initial, Collection<Node> abstractDT) {
        IdDepth = 0;
        while (true) {
            String result = genericSearch(initial, abstractDT);
            if (!result.equals("NOSOLUTION")) {
                IdDepth = -1;
                return result;
            }
            IdDepth++;
        }

    }

    private static Node getCurrentNode(Collection<Node> abstractDT) {
        if (abstractDT instanceof Queue)
            return ((Queue<Node>) abstractDT).poll();
        if (abstractDT instanceof Stack)
            return ((Stack<Node>) abstractDT).pop();
        if (abstractDT instanceof PriorityQueue)
            return ((PriorityQueue<Node>) abstractDT).poll();
        return ((LinkedList<Node>) abstractDT).poll();

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

}
