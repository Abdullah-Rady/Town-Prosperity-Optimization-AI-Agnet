package code;

import java.util.ArrayList;
import java.util.List;
import code.TownState;


public class Node {
    TownState state;
    Node parentNode;
    Actions action;
    int depth;
    double pathCost;


    public Node(TownState state, Node parentNode, Actions action, int depth, double pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public Node (){

    }

    public boolean isGoal(){
        return state.isProsperous();
    }

    public String steps() {
        // TO DO
        return "";
    }
}
