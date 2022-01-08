package fileio;

import java.util.List;

public final class SantaJobInput {
    private int numberOfYears;
    private int santaBudget;
    private Double budgetUnit;
    private InitialDataInput initialData;
    private List<AnnualChangesInput> annualChanges;

    public SantaJobInput(final int numberOfYears, final int santaBudget,
                         final InitialDataInput initialData,
                         final List<AnnualChangesInput> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
        this.budgetUnit = 0.0;
    }

    public Double getBudgetUnit() {
        return this.budgetUnit;
    }


    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public InitialDataInput getInitialData() {
        return initialData;
    }

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
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
