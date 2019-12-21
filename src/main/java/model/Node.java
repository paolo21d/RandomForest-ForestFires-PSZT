package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node {

    private Node parent, lowerSon, biggerSon; //tree structure
    private Double value; //wartosc oczekiwana - powierzchnia spaloana - srednia wartosc spalonej powierzchni poszczególnych sampli
    private Argument divisionArgument; //ptk podzialu jest w środku
    private List<Sample> samples;

    private void countValue() {
        value = samples.stream().mapToDouble(Sample::getResult).average().orElse(0.0);
    }

}
