package santa;

import children.Child;
import database.AnnualData;

import java.util.*;

public class NiceScoreCityStrategy implements AssignationStrategy{
    @Override
    public void assign(AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        Map<String, Double> cityScore = new HashMap<>();
        ArrayList<String> cities = new ArrayList<>();
        for (Child child : annualData.getChildren()) {
            if (!cities.contains(child.getCity())) {
                cities.add(child.getCity());
            }
        }
        for (var city : cities) {
            Double average = 0.0;
            int nrAverage = 0;
            for (Child child : annualData.getChildren()) {
                if (child.getCity().equals(city)) {
                    nrAverage++;
                    average = average + child.getAverageScore();
                }
            }
            if (nrAverage > 0) {
                average = average / nrAverage;
                cityScore.put(city, average);
            }
        }
        List<Map.Entry<String, Double>> citiesScored =
                new LinkedList<>(cityScore.entrySet());

        citiesScored.sort((a1, a2) -> (a1.getKey()).compareTo(a2.getKey()));
        citiesScored.sort((a1, a2) -> (a2.getValue()).compareTo(a1.getValue()));

        for (Map.Entry<String, Double> pair : citiesScored) {
            for (Child child : annualData.getChildren()) {
                if (child.getCity().equals(pair.getKey())) {
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
    }
}
