package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node {

    private Double value; //wartosc oczekiwana - powierzchnia spaloana - srednia wartosc spalonej powierzchni poszczególnych sampli
    private Argument divisionArgument; //punkt podzialu jest w środku; liść nie ma tego parametru
    private Double MSE;
    private Double SD;
    private Integer size;

    private transient Node parent;
    private Node lowerSon, biggerSon; //tree structure
    private List<Sample> samples;

    public void countValue() {
        value = samples.stream().mapToDouble(Sample::getResult).average().orElse(0.0);
    }

}
