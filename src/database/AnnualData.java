package database;
import children.Child;
import children.ChildFactory;
import children.Kid;
import children.Teen;
import children.VisitAverageScore;
import common.Constants;
import fileio.InitialDataInput;
import santa.Present;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class AnnualData {
    private List<Child> children =  new ArrayList<>();
    private List<Present> santaGiftsList = new ArrayList<>();

    public AnnualData(final InitialDataInput initialDataInput) {

        try {
            if (initialDataInput.getChildren() != null) {
                for (var childInput : initialDataInput.getChildren()) {
                    if (ChildFactory.create(childInput) != null) {
                        children.add(ChildFactory.create(childInput));
                    }
                }
                this.santaGiftsList = initialDataInput.getSantaGiftsList();
            }
        } catch (NullPointerException e) {

        }
    }

    /**
     * add all new children to annual children list and resets their average score
     * @param children the new list of children received from annual changes
     */
    public void setNewChildren(final List<Child> children) {
        VisitAverageScore visitAverageScore = new VisitAverageScore();
        for (Child child : children) {
            child.setHistoryScore(child.getNiceScore());
            child.acceptAverageScore(visitAverageScore);
        }
        this.children.addAll(children);
    }

    /**
     * method that finds a child in annual children list based on id
     * @param id the given id of the searched child
     * @return the found child
     */
    public Child findChild(final int id) {
        for (Child child : children) {
            if (child.getId() == id) {
               return child;
            }
        }
        return null;
    }

    /**
     * method that adds new presents to the already existing list of presents
     * @param presents the list of new presents received at input
     */
    public void setNewPresents(final List<Present> presents) {
        for (var present : presents) {
            santaGiftsList.add(present);
        }
    }
    public List<Child> getChildren() {
        return children;
    }

    public List<Present> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     */
    public void sortPresents() {
        Collections.sort(santaGiftsList);
    }

    /**
     * method that searches for the first present
     * from given category  in the annual list of presents
     * @param gift the name of the category of present we are searching for
     * @return the first found present in the given category from annual list
     */
    public Present getPresent(final String gift) {
        for (Present present : santaGiftsList) {
            if (present.getCategory().equals(gift)
                    && present.getQuantity() > 0) {
                return present;
            }
        }

        return null;
    }

    /**
     * method that annually updates the age of all children and changes the age category
     * by deleting and re-adding it as another age category
     */
    public void annualGrowth() {
        for (var child : children) {
            child.setAge(child.getAge() + 1);
        }
        Iterator<Child> iter = children.iterator();
        List<Child> newTypeChildren = new ArrayList<>();
        VisitAverageScore visitAverageScore = new VisitAverageScore();
        while (iter.hasNext()) {
            Child child = iter.next();
            if (child.getAge() == Constants.AGE_LAST_BABY) {
                Kid kid = new Kid(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), child.getGiftsPreferences(),
                        child.getNiceScoreBonus(), child.getElf());
                kid.setNiceScoreHistory(child.getNiceScoreHistory());
                kid.acceptAverageScore(visitAverageScore);
                newTypeChildren.add(kid);
                iter.remove();
            } else {
                if (child.getAge() == Constants.AGE_LAST_KID) {
                    Teen teen = new Teen(child.getId(), child.getLastName(),
                            child.getFirstName(), child.getAge(), child.getCity(),
                            child.getNiceScore(), child.getGiftsPreferences(),
                            child.getNiceScoreBonus(), child.getElf());
                    teen.setNiceScoreHistory(child.getNiceScoreHistory());
                    teen.acceptAverageScore(visitAverageScore);
                    newTypeChildren.add(teen);
                    iter.remove();
                } else {
                    if (child.getAge() > Constants.AGE_LAST_TEEN) {
                        iter.remove();
                    }
                }
            }
        }
        children.addAll(newTypeChildren);
    }

    /**
     * method that introduces new distributing method if the child's elf is yellow
     * @param child the child for which we check apply the property
     */
    public void yellowElf(final Child child) {
        if (child.getElf().equals("yellow")) {
            if (child.getReceivedGifts().isEmpty()) {
                sortPresents();
                for (Present present : santaGiftsList) {
                    if (present.getCategory().equals(child.getGiftsPreferences().get(0))) {
                        if (present.getQuantity() > 0) {
                            child.setReceivedGifts(present);
                            present.changeQuantity();
                        }
                        break;
                    }
                }
            }
        }
    }
}
