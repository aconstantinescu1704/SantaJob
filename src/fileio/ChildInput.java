package fileio;

import santa.Present;

import java.util.ArrayList;

public final class ChildInput {
    private final int id;
    private String lastName;
    private String firstName;
    private int age;
    private String city;
    private Double niceScore;
    private ArrayList<Double> historyScore = new ArrayList<>();
    private ArrayList<Present> giftsReceived = new ArrayList<>();
    private ArrayList<String> giftsPreference = new ArrayList<>();

    public ChildInput(final int id, final String lastName, final String firstName,
                      final int age, final String city, final Double niceScore,
                      final ArrayList<String> giftsPreference) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
    }

    public ArrayList<Double> getHistoryScore() {
        return historyScore;
    }

    /**
     */
    public void setHistoryScore() {
        this.historyScore.add(niceScore);
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreference() {
        return giftsPreference;
    }

    @Override
    public String toString() {
        return "ChildInput{"
                + "id="
                + id + ", lastName='"
                + lastName + '\'' + ", firstName='"
                + firstName + '\'' + ", age="
                + age + ", city='" + city
                + '\'' + ", niceScore="
                + niceScore + ", giftsPreference="
                + giftsPreference + '}';
    }
}
