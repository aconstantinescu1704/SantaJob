package database;

import children.Child;
import java.util.ArrayList;

public final class ChildrenUpdatesData {
    private int id;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildrenUpdatesData(final int id, final Double niceScore,
                               final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
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

    /**
     * method that updates a child's data based on annual input
     * @param child the child with the given id for which we apply the changes
     */
    public void updateChild(final Child child) {
        if (niceScore != -1) {
            child.setHistoryScore(niceScore);
            child.setAverageScore();
        }
        for (String gifts : giftsPreferences) {
            if (child.getGiftsPreferences().contains(gifts)) {
                child.getGiftsPreferences().remove(gifts);
            }
        }
        child.setNewPreferences(giftsPreferences);
    }

}
