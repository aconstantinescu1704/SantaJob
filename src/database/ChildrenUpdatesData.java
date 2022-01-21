package database;

import children.Child;
import children.VisitAverageScore;
import common.Constants;

import java.util.ArrayList;

public final class ChildrenUpdatesData {
    private int id;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;
    private String elf;

    public ChildrenUpdatesData(final int id, final Double niceScore,
                               final ArrayList<String> giftsPreferences,
                               final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public String getElf() {
        return elf;
    }

    /**
     * method that updates a child's data based on annual input
     * @param child the child with the given id for which we apply the changes
     */
    public void updateChild(final Child child) {
        VisitAverageScore visitAverageScore = new VisitAverageScore();
        if (niceScore != Constants.SCORE_NULL) {
            child.setHistoryScore(niceScore);
            child.acceptAverageScore(visitAverageScore);
        }
        for (String gifts : giftsPreferences) {
            if (child.getGiftsPreferences().contains(gifts)) {
                child.getGiftsPreferences().remove(gifts);
            }
        }
        child.setElf(this.elf);
        child.setNewPreferences(giftsPreferences);
    }

}
