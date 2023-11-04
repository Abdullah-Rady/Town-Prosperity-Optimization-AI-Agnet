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

    private int firstHeuristicFunction(int actionValue, TownConstants constants) {
        if (actionValue == 1)
            return constants.amountRequestFood - constants.delayRequestFood - 3;

        if (actionValue == 2)
            return constants.amountRequestMaterial - constants.delayRequestMaterial - 3;

        if (actionValue == 3)
            return constants.amountRequestEnergy - constants.delayRequestEnergy - 3;

        if (actionValue == 4)
            return -3;
        if (actionValue == 5)
            return -constants.energyUseBUILD1 - constants.foodUseBUILD1 - constants.materialsUseBUILD1
                    + constants.prosperityBUILD1;

        return -constants.energyUseBUILD2 - constants.foodUseBUILD2 - constants.materialsUseBUILD2
                + constants.prosperityBUILD2;

    }

    private int secondHeuristicFunction(int actionValue, TownConstants constants) {
        // everything here is in negative as we want least price so we maximize (-
        // price)
        if (actionValue == 1)
            return -constants.unitPriceFood * constants.amountRequestFood;
        if (actionValue == 2)
            return -constants.unitPriceMaterials * constants.amountRequestMaterial;
        if (actionValue == 3)
            return -constants.unitPriceEnergy * constants.amountRequestEnergy;
        if (actionValue == 4)
            return -3;
        if (actionValue == 5)
            return -constants.energyUseBUILD1;

        return -constants.energyUseBUILD2;
    }
}
