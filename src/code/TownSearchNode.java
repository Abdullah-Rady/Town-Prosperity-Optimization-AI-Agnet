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

    public String getHashString() {
        return food + ";" + materials + ";" + energy + ";" + getDelay() + ";" + getDelayType() + ";" + prosperity
                + ";";

        // working : f,m,e,d,dt,p
    }

    public int getDelay() {
        if (foodDelay > -1) {
            return foodDelay;
        }
        if (materialsDelay > -1) {
            return materialsDelay;
        }
        if (energyDelay > -1) {
            return energyDelay;
        }
        return -1;

    }

    public int getDelayType() {
        if (foodDelay > -1) {
            return 0;
        }
        if (materialsDelay > -1) {
            return 1;
        }
        if (energyDelay > -1) {
            return 2;
        }
        return -1;

    }

    @Override
    public String toString() {
        return "Prosperity: " + prosperity + ", Food: " + food +
                ", Materials: " + materials + ", Energy: " + energy +
                ", Money Spent: " + moneySpent +
                ", Food Delay: " + foodDelay + ", Materials Delay: " + materialsDelay +
                ", Energy Delay: " + energyDelay;
    }
}
