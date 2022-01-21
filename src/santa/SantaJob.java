package santa;

import children.VisitAverageScore;
import fileio.AnnualChildrenOutput;
import fileio.AnnualOutput;
import children.Child;
import fileio.SantaJobInput;
import database.AnnualChangeData;
import database.ChildrenUpdatesData;
import database.AnnualData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SantaJob {
    private int numberOfYears;
    private int santaBudget;
    private Double budgetUnit;
    private AnnualData initialData;
    private List<AnnualChangeData> annualChanges;


    public SantaJob(final SantaJobInput santaJobInput) {
        this.numberOfYears = santaJobInput.getNumberOfYears();
        this.santaBudget = santaJobInput.getSantaBudget();
        this.initialData = new AnnualData(santaJobInput.getInitialData());
        this.annualChanges = new ArrayList<AnnualChangeData>();
        for (int i = 0; i < numberOfYears; i++) {
           annualChanges.add(new AnnualChangeData(santaJobInput.getAnnualChanges().get(i)));
        }

        this.budgetUnit = 0.0;
    }

    public void setSantaBudget(final int santaBudget) {
        this.santaBudget = santaBudget;
    }

    public Double getBudgetUnit() {
        return this.budgetUnit;
    }

    /**
     * method that sets the budget unit based on children's average score
     * @param children anual list of children
     */
    public void setBudgetUnit(final List<Child> children) {
        Double sumScores = 0.0;
        Collections.sort(children);
        for (Child child : children) {
            sumScores = sumScores + child.getAverageScore();
        }
        budgetUnit = santaBudget / sumScores;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public AnnualData getInitialData() {
        return initialData;
    }

    public List<AnnualChangeData> getAnnualChanges() {
        return annualChanges;
    }

    /**
     * method that annually updates the database based on the stored data
     * and sets the received gifts for all children
     * @return annualOutput that will be printed in output files
     */
    public AnnualOutput annualJob() {

        AnnualOutput annualOutput = new AnnualOutput();
        AnnualChildrenOutput initial = new AnnualChildrenOutput();
        VisitAverageScore visitAverageScore = new VisitAverageScore();
        //runda 0
        for (var child : initialData.getChildren()) {
            child.setHistoryScore(child.getNiceScore());
            child.acceptAverageScore(visitAverageScore);
        }
        setBudgetUnit(initialData.getChildren());
        for (var child : initialData.getChildren()) {
            child.setAssignedBudget(budgetUnit);
            child.elfChanges();
        }
        initialData.sortPresents();
        AssignationStrategy initialStrategy = new IdStrategy();
        initialStrategy.assign(initialData);
        for (var child : initialData.getChildren()) {
            initial.setChildren(child);
        }
        annualOutput.setAnnualChildrenOutputs(initial);

        for (int i = 0; i < numberOfYears; i++) {

            AnnualChildrenOutput childrenOutput = new AnnualChildrenOutput();
            initialData.annualGrowth();
            initialData.setNewChildren(annualChanges.get(i).getNewChildren());
            initialData.setNewPresents(annualChanges.get(i).getNewGifts());

            for (ChildrenUpdatesData childrenUpdate : annualChanges.get(i).getChildrenUpdates()) {
                Child child = initialData.findChild(childrenUpdate.getId());
                if (child != null) {
                    childrenUpdate.updateChild(child);
                }
            }

            setSantaBudget(annualChanges.get(i).getNewSantaBudget());
            setBudgetUnit(initialData.getChildren());

            //set alocated budget
            for (var child : initialData.getChildren()) {
                child.setAssignedBudget(budgetUnit);
                child.elfChanges();
            }

            initialData.sortPresents();
            for (var child : initialData.getChildren()) {
                child.resetReceivedGifts();
            }

            AssignationStrategy assignationStrategy = annualChanges.get(i).setStrategy();
            assignationStrategy.assign(initialData);

            for (var child : initialData.getChildren()) {
                initialData.yellowElf(child);
                childrenOutput.setChildren(child);
            }

            childrenOutput.sortChildren();
            annualOutput.setAnnualChildrenOutputs(childrenOutput);
        }
        return annualOutput;
    }

    @Override
    public String toString() {
        return "SantaJobInput{"
                + "numberOfYears=" + numberOfYears
                + ", santaBudget=" + santaBudget
                + ", initialData=" + initialData
                + ", annualChanges=" + annualChanges
                + '}';
    }
}
