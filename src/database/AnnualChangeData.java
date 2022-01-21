package database;

import children.Child;
import children.ChildFactory;
import fileio.AnnualChangesInput;
import santa.AssignationStrategy;
import santa.AverageScoreStrategy;
import santa.IdStrategy;
import santa.NiceScoreCityStrategy;
import santa.Present;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChangeData {
    private int newSantaBudget;
    private List<Present> newGifts = new ArrayList<>();
    private List<Child> newChildren = new ArrayList<>();
    private List<ChildrenUpdatesData> childrenUpdates = new ArrayList<>();
    private String strategy;

    public AnnualChangeData(final AnnualChangesInput annualChangesInput) {
        this.newSantaBudget = annualChangesInput.getNewSantaBudget();
        this.newGifts = annualChangesInput.getNewGifts();
        ChildFactory childFactory = ChildFactory.getInstance();
        for (var childInput : annualChangesInput.getNewChildren()) {
            if (childFactory.create(childInput) != null) {
                newChildren.add(childFactory.create(childInput));
            }
        }
        this.childrenUpdates = annualChangesInput.getChildrenUpdates();
        this.strategy = annualChangesInput.getStrategy();
    }

    /**
     * methods that searches for a given child in the new list of annual children
     * @param id the id of the searched child
     * @return true if child was found / false if child was not found
     */
    public boolean foundNew(final int id) {
        for (Child child : newChildren) {
             if (child.getId() == id) {
                 return true;
             }
        }
        return false;
    }

    /**
     * method that sets strategy based on the given stored strategy as field
     * @return the object required by the strategy string
     */
    public AssignationStrategy setStrategy() {
        if (strategy.equals("id")) {
            return new IdStrategy();
        }
        if (strategy.equals("niceScore")) {
            return new AverageScoreStrategy();
        }
        if (strategy.equals("niceScoreCity")) {
            return new NiceScoreCityStrategy();
        }
        return null;
    }

    public int getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<Present> getNewGifts() {
        return newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public List<ChildrenUpdatesData> getChildrenUpdates() {
        return childrenUpdates;
    }
}
