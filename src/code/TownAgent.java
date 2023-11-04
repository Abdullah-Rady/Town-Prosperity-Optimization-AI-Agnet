package code;

public class TownAgent {

    TownConstants constants;

    public TownAgent(TownConstants constants) {
        this.constants = constants;
    }

    public boolean canConsumeResources(TownSearchNode state) {
        return (state.food > 0 && state.materials > 0 && state.energy > 0);
    }

    public boolean canRequestFood(TownSearchNode state) {
        return canConsumeResources(state)
                && (constants.unitPriceFood * constants.amountRequestFood <= constants.budget - state.moneySpent)
                && state.foodDelay == -1;
    }

    public boolean canRequestMaterials(TownSearchNode state) {
        return canConsumeResources(state)
                && (constants.unitPriceMaterials * constants.amountRequestMaterial <= constants.budget
                        - state.moneySpent)
                && state.materialsDelay == -1;
    }

    public boolean canRequestEnergy(TownSearchNode state) {
        return canConsumeResources(state)
                && (constants.unitPriceEnergy * constants.amountRequestEnergy <= constants.budget
                        - state.moneySpent)
                && state.energyDelay == -1;
    }

    public boolean canWait(TownSearchNode state) {
        return canConsumeResources(state);
    }

    public boolean canBuild1(TownSearchNode state) {
        return (state.food >= constants.foodUseBUILD1 && state.materials >= constants.materialsUseBUILD1
                && state.energy >= constants.energyUseBUILD1
                && constants.budget - state.moneySpent >= constants.priceBUILD1
                && state.prosperity < 100);
    }

    public boolean canBuild2(TownSearchNode state) {
        return (state.food >= constants.foodUseBUILD2 && state.materials >= constants.materialsUseBUILD2
                && state.energy >= constants.energyUseBUILD2
                && constants.budget - state.moneySpent >= constants.priceBUILD2
                && state.prosperity < 100);
    }

    public boolean checkAction(Actions action, TownSearchNode state) {
        TownSearchNode newState = update(state);
        switch (action) {
            case RequestFood:
                return canRequestFood(newState);
            case RequestMaterials:
                return canRequestMaterials(newState);
            case RequestEnergy:
                return canRequestEnergy(newState);
            case Wait:
                return canWait(newState);
            case Build1:
                return canBuild1(newState);
            case Build2:
                return canBuild2(newState);
            default:
                return false;
        }
    }

    public TownSearchNode consumeResources(TownSearchNode state) {
        state.food--;
        state.materials--;
        state.energy--;
        return state;
    }

    public TownSearchNode requestFood(TownSearchNode state) {
        consumeResources(state);
        state.moneySpent += constants.unitPriceFood * constants.amountRequestFood;
        state.foodDelay = constants.delayRequestFood;
        return state;
    }

    public TownSearchNode requestMaterials(TownSearchNode state) {
        consumeResources(state);
        state.moneySpent += constants.unitPriceMaterials * constants.amountRequestMaterial;
        state.materialsDelay = constants.delayRequestMaterial;
        return state;
    }

    public TownSearchNode requestEnergy(TownSearchNode state) {
        consumeResources(state);
        state.moneySpent += constants.unitPriceEnergy * constants.amountRequestEnergy;
        state.energyDelay = constants.delayRequestEnergy;
        return state;
    }

    public TownSearchNode waitAction(TownSearchNode state) {
        return consumeResources(state);
    }

    public TownSearchNode build1(TownSearchNode state) {

        // System.out.println(state.moneySpent);

        state.prosperity += constants.prosperityBUILD1;
        state.food -= constants.foodUseBUILD1;
        state.materials -= constants.materialsUseBUILD1;
        state.energy -= constants.energyUseBUILD1;
        state.moneySpent += constants.priceBUILD1;

        // System.out.println(state.moneySpent);

        return state;
    }

    public TownSearchNode build2(TownSearchNode state) {

        state.prosperity += constants.prosperityBUILD2;
        state.food -= constants.foodUseBUILD2;
        state.materials -= constants.materialsUseBUILD2;
        state.energy -= constants.energyUseBUILD2;
        state.moneySpent += constants.priceBUILD2;

        return state;
    }

    public TownSearchNode preformAction(Actions action, TownSearchNode state) {
        TownSearchNode newState = update(state);
        // System.out.println("new state"+ newState);
        switch (action) {
            case RequestFood:
                return requestFood(newState);
            case RequestMaterials:
                return requestMaterials(newState);
            case RequestEnergy:
                return requestEnergy(newState);
            case Wait:
                return waitAction(newState);
            case Build1:
                return build1(newState);
            case Build2:
                return build2(newState);
            default:
                return newState;
        }

    }

    public TownSearchNode update(TownSearchNode state) {
        TownSearchNode newState = new TownSearchNode(state.prosperity, state.food, state.materials, state.energy,
                state.moneySpent,
                state.foodDelay, state.materialsDelay, state.energyDelay);

        if (newState.foodDelay > 0)
            newState.foodDelay--;

        if (newState.materialsDelay > 0)
            newState.materialsDelay--;

        if (newState.energyDelay > 0)
            newState.energyDelay--;

        if (newState.foodDelay == 0) {
            newState.foodDelay = -1;
            newState.food += constants.amountRequestFood;
        }

        if (newState.materialsDelay == 0) {
            newState.materialsDelay = -1;
            newState.materials += constants.amountRequestMaterial;
        }

        if (newState.energyDelay == 0) {
            newState.energyDelay = -1;
            newState.energy += constants.amountRequestEnergy;
        }
        fixMax(newState);
        return newState;
    }

    void fixMax(TownSearchNode newState) {
        if (newState.food > constants.MAX_RESOURCE_CAPACITY) {
            newState.food = constants.MAX_RESOURCE_CAPACITY;
        }
        if (newState.materials > constants.MAX_RESOURCE_CAPACITY) {
            newState.materials = constants.MAX_RESOURCE_CAPACITY;
        }
        if (newState.energy > constants.MAX_RESOURCE_CAPACITY) {
            newState.energy = constants.MAX_RESOURCE_CAPACITY;
        }

    }

}
