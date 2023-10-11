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

    public TownState(
        int prosperity,
        int food,
        int materials,
        int energy,
        int unitPriceFood,
        int unitPriceMaterials,
        int unitPriceEnergy,
        int amountRequestFood,
        int delayRequestFood,
        int amountRequestMaterial,
        int delayRequestMaterial,
        int amountRequestEnergy,
        int delayRequestEnergy,
        int priceBUILD1,
        int foodUseBUILD1,
        int materialsUseBUILD1,
        int energyUseBUILD1,
        int prosperityBUILD1,
        int priceBUILD2,
        int foodUseBUILD2,
        int materialsUseBUILD2,
        int energyUseBUILD2,
        int prosperityBUILD2
    ) {
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;

        this.unitPriceFood = unitPriceFood;
        this.unitPriceMaterials = unitPriceMaterials;
        this.unitPriceEnergy = unitPriceEnergy;

        this.amountRequestFood = amountRequestFood;
        this.delayRequestFood = delayRequestFood;

        this.amountRequestMaterial = amountRequestMaterial;
        this.delayRequestMaterial = delayRequestMaterial;

        this.amountRequestEnergy = amountRequestEnergy;
        this.delayRequestEnergy = delayRequestEnergy;

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
