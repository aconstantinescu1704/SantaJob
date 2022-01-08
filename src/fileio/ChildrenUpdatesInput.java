package fileio;

import java.util.ArrayList;

public final class ChildrenUpdatesInput {
    private int id;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildrenUpdatesInput(final int id, final Double niceScore,
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
}
