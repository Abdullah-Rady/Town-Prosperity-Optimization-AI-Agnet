package code;

public class Main {

        public static void main(String[] args) {

                // String initialStateStr = "29;" +
                // "14,9,26;" +
                // "650,400,710;" +
                // "20,2;29,2;38,1;" +
                // "8255,8,7,9,36;" +
                // "30670,12,12,11,36;";

                // String initialState10 = "32;" +
                // "20,16,11;" +
                // "76,14,14;" +
                // "9,1;9,2;9,1;" +
                // "358,14,25,23,39;" +
                // "5024,20,17,17,38;";
                // String initialState1 = "50;" +
                // "12,12,12;" +
                // "50,60,70;" +
                // "30,2;19,2;15,2;" +
                // "300,5,7,3,20;" +
                // "500,8,6,3,40;";
                String initialState4 = "21;" +
                                "15,19,13;" +
                                "50,50,50;" +
                                "12,2;16,2;9,2;" +
                                "3076,15,26,28,40;" +
                                "5015,25,15,15,38;";

                String strategy = "DF";
                boolean visualize = false;
                System.out.println(LLAPSearch.solve(initialState4, strategy, visualize));
        }
}
