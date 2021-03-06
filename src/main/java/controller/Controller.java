package controller;

import input.SamplesImporter;
import model.Argument;
import model.DecisionTree;
import model.RandomForest;
import model.Sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static void main(String[] args) {
        try {
            SamplesImporter importer = new SamplesImporter();
            List<Sample> samples = importer.readSamplesFromFile("src/main/resources/dataToRead.txt");

            System.out.println(crossValidation(10, samples));
            System.out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DecisionTree buildDecisionTree() throws IOException {
        SamplesImporter importer = new SamplesImporter();
        List<Sample> samples = importer.readSamplesFromFile("src/main/resources/dataToRead.txt");
        DecisionTree decisionTree = new DecisionTree();
        List<Argument.ArgumentType> argumentTypes = Argument.getAllTypes();

        decisionTree.buildTree(argumentTypes, samples);
        return decisionTree;
    }

    private static void saveForest() throws IOException {
        ArrayList<DecisionTree> decisionTrees = new ArrayList<>();
        decisionTrees.add(buildDecisionTree());
        RandomForest forest = new RandomForest();
        forest.setDecisionTrees(decisionTrees);
        forest.saveStructure();
    }

    private static RandomForest loadForest() throws IOException {
        RandomForest forest = new RandomForest();
        forest.loadStructure();
        return forest;
    }

    private static Double result() throws IOException {
        DecisionTree tree = buildDecisionTree();
        SamplesImporter samplesImporter = new SamplesImporter();
        List<Sample> samples = samplesImporter.readSamplesFromFile("src/main/resources/dataToTest");
        for (Sample sample : samples) {
            Double result = tree.getResult(sample);
            System.out.println("Oczekiwana: " + sample.getResult() + " Przewidziana " + result);
        }
        return 0.0;
    }

    private static Double crossValidation(Integer kFold, List<Sample> samples) {
        Integer chunk = samples.size() / kFold;
        Double loss = 0.0;

        for (int i = 0; i < kFold; i++) {
            List<Sample> trainSet = new ArrayList<>(samples);
            List<Sample> testSet = new ArrayList<>();

            for (int j = chunk * i; j < chunk * (i + 1); j++) {
                testSet.add(samples.get(j));
            }

            for (Sample sample : testSet) {
                trainSet.remove(sample);
            }

            RandomForest forest = new RandomForest();
            forest.buildRandomForest(trainSet);

            Double MSE = 0.0;
            for (Sample sample : testSet) {
                System.out.println("Oczekiwana " + sample.getResult() + " Przewidziana " + forest.getResult(sample));
                Double err = (sample.getResult() - forest.getResult(sample));
                MSE += (err * err);
            }
            loss += (MSE / testSet.size());

        }

        return (loss / kFold);
    }
}
