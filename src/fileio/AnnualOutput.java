package fileio;

import java.util.ArrayList;
import java.util.List;

public final class AnnualOutput {
    private List<AnnualChildrenOutput> annualChildren = new ArrayList<>();

    public List<AnnualChildrenOutput> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * adds new annual children output to the final output
     * @param children annual children output (list of children) to be added
     *                 to the final output
     */
    public void setAnnualChildrenOutputs(final AnnualChildrenOutput children) {
        this.annualChildren.add(children);

    }
}
