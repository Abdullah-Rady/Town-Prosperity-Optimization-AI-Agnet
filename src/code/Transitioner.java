package code;

public class Transitioner {
    boolean firstHeuristic;

    public Transitioner(boolean firstHeuristic) {
        this.firstHeuristic = firstHeuristic;
    }

    public int calculateHeuristic(int prosperity, TownConstants constants) {
        if (firstHeuristic)
            return firstHeuristicFunction(prosperity, constants);
        return secondHeuristicFunction(prosperity, constants);
    }

    private int firstHeuristicFunction(int prosperity, TownConstants constants) {
        return getMinBuildsMoney(prosperity, constants);
    }

    public int getMinBuilds(int prosperity, TownConstants constants) {
        return (100 - prosperity) / Math.max(constants.prosperityBUILD1, constants.prosperityBUILD2);
    }

    public int getMinBuildsMoney(int prosperity, TownConstants constants) {
        return getMinBuilds(prosperity, constants) * Math.min(constants.priceBUILD1, constants.priceBUILD2);
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
