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

    public static Actions[] valuesReversed() {
        // working :return new Actions[] { Build1, Build2, Wait, RequestFood,
        // RequestEnergy, RequestMaterials };
        // working 2 : { Build1, Build2, Wait, RequestFood, RequestMaterials,
        // RequestEnergy }
        // working 3 : same as2 but switch builds

        return new Actions[] { Build2, Build1, Wait, RequestFood, RequestMaterials, RequestEnergy };
    }

}