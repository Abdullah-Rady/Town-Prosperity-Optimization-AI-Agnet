package code;
import code.TownState;

public class TownStateParser {

    public TownState parseInitialState(String initialStateStr) {
        String[] parts = initialStateStr.split(";");
        
        if (parts.length != 9) {
            throw new IllegalArgumentException("Invalid initial state string format.");
        }
        
        try {
            int initialProsperity = Integer.parseInt(parts[0].trim());

            String[] resourceLevels = parts[1].trim().split(",");
            int initialFood = Integer.parseInt(resourceLevels[0].trim());
            int initialMaterials = Integer.parseInt(resourceLevels[1].trim());
            int initialEnergy = Integer.parseInt(resourceLevels[2].trim());

            String[] unitPrices = parts[2].trim().split(",");
            int unitPriceFood = Integer.parseInt(unitPrices[0].trim());
            int unitPriceMaterials = Integer.parseInt(unitPrices[1].trim());
            int unitPriceEnergy = Integer.parseInt(unitPrices[2].trim());

            String[] foodDelivery = parts[3].trim().split(",");
            int amountRequestFood = Integer.parseInt(foodDelivery[0].trim());
            int delayRequestFood = Integer.parseInt(foodDelivery[1].trim());

            String[] materialsDelivery = parts[4].trim().split(",");
            int amountRequestMaterials = Integer.parseInt(materialsDelivery[0].trim());
            int delayRequestMaterials = Integer.parseInt(materialsDelivery[1].trim());

            String[] energyDelivery = parts[5].trim().split(",");
            int amountRequestEnergy = Integer.parseInt(energyDelivery[0].trim());
            int delayRequestEnergy = Integer.parseInt(energyDelivery[1].trim());

            String[] build1Values = parts[6].trim().split(",");
            int priceBUILD1 = Integer.parseInt(build1Values[0].trim());
            int foodUseBUILD1 = Integer.parseInt(build1Values[1].trim());
            int materialsUseBUILD1 = Integer.parseInt(build1Values[2].trim());
            int energyUseBUILD1 = Integer.parseInt(build1Values[3].trim());
            int prosperityBUILD1 = Integer.parseInt(build1Values[4].trim());

            String[] build2Values = parts[7].trim().split(",");
            int priceBUILD2 = Integer.parseInt(build2Values[0].trim());
            int foodUseBUILD2 = Integer.parseInt(build2Values[1].trim());
            int materialsUseBUILD2 = Integer.parseInt(build2Values[2].trim());
            int energyUseBUILD2 = Integer.parseInt(build2Values[3].trim());
            int prosperityBUILD2 = Integer.parseInt(build2Values[4].trim());

            
            // Parse other properties in a similar manner
            
            return new TownState(
                initialProsperity, initialFood, initialMaterials, initialEnergy,
                unitPriceFood, unitPriceMaterials, unitPriceEnergy,
                amountRequestFood, delayRequestFood,
                amountRequestMaterials, delayRequestMaterials,
                amountRequestEnergy, delayRequestEnergy,
                priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1,
                priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in initial state string.", e);
        }
    }
}
