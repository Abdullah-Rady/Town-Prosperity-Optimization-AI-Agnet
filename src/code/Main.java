package code;

public class Main {

    public static void main(String[] args) {

        String initialStateStr = "29;" +
                "14,9,26;" +
                "650,400,710;" +
                "20,2;29,2;38,1;" +
                "8255,8,7,9,36;" +
                "30670,12,12,11,36;";

        String strategy = "BF";
        boolean visualize = true;
        System.out.println(LLAPSearch.solve(initialStateStr, strategy, visualize));
    }
}