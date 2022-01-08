package children;

import santa.Present;

import java.util.ArrayList;

public class Kid extends Child {

    public Kid(final int id, final String lastName, final String firstName,
               final int age, final String city, final Double niceScore,
               final Double assignedBudget, final ArrayList<Double> historyScore,
               final ArrayList<Present> giftsReceived, final ArrayList<String> giftsPreference,
               final Double averageScore) {
        super(id, lastName, firstName, age, city, niceScore, assignedBudget,
                historyScore,  giftsReceived,
                giftsPreference);
        this.averageScore = averageScore;
    }

    public Kid(final int id, final String lastName, final String firstName,
               final int age, final String city, final Double niceScore,
               final ArrayList<String> giftsPreference) {

        super(id, lastName, firstName, age, city, niceScore, giftsPreference);
        averageScore = super.getNiceScore();
    }

    /**
     * sets average score for teen as the arithmetic average of
     * all nice scores accumulated
     */
    public void setAverageScore() {
        if (super.getNiceScoreHistory().size() == 1) {
            averageScore = super.getNiceScore();
        } else {
            averageScore = 0.0;
            for (var score : super.getNiceScoreHistory()) {
                averageScore = averageScore + score;
            }
            averageScore = averageScore / super.getNiceScoreHistory().size();
        }
    }

}
