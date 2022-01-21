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
     * method that assigns presents based on a child's city score
     * the city score = total of all children average score that live in the city
     * it sorts children in descending order based on the specified criteria
     * then distributes all presents making sure the preferences of all children are
     * met if possible
     * @param annualData object where are stored all information relevant for distribution
     */
    @Override
    public void assign(final AnnualData annualData) {
        Collections.sort(annualData.getChildren());
        Map<String, Double> cityScore = new HashMap<>();
        ArrayList<String> cities = new ArrayList<>();
        for (Child child : annualData.getChildren()) {
            if (!cities.contains(child.getCity())) {
                cities.add(child.getCity());
            }
        }

        // we complete a map that contains the match of the city name and total score
        // calculated
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

        // we store the map's entries in a linked list to sort
        List<Map.Entry<String, Double>> citiesScored =
                new LinkedList<>(cityScore.entrySet());

        citiesScored.sort((a1, a2) -> (a1.getKey()).compareTo(a2.getKey()));
        citiesScored.sort((a1, a2) -> (a2.getValue()).compareTo(a1.getValue()));

        // we distribute presents based oh the city score
        for (Map.Entry<String, Double> pair : citiesScored) {
            for (Child child : annualData.getChildren()) {
                if (child.getCity().equals(pair.getKey())) {
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
    }
}
