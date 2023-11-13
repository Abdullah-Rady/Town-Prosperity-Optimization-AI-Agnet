package code;

public class Transitioner {
    boolean firstHeuristic;

    public Transitioner(boolean firstHeuristic) {
        this.firstHeuristic = firstHeuristic;
    }

    public int calculateHeuristic(int actionValue, TownConstants constants) {
        if (firstHeuristic)
            return firstHeuristicFunction(actionValue, constants);
        return secondHeuristicFunction(actionValue, constants);
    }

    private int firstHeuristicFunction(int prosperity, TownConstants constants) {
        return getMinBuildsMoney(prosperity, constants);
    }

    public int getMinBuilds(int prosperity, TownConstants constants) {
        return (100 - prosperity) / Math.max(constants.prosperityBUILD1, constants.priceBUILD2);
    }

    public int getMinBuildsMoney(int prosperity, TownConstants constants) {
        return getMinBuilds(prosperity, constants) * Math.min(constants.foodUseBUILD1, constants.foodUseBUILD2);
    }

    public int getMinBuildsFood(int prosperity, TownConstants constants) {
        return getMinBuilds(prosperity, constants) * constants.unitPriceFood;
    }

    public int getMinBuildsMaterials(int prosperity, TownConstants constants) {
        return getMinBuilds(prosperity, constants) * constants.unitPriceMaterials;
    }

    public int getMinBuildsEnergy(int prosperity, TownConstants constants) {
        return getMinBuilds(prosperity, constants) * constants.unitPriceEnergy;
    }

    private int secondHeuristicFunction(int prosperity, TownConstants constants) {
        return getMinBuildsFood(prosperity, constants) + getMinBuildsMaterials(prosperity, constants)
                + getMinBuildsEnergy(prosperity, constants);
    }
}
