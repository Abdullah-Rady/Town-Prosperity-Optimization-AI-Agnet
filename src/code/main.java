package code;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
        System.out.println(LLAPSearch.solve(initialStateStr, strategy, visualize));


    }
}
