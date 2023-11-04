package code;

public class Main {

    public static void main(String[] args) {

        String initialStateStr = "29;" +
                "14,9,26;" +
                "650,400,710;" +
                "20,2;29,2;38,1;" +
                "8255,8,7,9,36;" +
                "30670,12,12,11,36;";
        String initialState10 = "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";

        String strategy = "BF";
        boolean visualize = true;
        System.out.println(LLAPSearch.solve(initialState10, strategy, visualize));
    }
}
