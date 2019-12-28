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

    public static void main(String[] args){
        try {
            //saveForest();
            //RandomForest forest = loadForest();
            System.out.println(result());
            System.out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DecisionTree buildDecisionTree() throws IOException{
        SamplesImporter importer = new SamplesImporter();
        List<Sample> samples = importer.readSamplesFromFile("src/main/resources/shortData.txt");
        DecisionTree decisionTree = new DecisionTree();
        List<Argument.ArgumentType> argumentTypes = Argument.getAllTypes();

        decisionTree.buildTree(argumentTypes, samples);
        return decisionTree;
    }

    private static void saveForest() throws IOException{
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

    private static Double result() throws IOException{
        DecisionTree tree = buildDecisionTree();
        Sample sample = new Sample(7.0, 5.0, "mar", "fri",
                86.2, 26.2, 94.3, 5.1, 8.2, 51.0, 6.7, 0.0, null);
        return tree.getResult(sample);
    }
}
