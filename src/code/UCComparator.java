package code;

import java.util.Comparator;

public class UCComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node1.pathCost - node2.pathCost;
    }
}
