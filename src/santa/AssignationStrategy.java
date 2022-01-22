package santa;

import database.AnnualData;



public interface AssignationStrategy {
    /**
     *  method that assigns the presents to all children through different strategies
     * @param annualData
     */
     void assign(AnnualData annualData);
}
