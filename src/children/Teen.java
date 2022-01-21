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
     * sets average score for teen as the weighted average of
     * all nice scores accumulated
     */
    public void acceptAverageScore(VisitAverageScore visitor) {
        visitor.setAverageScore(this);
      /*  int ponderi = 0;
        if (super.getNiceScoreHistory().size() == 1) {
            averageScore = super.getNiceScore();
        } else {
            averageScore = 0.0;
            for (int i = 1; i <= super.getNiceScoreHistory().size(); i++) {
                averageScore = averageScore + i * getNiceScoreHistory().get(i - 1);
                ponderi = ponderi + i;
            }
            averageScore = averageScore / ponderi;
        }
        averageScore += averageScore * super.getNiceScoreBonus()/ 100 ;
        if (averageScore > 10.0) {
            averageScore = 10.0;
        }

       */
    }
}
