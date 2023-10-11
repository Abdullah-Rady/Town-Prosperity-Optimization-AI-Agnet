package code;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import code.Actions;

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

    boolean checkAction(int n) {
        switch (n) {
            case 1:
                return canRequestFood();
            case 2:
                return canRequestMaterials();
            case 3:
                return canRequestEnergy();
            case 4:
                return canWait();
            case 5:
                return canBuild1();
            case 6:
                return canBuild2();
            default:
                return false;
        }
    }

    // TO DO: Implement actions to update the town's state
    // for actions we need to delay request material, food and energy

    public void consumeResources() {
        food -= 1;
        energy -= 1;
        materials -= 1;
    }

    public void requestFood() {
        consumeResources();
        budget -= unitPriceFood * amountRequestFood;
    }

    public void requestMaterials() {
        consumeResources();
        budget -= unitPriceMaterials * amountRequestMaterial;
    }

    public void requestEnergy() {
        consumeResources();
        budget -= unitPriceEnergy * amountRequestEnergy;
    }

    public void waitAction() {
        consumeResources();
        return;
    }

    public void build1() {
        food -= foodUseBUILD1;
        energy -= energyUseBUILD1;
        materials -= materialsUseBUILD1;
        budget -= priceBUILD1;
        prosperity += prosperityBUILD1;
    }

    public void build2() {
        food -= foodUseBUILD2;
        energy -= energyUseBUILD2;
        materials -= materialsUseBUILD2;
        budget -= priceBUILD2;
        prosperity += prosperityBUILD2;
    }

    
    public void preformAction(int n) {
        switch (n) {
            case 1:
                pendingActions.add(new PendingAction(Actions.RequestFood, delayRequestFood));
                requestFood();
                break;
            case 2:
                requestMaterials();
                break;
            case 3:
                requestEnergy();
                break;
            case 4:
                waitAction();
                break;
            case 5:
                build1();
                break;
            case 6:
                build2();
                break;
            default:
                break;
        }
    }

    // TO DO: Implement an update function that will run in every step to check if
    // the pending deliveries
    // arrived or not and update the town's state accordingly

    // ! Since this is a private object, when cloning it to save it
    // ! in the search tree, this instance of the object will be shared across all tree nodes.
    // ! Therefore, we need to override .clone and code the cloning logic.
    private Deque<PendingAction> pendingActions = new ArrayDeque<>();

    // Update the state by decrementing counters and removing actions from the front
    // of the Deque.
    void update() {
        Iterator<PendingAction> iterator = pendingActions.iterator();

        while (iterator.hasNext()) {
            PendingAction pendingAction = iterator.next();
            pendingAction.counter--;

            if (pendingAction.counter == 0) {
                // Check the action type to update the corresponding resource
                if (pendingAction.action.getValue() == 1)
                    food += amountRequestFood;
                else if (pendingAction.action.getValue() == 2)
                    materials += amountRequestMaterial;
                else if (pendingAction.action.getValue() == 3)
                    energy += amountRequestEnergy;

                iterator.remove();
            } else {
                break;
            }
        }
    }

    // A private class to represent a pending action with its counter.
    private class PendingAction {
        Actions action;
        int counter;

        PendingAction(Actions action, int counter) {
            this.action = action;
            this.counter = counter;
        }
    }

}
