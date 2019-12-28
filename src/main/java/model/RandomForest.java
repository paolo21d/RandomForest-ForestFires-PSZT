package model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RandomForest {
    List<DecisionTree> decisionTrees;

    public void buildRandomForest(List<Sample> samples, int treesQuantity) {

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

        for(DecisionTree tree: decisionTrees) {
            tree.restoreNodesParent();
        }
    }

}
