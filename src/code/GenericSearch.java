package code;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class GenericSearch {



    public String solve(String initialStateStr, String strategy, Boolean visualize){

        Queue<Node> nodes = new LinkedList<>();
        
        nodes.add(new Node());

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            if (node.isGoal()) {
                return node.steps();
            }

            nodes = enqueue(nodes, node); // Assuming OPER is a function that generates the successors of a node
        }

        return "NOSOLUTION"; // Return null if no solution is found
    }

    public Queue<Node> enqueue(Queue<Node> nodes , Node node){
        // TO DO
        return new LinkedList<>();
    }

}
