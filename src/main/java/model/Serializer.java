package model;

import com.google.gson.Gson;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Serializer {
    private LocalDateTime date;
    private List<DecisionTree> decisionTrees = new ArrayList<>();

    public String toJSON(List<DecisionTree> trees) {
        date = LocalDateTime.now();
        decisionTrees = new ArrayList<>(trees);
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Serializer fromJSON(String json) {
        Gson gson = new Gson();
        Serializer tmp = gson.fromJson(json, Serializer.class);
        date = tmp.date;
        decisionTrees = tmp.decisionTrees;
        return tmp;
    }
}
