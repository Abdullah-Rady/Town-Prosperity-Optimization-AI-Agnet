package code;

import java.util.ArrayList;
import java.util.List;
import code.TownSearchNode;

public class Node {
    TownSearchNode state;
    Node parentNode;
    Actions action;
    int depth;
    int pathCost;

    public Node(TownSearchNode state, Node parentNode, Actions action, int depth, int pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public Node() {

    }

    public boolean isGoal() {
        return state.isProsperous();
    }

    public String steps() {
        // TO DO
        return "";
    }
}
