package fileio;

import santa.Present;

import java.util.ArrayList;
import java.util.List;

public final class InitialDataInput {
    private List<ChildInput> children =  new ArrayList<>();
    private List<Present> santaGiftsList = new ArrayList<>();

    public InitialDataInput(final List<ChildInput> children,
                            final List<Present> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }


    public List<ChildInput> getChildren() {
        return children;
    }

    public List<Present> getSantaGiftsList() {
        return santaGiftsList;
    }
}
