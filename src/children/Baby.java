package children;
import common.Constants;
import santa.Present;

import java.util.ArrayList;

public class Baby extends Child {

    public Baby(final int id, final String lastName, final String firstName,
                final int age, final String city, final Double niceScore,
                final Double assignedBudget, final ArrayList<Double> historyScore,
                final ArrayList<Present> giftsReceived, final ArrayList<String> giftsPreference,
                final Double averageScore, final Double niceScoreBonus, final String elf) {
        super(id, lastName, firstName, age, city, niceScore, assignedBudget,
                historyScore,  giftsReceived,
                giftsPreference, niceScoreBonus, elf);
        this.averageScore = averageScore;
    }

    public Baby(final int id, final String lastName, final String firstName,
                final int age, final String city, final Double niceScore,
                final ArrayList<String> giftsPreference,
                final Double niceScoreBonus, final String elf) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreference,
                niceScoreBonus, elf);
        averageScore = Constants.AVERAGE_SCORE_BABY;
    }


    /**
     * sets average score as 10 for baby
     */
    public void acceptAverageScore(VisitAverageScore visitor) {
        visitor.setAverageScore(this);
        //this.averageScore = Constants.AVERAGE_SCORE_BABY;
    }
}
