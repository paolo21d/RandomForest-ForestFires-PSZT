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
            RandomForest forest = loadForest();
            System.out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DecisionTree buildDecisionTree() throws IOException{
        SamplesImporter importer = new SamplesImporter();
        List<Sample> samples = importer.readSamplesFromFile("src/main/resources/shortData.txt");
        DecisionTree decisionTree = new DecisionTree();
        List<Argument.ArgumentType> argumentTypes = new ArrayList<>();
        ///
        argumentTypes.add(Argument.ArgumentType.X);
        argumentTypes.add(Argument.ArgumentType.Y);
        argumentTypes.add(Argument.ArgumentType.FFMC);
        argumentTypes.add(Argument.ArgumentType.DMC);
        argumentTypes.add(Argument.ArgumentType.DC);
        argumentTypes.add(Argument.ArgumentType.ISI);
        argumentTypes.add(Argument.ArgumentType.TEMP);
        argumentTypes.add(Argument.ArgumentType.RH);
        argumentTypes.add(Argument.ArgumentType.WIND);
        argumentTypes.add(Argument.ArgumentType.RAIN) ;

        argumentTypes.add(Argument.ArgumentType.JAN);
        argumentTypes.add(Argument.ArgumentType.FEB);
        argumentTypes.add(Argument.ArgumentType.MAR);
        argumentTypes.add(Argument.ArgumentType.APR);
        argumentTypes.add(Argument.ArgumentType.MAY);
        argumentTypes.add(Argument.ArgumentType.JUN);
        argumentTypes.add(Argument.ArgumentType.JUL);
        argumentTypes.add(Argument.ArgumentType.AUG);
        argumentTypes.add(Argument.ArgumentType.SEP);
        argumentTypes.add(Argument.ArgumentType.OCT);
        argumentTypes.add(Argument.ArgumentType.NOV);
        argumentTypes.add(Argument.ArgumentType.DEC);

        argumentTypes.add(Argument.ArgumentType.MON);
        argumentTypes.add(Argument.ArgumentType.TUE);
        argumentTypes.add(Argument.ArgumentType.WED);
        argumentTypes.add(Argument.ArgumentType.THU);
        argumentTypes.add(Argument.ArgumentType.FRI);
        argumentTypes.add(Argument.ArgumentType.SAT);
        argumentTypes.add(Argument.ArgumentType.SUN);

        ///
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
}
