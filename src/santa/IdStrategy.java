package santa;

import children.Child;
import database.AnnualData;

import java.util.Collections;
import java.util.List;

public class IdStrategy implements AssignationStrategy{

    @Override
    public void assign(AnnualData annualData) {
        Collections.sort(annualData.getChildren());
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
