package children;

import com.fasterxml.jackson.annotation.JsonIgnore;
import common.Constants;
import santa.Present;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public abstract class Child implements Comparable<Child> {
    private final int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    private ArrayList<String> giftsPreferences = new ArrayList<>();
    protected Double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    @JsonIgnore
    private Double niceScore;
    private Double assignedBudget;
    private ArrayList<Present> receivedGifts = new ArrayList<>();
    @JsonIgnore
    private Double niceScoreBonus;
    @JsonIgnore
    private String elf;

    public Child(final int id, final String lastName, final String firstName,
                 final int age, final String city, final Double niceScore,
                 final ArrayList<String> giftsPreference, final Double niceScoreBonus,
                 final String elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences.addAll(giftsPreference);
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    public Child(final int id, final String lastName, final String firstName,
                 final int age, final String city, final Double niceScore,
                 final Double assignedBudget, final ArrayList<Double> historyScore,
                 final ArrayList<Present> giftsReceived, final ArrayList<String> giftsPreference,
                 final Double niceScoreBonus, final String elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.assignedBudget = assignedBudget;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(historyScore);
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(giftsPreference);

        this.receivedGifts = new ArrayList<>();
        for (var present : giftsReceived) {
            this.receivedGifts.add(new Present(present.getProductName(),
                    present.getPrice(), present.getCategory(), present.getQuantity()));
        }
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    /**
     * sets the average score for all age category through separate implementations
     */
    public abstract void acceptAverageScore(VisitAverageScore visitor);

    /**
     *
     * @return the average score after being set by each implementation based on age
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     *
     * @return the assigned budget for each child
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * calculates the assigned budget for each child based on a given formula
     * @param budgetUnit the budget unit set each year based
     *                  on average scores and santa budget
     */
    public void setAssignedBudget(final Double budgetUnit) {
        this.assignedBudget = budgetUnit * getAverageScore();
    }

    /**
     * resets the received gifts list
     */
    public void resetReceivedGifts() {
        receivedGifts.clear();
        receivedGifts = new ArrayList<>();
    }

    /**
     * adds another gift to the receivedGift list
     * @param gift the given present to be added
     */
    public void setReceivedGifts(final Present gift) {
        receivedGifts.add(new Present(gift.getProductName(), gift.getPrice(),
                gift.getCategory(), gift.getQuantity()));
    }

    /**
     *
     * @return
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     *
     * @return
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     *
     * @return
     */
    public String getElf() {
        return elf;
    }

    /**
     *
     * @param niceScoreHistory the nice scoreHistory to be copied to existing nice score
     *                         history
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory.addAll(niceScoreHistory);
    }

    /**
     * @param score the score to be added to history score list
     */
    public void setHistoryScore(final Double score) {
        this.niceScoreHistory.add(score);
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * sets new gift preference list based on new gift category preferences received
     * at annual input change
     * @param giftsCategory gift category preferences received
     */
    public void setNewPreferences(final ArrayList<String> giftsCategory) {

        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (int i = 0; i < giftsCategory.size(); i++) {
           set.add(giftsCategory.get(i));
        }
        int indx = 0;
        for (var present : set) {
            giftsPreferences.add(indx, present);
            indx++;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     *
     * @return
     */
    public ArrayList<Present> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     *
     */
    public void elfChanges() {
        if (elf.equals("black")) {
            assignedBudget = assignedBudget - assignedBudget * Constants.BUDGET_CHANGE_FACTOR
                    / Constants.FULL_PERCENTAGE;
        }
        if (elf.equals("pink")) {
            assignedBudget = assignedBudget + assignedBudget * Constants.BUDGET_CHANGE_FACTOR
                    / Constants.FULL_PERCENTAGE;
        }
    }

    /**
     *
     * @param elf
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }

    /**
     * method that compares the id of the children
     * and so implements the method from the Comparable interface
     * @param o other child that we compare to
     * @return 1 - if this > o / -1 this < o 0 - this = o
     */
    public int compareTo(final Child o) {
        return Double.compare(id, o.id);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Child{"
                + "\nid="
                + id
                + ", \nlastName='"
                + lastName + '\''
                + ", \nfirstName='"
                + firstName
                + '\''
                + ", \nage=" + age
                + ", \ncity='" + city + '\''
                + ", \nniceScore=" + niceScore
                + ", \nassignedBudget=" + assignedBudget
                + ", \nhistoryScore=" + niceScoreHistory
                + ", \ngiftsReceived=" + receivedGifts.toString()
                + ", \ngiftsPreference=" + giftsPreferences
                + '}';
    }
}
