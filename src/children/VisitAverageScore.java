package children;

import common.Constants;

public class VisitAverageScore {

    /**
     * sets average score for teen as the arithmetic average of
     * all nice scores accumulated
     */
    public void setAverageScore(final Kid kid) {
        if (kid.getNiceScoreHistory().size() == 1) {
            kid.averageScore = kid.getNiceScore();
        } else {
            kid.averageScore = 0.0;
            for (var score : kid.getNiceScoreHistory()) {
                kid.averageScore = kid.averageScore + score;
            }
            kid.averageScore = kid.averageScore / kid.getNiceScoreHistory().size();
        }
        kid.averageScore += kid.averageScore * kid.getNiceScoreBonus() / Constants.FULL_PERCENTAGE;
        if (kid.averageScore > Constants.MAX_SCORE) {
            kid.averageScore = Constants.MAX_SCORE;
        }
    }

    /**
     * sets average score for teen as the weighted average of
     * all nice scores accumulated
     */
    public void setAverageScore(final Teen teen) {
        int ponderi = 0;
        if (teen.getNiceScoreHistory().size() == 1) {
            teen.averageScore = teen.getNiceScore();
        } else {
            teen.averageScore = 0.0;
            for (int i = 1; i <= teen.getNiceScoreHistory().size(); i++) {
                teen.averageScore = teen.averageScore + i * teen.getNiceScoreHistory().get(i - 1);
                ponderi = ponderi + i;
            }
            teen.averageScore = teen.averageScore / ponderi;
        }
        teen.averageScore += teen.averageScore * teen.getNiceScoreBonus()
                / Constants.FULL_PERCENTAGE;
        if (teen.averageScore > Constants.MAX_SCORE) {
            teen.averageScore = Constants.MAX_SCORE;
        }
    }

    /**
     * sets average score as 10 for baby
     */
    public void setAverageScore(final Baby baby) {
        baby.averageScore = Constants.AVERAGE_SCORE_BABY;
    }
}
