package children;

import common.Constants;
import fileio.ChildInput;

public final class ChildFactory {

    private static ChildFactory instance =  null;

    private ChildFactory() {

    }

    /**
     *
     * @return
     */
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

        if (childInput.getAge() >= Constants.AGE_LAST_KID
                && childInput.getAge() <= Constants.AGE_LAST_TEEN) {
            return new Teen(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference(), childInput.getNiceScoreBonus(),
                    childInput.getElf());
        }
        if (childInput.getAge() >= Constants.AGE_LAST_BABY
                && childInput.getAge() < Constants.AGE_LAST_KID) {
            return new Kid(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference(), childInput.getNiceScoreBonus(),
                    childInput.getElf());
        }
        if (childInput.getAge() < Constants.AGE_LAST_BABY) {
            return new Baby(childInput.getId(), childInput.getLastName(),
                    childInput.getFirstName(), childInput.getAge(),
                    childInput.getCity(), childInput.getNiceScore(),
                    childInput.getGiftsPreference(), childInput.getNiceScoreBonus(),
                    childInput.getElf());
        }
        return null;
    }

    /**
     * creates based on age, the correct category age object
     * @param child child data through which we construct the object
     * @return a child category object
     */
    public static Child create(final Child child) {

        if (child.getAge() >= Constants.AGE_LAST_KID
                && child.getAge() <= Constants.AGE_LAST_TEEN) {
            return new Teen(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreBonus(), child.getElf());
        }
        if (child.getAge() >= Constants.AGE_LAST_BABY
                && child.getAge() < Constants.AGE_LAST_KID) {
            return new Kid(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreBonus(), child.getElf());
        }
        if (child.getAge() < Constants.AGE_LAST_BABY) {
            return new Baby(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getCity(),
                    child.getNiceScore(), child.getAssignedBudget(),
                    child.getNiceScoreHistory(), child.getReceivedGifts(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreBonus(), child.getElf());
        }
        return null;
    }
}
