package fileio;

import children.Child;
import children.ChildFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AnnualChildrenOutput {
    private List<Child> children = new ArrayList<>();

    /**
     * adds child to the final children output list
     * @param child child  object to be added
     */
    public void setChildren(final Child child) {
        ChildFactory childFactory = ChildFactory.getInstance();
        children.add(childFactory.create(child));
    }

    public List<Child> getChildren() {
        return children;
    }

    /**
     * sorts children
     */
    public void sortChildren() {
        Collections.sort(children);
    }

    @Override
    public String toString() {
        return "AnnualChildrenOutput{"
                + "children="
                + children
                + '}';
    }
}
