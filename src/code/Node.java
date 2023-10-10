package code;

import java.util.ArrayList;
import java.util.List;

public class Node {
    TownState state;
    Node parentNode;
    Action action;
    int depth;
    double pathCost;


    public Node(TownState state, Node parentNode, Action action, int depth, double pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public Node (){

    }

    public boolean isGoal(){
        // TODO
        return false;
    }

    public String steps() {
        // TODO
        return "";
    }
}
