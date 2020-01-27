package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node {

    private Double value; //expected value - burned area - average value of burned area of each sample
    private Argument divisionArgument; //division point value is inside Argument.value; leaf hasn't this parameter
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
