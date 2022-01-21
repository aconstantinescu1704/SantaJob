package children;

import common.Constants;

public class VisitAverageScore {

    public void setAverageScore(Kid kid) {
        if (kid.getNiceScoreHistory().size() == 1) {
            kid.averageScore = kid.getNiceScore();
        } else {
            kid.averageScore = 0.0;
            for (var score : kid.getNiceScoreHistory()) {
                kid.averageScore = kid.averageScore + score;
            }
            kid.averageScore = kid.averageScore / kid.getNiceScoreHistory().size();
        }
        kid.averageScore += kid.averageScore * kid.getNiceScoreBonus()/ 100 ;
        if (kid.averageScore > 10.0) {
            kid.averageScore = 10.0;
        }
    }
    public void setAverageScore(Teen teen)  {
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
        teen.averageScore += teen.averageScore * teen.getNiceScoreBonus()/ 100 ;
        if (teen.averageScore > 10.0) {
            teen.averageScore = 10.0;
        }
    }

    public void setAverageScore(Baby baby) {
        baby.averageScore = Constants.AVERAGE_SCORE_BABY;
    }
}
