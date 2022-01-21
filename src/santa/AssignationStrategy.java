package santa;

import children.Child;
import database.AnnualData;

import java.util.List;

public interface AssignationStrategy {
    public void assign (AnnualData annualData);
}
