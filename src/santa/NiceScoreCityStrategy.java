package santa;

import children.Child;
import database.AnnualData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NiceScoreCityStrategy implements AssignationStrategy {

    /**
     * method that assign presents to children based on their city score
     * the city score is calculated as the total of children's average score that live there
     * all children are sorted based on the specified criteria
     * presents are distributed to children in order based on their preferences
     * price and quantity remained
     * @param annualData object where all data needed for distribution is stored
     */
    @Override
    public void assign(final AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        Map<String, Double> cityScore = new HashMap<>();

        // we store all existing cities in that year in a list
        ArrayList<String> cities = new ArrayList<>();
        for (Child child : annualData.getChildren()) {
            if (!cities.contains(child.getCity())) {
                cities.add(child.getCity());
            }
        }

        // we store the pair City - total score of all children in hash map
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
        // we transform the entries of the hash map in a linked list because of sorting reasons
        List<Map.Entry<String, Double>> citiesScored =
                new LinkedList<>(cityScore.entrySet());

        // sort cities
        citiesScored.sort((a1, a2) -> (a1.getKey()).compareTo(a2.getKey()));
        citiesScored.sort((a1, a2) -> (a2.getValue()).compareTo(a1.getValue()));

        // the first for which iterates through the cities sorted list sets the
        // order for distribution
        for (Map.Entry<String, Double> pair : citiesScored) {
            for (Child child : annualData.getChildren()) {
                if (child.getCity().equals(pair.getKey())) {
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
    }
}
