package code;

public class TownConstants {

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

    public TownConstants(
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
            int prosperityBUILD2) {
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

    public TownSearchNode getInitialState() {
        return new TownSearchNode(
                prosperity, food, materials, energy, 0, -1, -1, -1);

    }

}
