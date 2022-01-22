package santa;

import children.Child;
import database.AnnualData;

import java.util.Collections;


public class IdStrategy implements AssignationStrategy {

    /**
     * method that assign presents to children based on their id
     * all children are sorted based on the specified criteria
     * presents are distributed to children in order based on their preferences
     * price and quantity remained
     * @param annualData object where all data needed for distribution is stored
     */
    @Override
    public void assign(final AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        for (Child child : annualData.getChildren()) {
            double sumGifts = 0.0;
            for (var category : child.getGiftsPreferences()) {
                Present present = annualData.getPresent(category);

                // the presents are distributed only if the price and the quantity
                // allows it
                if (present != null && present.getPrice() != null
                        && !child.getReceivedGifts().contains(present)
                        && present.getQuantity() > 0) {
                    if ((sumGifts + present.getPrice()) <= child.getAssignedBudget()) {
                        child.setReceivedGifts(present);
                        sumGifts = sumGifts + present.getPrice();
                        present.changeQuantity();
                    }
                }
            }
        }
    }
}
