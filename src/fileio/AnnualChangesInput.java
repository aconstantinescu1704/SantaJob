package fileio;

import santa.Present;
import database.ChildrenUpdatesData;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChangesInput {
    private int newSantaBudget;
    private List<Present> newGifts = new ArrayList<>();
    private List<ChildInput> newChildren = new ArrayList<>();
    private List<ChildrenUpdatesData> childrenUpdates = new ArrayList<>();
    private String strategy;

    public AnnualChangesInput(final int newSantaBudget, final List<Present> newGifts,
                               final List<ChildInput> newChildren,
                               final List<ChildrenUpdatesData> childrenUpdates,
                               final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public int getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<Present> getNewGifts() {
        return newGifts;
    }

    public List<ChildInput> getNewChildren() {
        return newChildren;
    }

    public List<ChildrenUpdatesData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }
}
