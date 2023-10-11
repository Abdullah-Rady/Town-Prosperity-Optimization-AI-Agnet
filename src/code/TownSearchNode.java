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

    @Override
    public boolean equals(Object obj) {

        TownSearchNode other = (TownSearchNode) obj;
        return prosperity == other.prosperity &&
                food == other.food &&
                materials == other.materials &&
                energy == other.energy &&
                moneySpent == other.moneySpent &&
                foodDelay == other.foodDelay &&
                materialsDelay == other.materialsDelay &&
                energyDelay == other.energyDelay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + prosperity;
        result = prime * result + food;
        result = prime * result + materials;
        result = prime * result + energy;
        result = prime * result + moneySpent;
        result = prime * result + foodDelay;
        result = prime * result + materialsDelay;
        result = prime * result + energyDelay;
        return result;
    }
}
