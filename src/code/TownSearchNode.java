package code;

public class TownSearchNode {

    int prosperity;
    int food;
    int materials;
    int energy;
    int moneySpent;

    int foodDelay;
    int materialsDelay;
    int energyDelay;

    public TownSearchNode(
            int prosperity,
            int food,
            int materials,
            int energy,
            int moneySpent,
            int foodDelay,
            int materialsDelay,
            int energyDelay) {

        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.foodDelay = foodDelay;
        this.materialsDelay = materialsDelay;
        this.energyDelay = energyDelay;
    }

    boolean isProsperous() {
        return prosperity >= 100;
    }
}
            