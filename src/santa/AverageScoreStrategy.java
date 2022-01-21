package santa;

import children.Child;
import database.AnnualData;

import java.util.Collections;
import java.util.List;

public class AverageScoreStrategy implements AssignationStrategy{
    @Override
    public void assign(AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        annualData.getChildren().sort((child1, child2) ->
                Double.compare(child2.getAverageScore(), child1.getAverageScore()));

        for (Child child : annualData.getChildren()) {
            double sumGifts = 0.0;
            for (var category : child.getGiftsPreferences()) {
                Present present = annualData.getPresent(category);
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
