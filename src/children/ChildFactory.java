package children;

import fileio.ChildInput;

public class ChildFactory {

    private static ChildFactory instance =  null;

    private ChildFactory() {

    }
    public static ChildFactory getInstance() {
        if (instance == null) {
            instance = new ChildFactory();
        }
        return instance;
    }

    /**
     * creates based on age, the correct category age object
     * @param childInput child input data through which we construct the object
     * @return a child category object
     */
    public static Child create(final ChildInput childInput) {

        if (childInput.getAge() >= 12 && childInput.getAge() <= 18) {
            return new Teen(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference());
        }
        if (childInput.getAge() >= 5 && childInput.getAge() < 12) {
            return new Kid(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference());
        }
        if (childInput.getAge() < 5) {
            return new Baby(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference());
        }
        return null;
    }

    /**
     * creates based on age, the correct category age object
     * @param child child data through which we construct the object
     * @return a child category object
     */
    public static Child create(final Child child) {

        if (child.getAge() >= 12 && child.getAge() <= 18) {
            return new Teen(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore());
        }
        if (child.getAge() >= 5 && child.getAge() < 12) {
            return new Kid(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore());
        }
        if (child.getAge() < 5) {
            return new Baby(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore());
        }
        return null;
    }
}
