package code;

import java.util.List;
import code.TownState;
import code.TownStateParser;

public class main {
    public static void main(String[] args) {

        String initialStateStr = "40;30,20,25;2,3,4;5,2;3,1;4,2;10,2,3,4,5;12,3,5,6,7;";
        String strategy = "BF";
        boolean visualize = true;

        TownState state = TownStateParser.parseInitialState(initialStateStr);

       
    }
}
