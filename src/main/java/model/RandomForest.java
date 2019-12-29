package model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

@Getter
@Setter
public class RandomForest {
    List<DecisionTree> decisionTrees;


    //TODO random forest
    public void buildRandomForest(List<Sample> samples, int treesQuantity) {
        Integer treeNumber =  (int) Math.ceil(Math.sqrt(samples.size()));
        Integer argumentNumber = (int) Math.floor(treeNumber);
        List<Argument.ArgumentType> argumentTypes = Argument.getAllTypes();
        Random rand = new Random(1);

        for (int i = 0; i < treeNumber; i++){
            List<Sample> treeSamples = new ArrayList<>();
            List<Argument.ArgumentType> argumentSample = new ArrayList<>();
            for(int j = 0; j < treeNumber; j++){
                treeSamples.add(samples.get(rand.nextInt(samples.size())));
            }

            for(int j = 0; j < argumentNumber; j++){
                //TODO
            }


        }
    }

    public void saveStructure() throws IOException {
        Serializer serializer = new Serializer();
        String json = serializer.toJSON(decisionTrees);

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/serializedStructure.json"));
        writer.write(json);
        writer.close();
    }

    public void loadStructure() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/serializedStructure.json"));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            json.append(line);
            line = reader.readLine();
        }
        Serializer serializer = new Serializer();
        serializer.fromJSON(json.toString());
        decisionTrees = new ArrayList<>(serializer.getDecisionTrees());

        for (DecisionTree tree : decisionTrees) {
            tree.restoreNodesParent();
        }
    }

    public Double getResult(Sample sample) {
        List<Double> results = new ArrayList<>();
        for (DecisionTree tree : decisionTrees) {
            results.add(tree.getResult(sample));
        }
        OptionalDouble average = results.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
