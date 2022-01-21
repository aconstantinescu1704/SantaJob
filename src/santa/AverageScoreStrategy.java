package santa;

import children.Child;
import database.AnnualData;

import java.util.Collections;

public class AverageScoreStrategy implements AssignationStrategy {

    /**
     * method that assigns presents based on a child's average score
     * it sorts children in descending order based on the specified criteria
     * then distributes all presents making sure the preferences of all children are
     * met if possible
     * @param annualData object where are stored all information relevant for distribution
     */
    @Override
    public void assign(final AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        annualData.getChildren().sort((child1, child2) ->
                Double.compare(child2.getAverageScore(), child1.getAverageScore()));

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
