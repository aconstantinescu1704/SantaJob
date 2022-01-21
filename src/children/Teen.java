package children;

import santa.Present;

import java.util.ArrayList;

public class Teen extends Child {

    public Teen(final int id, final String lastName, final String firstName,
                final int age, final String city, final Double niceScore,
                final Double assignedBudget, final ArrayList<Double> historyScore,
                final ArrayList<Present> giftsReceived, final ArrayList<String> giftsPreference,
                final Double averageScore, final Double niceScoreBonus, final String elf) {
        super(id, lastName, firstName, age, city, niceScore, assignedBudget,
                historyScore,  giftsReceived,
                giftsPreference, niceScoreBonus, elf);
        this.averageScore = averageScore;
    }

    public Teen(final int id, final String lastName, final String firstName,
                final int age, final String city, final Double niceScore,
                final ArrayList<String> giftsPreference, final Double niceScoreBonus,
                final String elf) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreference,
                niceScoreBonus, elf);
        this.averageScore = super.getNiceScore();
    }

    /**
     * accepts to be visited in order to update average score
     */
    public void acceptAverageScore(final VisitAverageScore visitor) {
        visitor.setAverageScore(this);
    }
}
