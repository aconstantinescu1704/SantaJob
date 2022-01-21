package santa;

import database.AnnualData;



public interface AssignationStrategy {
    /**
     * method that assigns the presents to the children based on
     * a certain strategy
     * @param annualData
     */
     void assign(AnnualData annualData);
}
