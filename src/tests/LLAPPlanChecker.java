package tests;

import java.util.ArrayList;
import java.util.HashMap;

public class LLAPPlanChecker {
    int prosperity;
    int food;
    int material;
    int energy;

    HashMap<String, Integer> unitPrices;
    // vector code :
    // 0 : spendMoney
    // 1 : consumeFood
    // 2 : consumeMaterial
    // 3 : consumeEnergy
    // 4 : prosperityGained
    // 5 : delay
    // 6 : delayType
    ArrayList<Integer> foodList;
    ArrayList<Integer> materialList;
    ArrayList<Integer> energyList;
    ArrayList<Integer> waitList;
    ArrayList<Integer> buildOneList;
    ArrayList<Integer> buildTwoList;

    int maxBudget = 100000;
    int moneySpent = 0;

    int delay = 0;
    int maxCapacity = 50;

    int delayType = -1;

    public LLAPPlanChecker(String str) {

        String[] splitState = str.split(";");

        this.prosperity = Integer.parseInt(splitState[0]);

        this.food = Integer.parseInt(splitState[1].split(",")[0]);
        this.material = Integer.parseInt(splitState[1].split(",")[1]);
        this.energy = Integer.parseInt(splitState[1].split(",")[2]);

        this.unitPrices = new HashMap<String, Integer>();
        unitPrices.put("FoodRequestAction", Integer.parseInt(splitState[2].split(",")[0]));
        unitPrices.put("MaterialRequestAction", Integer.parseInt(splitState[2].split(",")[1]));
        unitPrices.put("EnergyRequestAction", Integer.parseInt(splitState[2].split(",")[2]));

        this.foodList = new ArrayList<Integer>();
        foodList.add(0);
        foodList.add(1);
        foodList.add(1);
        foodList.add(1);
        foodList.add(0);
        foodList.add(Integer.parseInt(splitState[3].split(",")[0]));
        foodList.add(Integer.parseInt(splitState[3].split(",")[1]));
        foodList.set(0,
                foodList.get(0) + foodList.get(1) * unitPrices.get("FoodRequestAction")
                        + foodList.get(2) * unitPrices.get("MaterialRequestAction")
                        + foodList.get(3) * unitPrices.get("EnergyRequestAction"));
        this.materialList = new ArrayList<Integer>();
        materialList.add(0);
        materialList.add(1);
        materialList.add(1);
        materialList.add(1);
        materialList.add(0);
        materialList.add(Integer.parseInt(splitState[4].split(",")[0]));
        materialList.add(Integer.parseInt(splitState[4].split(",")[1]));
        materialList.set(0,
                materialList.get(0) + materialList.get(1) * unitPrices.get("FoodRequestAction")
                        + materialList.get(2) * unitPrices.get("MaterialRequestAction")
                        + materialList.get(3) * unitPrices.get("EnergyRequestAction"));
        this.energyList = new ArrayList<Integer>();
        energyList.add(0);
        energyList.add(1);
        energyList.add(1);
        energyList.add(1);
        energyList.add(0);
        energyList.add(Integer.parseInt(splitState[5].split(",")[0]));
        energyList.add(Integer.parseInt(splitState[5].split(",")[1]));
        energyList.set(0,
                energyList.get(0) + energyList.get(1) * unitPrices.get("FoodRequestAction")
                        + energyList.get(2) * unitPrices.get("MaterialRequestAction")
                        + energyList.get(3) * unitPrices.get("EnergyRequestAction"));
        this.waitList = new ArrayList<Integer>();
        waitList.add(0);
        waitList.add(1);
        waitList.add(1);
        waitList.add(1);
        waitList.add(0);
        waitList.add(0);
        waitList.add(0);
        waitList.set(0,
                waitList.get(0) + waitList.get(1) * unitPrices.get("FoodRequestAction")
                        + waitList.get(2) * unitPrices.get("MaterialRequestAction")
                        + waitList.get(3) * unitPrices.get("EnergyRequestAction"));

        this.buildOneList = new ArrayList<Integer>();
        this.buildTwoList = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            String par1 = splitState[6].split(",")[i];
            buildOneList.add(Integer.parseInt(par1));
            String par2 = splitState[7].split(",")[i];
            buildTwoList.add(Integer.parseInt(par2));
        }
        buildOneList.set(0,
                buildOneList.get(0) + buildOneList.get(1) * unitPrices.get("FoodRequestAction")
                        + buildOneList.get(2) * unitPrices.get("MaterialRequestAction")
                        + buildOneList.get(3) * unitPrices.get("EnergyRequestAction"));
        buildTwoList.set(0,
                buildTwoList.get(0) + buildTwoList.get(1) * unitPrices.get("FoodRequestAction")
                        + buildTwoList.get(2) * unitPrices.get("MaterialRequestAction")
                        + buildTwoList.get(3) * unitPrices.get("EnergyRequestAction"));

    }

    public boolean er(String y) {
        ArrayList<Integer> x = new ArrayList<>();
        switch (y) {
            case "FoodRequestAction":
                x = foodList;
                break;
            case "MaterialRequestAction":
                x = materialList;
                break;
            case "EnergyRequestAction":
                x = energyList;
                break;
            case "WaitAction":
                x = waitList;
                break;
            case "E1":
                x = buildOneList;
                break;
            case "E2":
                x = buildTwoList;
                break;
            default:
                x = new ArrayList<>();
                break;
        }
        return (this.food >= x.get(1) && this.material >= x.get(2) && this.energy >= x.get(3)
                && this.maxBudget - this.moneySpent >= x.get(0));
    }

    public void ur(String y) {

        ArrayList<Integer> x = new ArrayList<>();

        switch (y) {
            case "FoodRequestAction":
                x = foodList;
                break;
            case "MaterialRequestAction":
                x = materialList;
                break;
            case "EnergyRequestAction":
                x = energyList;
                break;
            case "WaitAction":
                x = waitList;
                break;
            case "E1":
                x = buildOneList;
                break;
            case "E2":
                x = buildTwoList;
                break;
            default:
                x = new ArrayList<>();
                break;
        }

        this.food -= x.get(1);
        this.material -= x.get(2);
        this.energy -= x.get(3);
        this.moneySpent += x.get(0);
        this.prosperity += x.get(4);
    }

    void au() {
        if (delayType != -1 && delay > 0) {
            delay--;
        }
        if (this.delay == 0 && this.delayType != -1) {

            if (this.delayType == 0) {
                this.food += this.foodList.get(5);
            }
            if (this.delayType == 1) {
                this.material += this.materialList.get(5);
            }
            if (this.delayType == 2) {
                this.energy += this.energyList.get(5);
            }
            this.delayType = -1;
            this.delay = 0;
        }
    }

    void mc() {
        if (food > maxCapacity) {
            food = maxCapacity;
        }
        if (material > maxCapacity) {
            material = maxCapacity;
        }
        if (energy > maxCapacity) {
            energy = maxCapacity;
        }
    }

    boolean request(String an) {
        au();
        int i = -1;
        if (!er(an)) {
            return false;
        }
        switch (an) {
            case "FoodRequestAction":
                if (this.maxBudget - this.moneySpent < this.foodList.get(0)) {
                    return false;
                }
                i = 0;
                delay = foodList.get(6);
                break;
            case "MaterialRequestAction":
                if (this.maxBudget - this.moneySpent < this.materialList.get(0)) {
                    return false;
                }
                i = 1;
                delay = materialList.get(6);
                break;
            case "EnergyRequestAction":
                if (this.maxBudget - this.moneySpent < this.energyList.get(0)) {
                    return false;
                }
                i = 2;
                delay = energyList.get(6);
                break;
            default:
                return false;
        }
        this.delayType = i;
        ur(an);
        mc();
        return true;
    }

    boolean waitfunction() {
        au();
        if (!er("WaitAction")) {
            return false;
        }
        ur("WaitAction");
        mc();
        return true;
    }

    boolean build(int i) {
        au();
        String an = "Build" + i;
        if (!er(an)) {
            return false;
        }
        ur(an);
        mc();
        return true;
    }

    public boolean tryPlan(String[] actions, LLAPPlanChecker s) {
        boolean linkin = false;
        for (int i = 0; i < actions.length; i++) {

            switch (actions[i]) {
                case "requestfood":
                    linkin = s.request("FoodRequestAction");
                    break;
                case "requestmaterials":
                    linkin = s.request("MaterialRequestAction");
                    break;
                case "requestenergy":
                    linkin = s.request("EnergyRequestAction");
                    break;
                case "build1":
                    linkin = s.build(1);
                    break;
                case "build2":
                    linkin = s.build(2);
                    break;
                case "wait":
                    linkin = s.waitfunction();
                    break;
                default:
                    linkin = false;
                    break;

            }
            if (!linkin) {
                System.out.println("action that failed: " + actions[i] + ", order: " + i);
                return false;
            }
        }
        return true;
    }

    boolean isGoal() {
        return this.prosperity >= 100;
    }

    public boolean applyPlan(String grid, String solution) {
        boolean linkin = true;
        solution = solution.toLowerCase();
        if (solution.equals("nosolution")) {
            return false;
        }
        // System.out.println(solution);
        String[] solutionArray = solution.split(";");
        String plan = solutionArray[0];
        int blue = Integer.parseInt(solutionArray[1]);
        plan.replace(" ", "");
        plan.replace("\n", "");
        plan.replace("\r", "");
        plan.replace("\n\r", "");
        plan.replace("\t", "");

        String[] actions = plan.split(",");

        LLAPPlanChecker s = new LLAPPlanChecker(grid);
        linkin = tryPlan(actions, s);
        if (!linkin) {
            return false;
        }

        return s.isGoal() && s.moneySpent == blue;
    }
}
