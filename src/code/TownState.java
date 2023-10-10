package code;
public class TownState {


    final int MAX_RESOURCE_CAPACITY = 50;
    int budget = 100000;
    int prosperity;
    int food;
    int materials;
    int energy;
    int unitPriceFood;
    int unitPriceMaterials;
    int unitPriceEnergy;
    int amountRequestFood;
    int delayRequestFood;
    int amountRequestEnergy;
    int delayRequestEnergy;
    int amountRequestMaterial;
    int delayRequestMaterial;
    int priceBUILD1;
    int foodUseBUILD1;
    int materialsUseBUILD1;
    int energyUseBUILD1;
    int prosperityBUILD1;
    int priceBUILD2;
    int foodUseBUILD2;
    int materialsUseBUILD2;
    int energyUseBUILD2;
    int prosperityBUILD2;

    TownState(String initialStateStr) {
        // Split the input string using the delimiter ";"
        String[] parts = initialStateStr.split(";");

        // Ensure that the input string has the correct number of parts
        if (parts.length != 9) {
            throw new IllegalArgumentException("Invalid initial state string format.");
        }

        try {
            // Parse each part and assign the values to their respective variables
            int initialProsperity = Integer.parseInt(parts[0].trim());

            String[] resourceLevels = parts[1].trim().split(",");
            int initialFood = Integer.parseInt(resourceLevels[0].trim());
            int initialMaterials = Integer.parseInt(resourceLevels[1].trim());
            int initialEnergy = Integer.parseInt(resourceLevels[2].trim());

            String[] unitPrices = parts[2].trim().split(",");
            int unitPriceFood = Integer.parseInt(unitPrices[0].trim());
            int unitPriceMaterials = Integer.parseInt(unitPrices[1].trim());
            int unitPriceEnergy = Integer.parseInt(unitPrices[2].trim());

            String[] foodDelivery = parts[3].trim().split(",");
            int amountRequestFood = Integer.parseInt(foodDelivery[0].trim());
            int delayRequestFood = Integer.parseInt(foodDelivery[1].trim());

            String[] materialsDelivery = parts[4].trim().split(",");
            int amountRequestMaterials = Integer.parseInt(materialsDelivery[0].trim());
            int delayRequestMaterials = Integer.parseInt(materialsDelivery[1].trim());

            String[] energyDelivery = parts[5].trim().split(",");
            int amountRequestEnergy = Integer.parseInt(energyDelivery[0].trim());
            int delayRequestEnergy = Integer.parseInt(energyDelivery[1].trim());

            String[] build1Values = parts[6].trim().split(",");
            int priceBUILD1 = Integer.parseInt(build1Values[0].trim());
            int foodUseBUILD1 = Integer.parseInt(build1Values[1].trim());
            int materialsUseBUILD1 = Integer.parseInt(build1Values[2].trim());
            int energyUseBUILD1 = Integer.parseInt(build1Values[3].trim());
            int prosperityBUILD1 = Integer.parseInt(build1Values[4].trim());

            String[] build2Values = parts[7].trim().split(",");
            int priceBUILD2 = Integer.parseInt(build2Values[0].trim());
            int foodUseBUILD2 = Integer.parseInt(build2Values[1].trim());
            int materialsUseBUILD2 = Integer.parseInt(build2Values[2].trim());
            int energyUseBUILD2 = Integer.parseInt(build2Values[3].trim());
            int prosperityBUILD2 = Integer.parseInt(build2Values[4].trim());

            this.prosperity = initialProsperity;
            this.food = initialFood;
            this.materials = initialMaterials;
            this.energy = initialEnergy;
            this.unitPriceFood = unitPriceFood;
            this.unitPriceMaterials = unitPriceMaterials;
            this.unitPriceEnergy = unitPriceEnergy;
            this.amountRequestFood = amountRequestFood;
            this.delayRequestFood = delayRequestFood;
            this.amountRequestEnergy = amountRequestEnergy;
            this.delayRequestEnergy = delayRequestEnergy;
            this.amountRequestMaterial = amountRequestMaterials;
            this.delayRequestMaterial = delayRequestMaterials;
            this.priceBUILD1 = priceBUILD1;
            this.foodUseBUILD1 = foodUseBUILD1;
            this.materialsUseBUILD1 = materialsUseBUILD1;
            this.energyUseBUILD1 = energyUseBUILD1;
            this.prosperityBUILD1 = prosperityBUILD1;
            this.priceBUILD2 = priceBUILD2;
            this.foodUseBUILD2 = foodUseBUILD2;
            this.materialsUseBUILD2 = materialsUseBUILD2;
            this.energyUseBUILD2 = energyUseBUILD2;
            this.prosperityBUILD2 = prosperityBUILD2;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in initial state string.", e);
        }

    }

    /**
     * Check if the town has achieved prosperity.
     *
     * @return true if the town has achieved prosperity, otherwise false.
     */
    boolean isProsperous() {
        return prosperity >= 100;
    }

    boolean canConsumeResources() {
        return (food > 0 && materials > 0 && energy > 0);
    }


    // ? is requesting food / materials / energy allowed if the there is a pending
    // delivery

    boolean canRequestFood() {
        return canConsumeResources() && (food + amountRequestFood <= MAX_RESOURCE_CAPACITY
                && unitPriceFood * amountRequestFood <= budget);
    }

    boolean canRequestMaterials() {
        return canConsumeResources() && (materials + amountRequestMaterial <= MAX_RESOURCE_CAPACITY
                && unitPriceMaterials * amountRequestMaterial <= budget);
    }

    boolean canRequestEnergy() {
        return canConsumeResources()
                && (energy < amountRequestEnergy && unitPriceEnergy * amountRequestEnergy <= budget);
    }

    // only allowed to wait if there is a pending delivery
    boolean canWait() {
        return canConsumeResources();
    }

    // ? is taking an action that increases the prosperity of the town over 100
    // allowed
    boolean canBuild1() {
        return (food >= foodUseBUILD1 && materials >= materialsUseBUILD1 && energy >= energyUseBUILD1
                && budget >= priceBUILD1 && prosperity + prosperityBUILD1 < 100);
    }

    boolean canBuild2() {
        return (food >= foodUseBUILD2 && materials >= materialsUseBUILD2 && energy >= energyUseBUILD2
                && budget >= priceBUILD2 && prosperity + prosperityBUILD2 < 100);
    }

    // TO DO: Implement actions to update the town's state

    // for actions we need to delay request material, food and energy

    // TO DO: Implement an update function that will run in every step to check if
    // the pending deliveries arrived or not

}
