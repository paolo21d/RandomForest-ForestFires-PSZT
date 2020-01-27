package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class DecisionTree {

    Node root;
    Long maxDepth = 10L;
    List<Argument.ArgumentType> argumentTypes = new ArrayList<>();

    public void restoreNodesParent() {
        recursiveRestoreParent(null, root);
    }
    
    private void recursiveRestoreParent(Node parent, Node currentNode) {
        if (currentNode == null)
            return;
        currentNode.setParent(parent);
        recursiveRestoreParent(currentNode, currentNode.getLowerSon());
        recursiveRestoreParent(currentNode, currentNode.getBiggerSon());
    }

    public Double getResult(Sample sample) {
        return recursiveSearch(sample, root);
    }

    private Double recursiveSearch(Sample sample, Node node) {
        if (node.getLowerSon() == null && node.getBiggerSon() == null) { //that's leaf
            return node.getValue();
        }

        Argument divisionArgument = node.getDivisionArgument();
        Double sampleValueOfArgument = sample.getArgumentByType(divisionArgument.getType()).getValue();
        if (sampleValueOfArgument < divisionArgument.getValue()) {
            return recursiveSearch(sample, node.getLowerSon());
        } else {
            return recursiveSearch(sample, node.getBiggerSon());
        }
    }

    public void buildTree(List<Argument.ArgumentType> argumentTypes, List<Sample> samples) {
        root = ID3(argumentTypes, samples, null, 0L);
    }

    private Node ID3(List<Argument.ArgumentType> argumentTypes, List<Sample> samples, Node parent, Long depth) {
        //System.out.println(depth);
        //ilosc probek na tyle mala ze nie warto dzielić, przeuczenie
        //głębokość duża
        if (samples.size() <= 3 || depth >= maxDepth) {
            Node leaf = new Node();
            leaf.setParent(parent);
            leaf.setSamples(samples);
            leaf.countValue();
            leaf.setMSE(countMSE(samples));
            leaf.setSize(samples.size());
            leaf.setSD(Math.sqrt(leaf.getMSE() / samples.size()));
            return leaf;
        }

        //znalezienie argumentu podzialu
        Argument divisionArgument = findDivisionArgument(argumentTypes, samples);

        if (divisionArgument == null) {
            Node leaf = new Node();
            leaf.setParent(parent);
            leaf.setSamples(samples);
            leaf.countValue();
            leaf.setMSE(countMSE(samples));
            leaf.setSize(samples.size());
            leaf.setSD(Math.sqrt(leaf.getMSE() / samples.size()));
            return leaf;
        }

        List<Sample> left = new ArrayList<>();
        List<Sample> right = new ArrayList<>();

        for (Sample sample : samples) {
            if (sample.getArgumentByType(divisionArgument.getType()).getValue() < divisionArgument.getValue()) {
                left.add(sample);
            } else {
                right.add(sample);
            }
        }

        //zbudowanie noda
        Node node = new Node();
        node.setParent(parent);
        node.setSamples(samples);
        node.countValue();
        node.setDivisionArgument(divisionArgument);
        node.setSize(samples.size());
        node.setMSE(countMSE(samples));
        node.setSD(Math.sqrt(node.getMSE() / samples.size()));
        if (left.size() == 0 || right.size() == 0) {
            return node;
        }
        node.setLowerSon(ID3(argumentTypes, left, node, depth + 1));
        node.setBiggerSon(ID3(argumentTypes, right, node, depth + 1));

        return node;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Argument findDivisionArgument(List<Argument.ArgumentType> argumentTypes, List<Sample> samples) {
        List<Triple<Argument.ArgumentType, Double, Double>> CFForArgumentTypes = new ArrayList<>(); //Pair<Argument Type, cost function for this argument type, division point>

        for (Argument.ArgumentType arg : argumentTypes) {
            Double divisionPoint = findDivision(arg, samples);
            Double costFunction = countCF(arg, samples, divisionPoint);
            CFForArgumentTypes.add(new ImmutableTriple<>(arg, costFunction, divisionPoint));
        }

        Argument.ArgumentType divisionArgument = CFForArgumentTypes.get(0).getLeft();
        Double minCF = CFForArgumentTypes.get(0).getMiddle();
        Double divisionPoint = CFForArgumentTypes.get(0).getRight();

        for (Triple<Argument.ArgumentType, Double, Double> triple : CFForArgumentTypes) {
            //System.out.println(countMSE(samples) + " " + minCF);
            if (triple.getMiddle() < minCF) {
                divisionArgument = triple.getLeft();
                minCF = triple.getMiddle();
                divisionPoint = triple.getRight();
            }
        }
        //System.out.println(countMSE(samples) + " " + minCF);

        if (countMSE(samples) <= minCF) {
            //System.out.println("A tutaj co sie dzieje");
            return null;
        }

        return new Argument(divisionArgument, divisionPoint);
    }

    private Double findDivision(Argument.ArgumentType argumentType, List<Sample> samples) {
        List<Pair<Sample, Argument>> sampleArgumentPairs = new ArrayList<>();
        //prepare division points by adding every value of argument into hash set (it sorts list and remove duplicates)
        Set<Double> divisionPoints = new HashSet<>();
        for (Sample sample : samples) {
            Argument arg = sample.getArgumentByType(argumentType);
            sampleArgumentPairs.add(new ImmutablePair<>(sample, arg));
            Double value = arg.getValue();
            divisionPoints.add(value);
        }

        List<Sample> left = new ArrayList<>();
        List<Sample> right = new ArrayList<>();

        Pair<Double, Double> divisionWithMinCF = new ImmutablePair<>(0.0, Double.MAX_VALUE); //division point, cost function for this division

        for (Double divisionPoint : divisionPoints) {
            left.clear();
            right.clear();

            for (Pair<Sample, Argument> sampleArgumentPair : sampleArgumentPairs) {
                if (sampleArgumentPair.getRight().getValue() < divisionPoint) {
                    left.add(sampleArgumentPair.getLeft());
                } else {
                    right.add(sampleArgumentPair.getLeft());
                }
            }

            Double costFunction = countCF(left, right);
            if (costFunction < divisionWithMinCF.getRight()) {
                divisionWithMinCF = new ImmutablePair<>(divisionPoint, costFunction);
            }
        }

        return divisionWithMinCF.getLeft();
    }

    private Double countCF(Argument.ArgumentType argumentType, List<Sample> samples, Double divisionPoint) {
        List<Sample> left = new ArrayList<>();
        List<Sample> right = new ArrayList<>();
        for (Sample sample : samples) {
            Double value = sample.getArgumentByType(argumentType).getValue();
            if (value < divisionPoint) {
                left.add(sample);
            } else {
                right.add(sample);
            }
        }

        return countCF(left, right);
    }

    private Double countCF(List<Sample> leftTree, List<Sample> rightTree) {
        int leftTreeSize = leftTree.size();
        int rightTreeSize = rightTree.size();
        int allTreeSize = leftTreeSize + rightTreeSize;
        //System.out.println(countMSE(leftTree) + " " + leftTreeSize + " " + countMSE(rightTree) + " " + rightTreeSize + " " + allTreeSize);
        Double left = countMSE(leftTree) * ((double) leftTreeSize / (double) allTreeSize);
        Double right = countMSE(rightTree) * ((double) rightTreeSize / (double) allTreeSize);
        //System.out.println(left + right);
        return left + right;
    }

    private Double countMSE(List<Sample> samples) {
        double mse = 0.0;
        Double resultValue = countResultValueForSamples(samples);
        for (Sample sample : samples) {
            Double error = resultValue - sample.getResult();
            mse += error * error;
        }
        return mse;
    }

    private Double countResultValueForSamples(List<Sample> samples) {
        return samples.stream().mapToDouble(Sample::getResult).average().orElse(0.0);
    }
}
