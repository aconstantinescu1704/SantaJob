package santa;

import children.Child;
import database.AnnualData;

import java.util.Collections;


public class IdStrategy implements AssignationStrategy {

    /**
     * method that assigns presents based on a child's id
     * it sorts children in ascending order based on the specified criteria
     * then distributes all presents making sure the preferences of all children are
     * met if possible
     * @param annualData object where are stored all information relevant for distribution
     */
    @Override
    public void assign(final AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        for (Child child : annualData.getChildren()) {
            double sumGifts = 0.0;
            for (var category : child.getGiftsPreferences()) {
                Present present = annualData.getPresent(category);

                // based on a child's preferences we distribute the presents if
                // the quantity and price allows
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
