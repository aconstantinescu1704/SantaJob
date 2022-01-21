package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import santa.Present;
import database.ChildrenUpdatesData;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * The method reads the database
     *
     * @return an Input Data object
     */
    public SantaJobInput readInitialData() throws IOException {
        JSONParser jsonParser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        int numberOfYears = 0;
        int santaBuget = 0;
        InitialDataInput initialDataInput = null;
        ArrayList<ChildInput> children = new ArrayList<>();
        ArrayList<Present> presents = new ArrayList<>();
        ArrayList<AnnualChangesInput> annualChangesInputs = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            if (jsonObject != null) {
                numberOfYears = ((Long) ((JSONObject) jsonObject).get("numberOfYears")).intValue();
                santaBuget = ((Long) ((JSONObject) jsonObject).get("santaBudget")).intValue();

                JSONObject jsonInitialData = (JSONObject) jsonObject.get("initialData");
                JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get("annualChanges");

                if (jsonInitialData != null) {
                    JSONArray jsonChildren = (JSONArray) jsonInitialData.get("children");
                    if (jsonChildren != null) {
                        for (Object jsonChild : jsonChildren) {
                            children.add(new ChildInput(
                                    ((Long) ((JSONObject) jsonChild).get("id")).intValue(),
                                    (String) ((JSONObject) jsonChild).get("lastName"),
                                    (String) ((JSONObject) jsonChild).get("firstName"),
                                    ((Long) ((JSONObject) jsonChild).get("age")).intValue(),
                                    (String) ((JSONObject) jsonChild).get("city"),
                                    ((Long) ((JSONObject) jsonChild).get("niceScore"))
                                            .doubleValue(),
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                            .get("giftsPreferences")),
                                    ((Long) ((JSONObject) jsonChild).get("niceScoreBonus"))
                                            .doubleValue(),
                                    (String) ((JSONObject) jsonChild).get("elf")));

                        }
                    }
                }
                JSONArray jsonPrsents = (JSONArray) jsonInitialData.get("santaGiftsList");
                if (jsonPrsents != null) {
                    for (Object jsonPresent : jsonPrsents) {
                        presents.add(new Present(
                                (String) ((JSONObject) jsonPresent).get("productName"),
                                ((Long) ((JSONObject) jsonPresent).get("price")).doubleValue(),
                                (String) ((JSONObject) jsonPresent).get("category"),
                                ((Long) ((JSONObject) jsonPresent).get("quantity")).intValue()));
                    }
                }

                initialDataInput = new InitialDataInput(children, presents);

                if (jsonAnnualChanges != null) {
                    for (Object jsonAnnualChange : jsonAnnualChanges) {

                        ArrayList<ChildInput> newChildren = new ArrayList<>();
                        ArrayList<Present> newPresents = new ArrayList<>();
                        ArrayList<ChildrenUpdatesData> childrenUpdates = new ArrayList<>();

                        int newSantaBuget = ((Long) ((JSONObject) jsonAnnualChange)
                                .get("newSantaBudget")).intValue();
                        JSONArray jsonNewPresents = (JSONArray) ((JSONObject) jsonAnnualChange)
                                .get("newGifts");
                        if (jsonNewPresents != null) {
                            for (Object jsonNewPresent : jsonNewPresents) {
                                newPresents.add(new Present(
                                        (String) ((JSONObject) jsonNewPresent).get("productName"),
                                        ((Long) ((JSONObject) jsonNewPresent).get("price"))
                                                .doubleValue(),
                                        (String) ((JSONObject) jsonNewPresent).get("category"),
                                        ((Long) ((JSONObject) jsonNewPresent).get("quantity")).intValue()));
                            }
                        }

                        JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonAnnualChange)
                                .get("newChildren");
                        if (jsonNewChildren != null) {
                            for (Object jsonNewChild : jsonNewChildren) {
                                newChildren.add(new ChildInput(
                                        ((Long) ((JSONObject) jsonNewChild).get("id")).intValue(),
                                        (String) ((JSONObject) jsonNewChild).get("lastName"),
                                        (String) ((JSONObject) jsonNewChild).get("firstName"),
                                        ((Long) ((JSONObject) jsonNewChild).get("age")).intValue(),
                                        (String) ((JSONObject) jsonNewChild).get("city"),
                                        ((Long) ((JSONObject) jsonNewChild).get("niceScore"))
                                                .doubleValue(),
                                        Utils.convertJSONArray((JSONArray)
                                                ((JSONObject) jsonNewChild)
                                                        .get("giftsPreferences")),
                                        ((Long) ((JSONObject) jsonNewChild).get("niceScoreBonus"))
                                                .doubleValue(),
                                        (String) ((JSONObject) jsonNewChild).get("elf")));

                            }
                        }
                        JSONArray jsonChildrenUpdate = (JSONArray) ((JSONObject) jsonAnnualChange)
                                .get("childrenUpdates");
                        if (jsonChildrenUpdate != null) {
                            for (Object jsonChildUpdate : jsonChildrenUpdate) {
                                if (((Long) ((JSONObject) jsonChildUpdate)
                                        .get("niceScore")) == null) {
                                    childrenUpdates.add(new ChildrenUpdatesData(
                                            ((Long) ((JSONObject) jsonChildUpdate).get("id"))
                                                    .intValue(), Constants.SCORE_NULL,
                                            Utils.convertJSONArray((JSONArray)
                                                    ((JSONObject) jsonChildUpdate)
                                                            .get("giftsPreferences")),
                                            (String) ((JSONObject) jsonChildUpdate).get("elf")));
                                } else {
                                    childrenUpdates.add(new ChildrenUpdatesData(
                                            ((Long) ((JSONObject) jsonChildUpdate)
                                                    .get("id")).intValue(),
                                            ((Long) ((JSONObject) jsonChildUpdate)
                                                    .get("niceScore")).doubleValue(),
                                            Utils.convertJSONArray((JSONArray)
                                                    ((JSONObject) jsonChildUpdate)
                                                            .get("giftsPreferences")),
                                            (String) ((JSONObject) jsonChildUpdate).get("elf")));
                                }
                            }
                        }
                        String strategy = (String) ((JSONObject) jsonAnnualChange).get("strategy");
                        annualChangesInputs.add(new AnnualChangesInput(newSantaBuget,
                                newPresents, newChildren, childrenUpdates, strategy));
                    }
                }

            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new SantaJobInput(numberOfYears, santaBuget,
                initialDataInput, annualChangesInputs);
    }

}
