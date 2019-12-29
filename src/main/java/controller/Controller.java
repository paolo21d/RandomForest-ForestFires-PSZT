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
            //saveForest();
            //RandomForest forest = loadForest();
            result();
            saveForest();
            //System.out.println(result());
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
        for(Sample sample : samples){
            Double result = tree.getResult(sample);
            System.out.println("Oczekiwana: " + sample.getResult() + " Przewidziana " + result);
        }
        return 0.0;
        //return tree.getResult(sample);
    }
}
