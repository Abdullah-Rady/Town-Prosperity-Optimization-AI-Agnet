package code;

import java.util.Comparator;

class GreedyComparator implements Comparator<Node> {
    boolean firstHeuristic;

    public GreedyComparator(boolean firstHeuristic) {
        this.firstHeuristic = firstHeuristic;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return new Transitioner(firstHeuristic).calculateHeuristic(node1.action.getValue(), GenericSearch.townConstants)
                - new Transitioner(firstHeuristic).calculateHeuristic(node2.action.getValue(),
                        GenericSearch.townConstants);

    }
}