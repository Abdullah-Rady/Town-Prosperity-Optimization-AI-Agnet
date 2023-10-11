package code;

public enum Actions {

    RequestFood(1),
    RequestMaterials(2),
    RequestEnergy(3),
    Wait(4),
    Build1(5),
    Build2(6);

    private final int value;

    Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}