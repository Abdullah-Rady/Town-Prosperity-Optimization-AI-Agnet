package code;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.Action;

import code.TownStateParser;
import code.Actions;

public class main {
    public static void main(String[] args) {

        String initialStateStr = "50;" +
                "22,22,22;" +
                "50,60,70;" +
                "30,2;19,1;15,1;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";

        String strategy = "BF";

        boolean visualize = true;

        TownConstants constants = TownStateParser.parseInitialState(initialStateStr);
        TownNodeAction a = new TownNodeAction(constants);

        Node initial = new Node(constants.getInitialState(), null, null, 0, 0);
        Stack<Node> stack = new Stack<>();
        Set<TownSearchNode> visited = new HashSet<>();

        stack.push(initial);

        while (!stack.isEmpty()) {

            Node currentNode = stack.pop();
            TownSearchNode currentState = currentNode.state;

            if (currentNode.isGoal()) {
                // If the goal is reached, reconstruct and return the path
                return;
            }

            visited.add(currentState);

            for (Actions action : Actions.values()) {

                int actionValue = action.getValue();
                if (a.checkAction(actionValue, currentState)
                        && !visited.contains(a.preformAction(actionValue, currentState))) {
                    stack.push(new Node(a.preformAction(actionValue, currentState), currentNode, action,
                            currentNode.depth + 1, currentNode.pathCost + 1));
                }

            }

        }
    }
}
