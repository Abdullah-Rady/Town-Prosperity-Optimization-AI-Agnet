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

        // System.out.println(prosperity + " " + food + " " + materials + " " + energy + " " + moneySpent + " " + foodDelay + " " + materialsDelay + " " + energyDelay);
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
        int result = 1;
        result = 31 * result + prosperity;
        result = 37 * result + food;
        result = 41 * result + materials;
        result = 43 * result + energy;
        result = 47 * result + moneySpent;
        result = 53 * result + foodDelay;
        result = 59 * result + materialsDelay;
        result = 61 * result + energyDelay;
        return result;
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
