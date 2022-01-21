package main;

import checker.Checker;
import fileio.AnnualChildrenOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.InputLoader;
import fileio.SantaJobInput;
import santa.SantaJob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        File directory = new File("tests/");
        Path path = Paths.get("output");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File outputDirectory = new File("result");
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String number = file.getName().replaceAll("test", "");
            String filepath = "output/out_" + number;
            File out = new File(filepath);
            InputLoader inputLoader = new InputLoader(file.getAbsolutePath());
            SantaJobInput santaJobInput = inputLoader.readInitialData();
            SantaJob santaJob = new SantaJob(santaJobInput);
            AnnualChildrenOutput annualChildrenOutput = new AnnualChildrenOutput();

            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter fileWriter = new FileWriter(filepath);
            String json = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(santaJob.annualJob());
            fileWriter.write(json);
            fileWriter.close();

        }

        Checker.calculateScore();
    }
}
