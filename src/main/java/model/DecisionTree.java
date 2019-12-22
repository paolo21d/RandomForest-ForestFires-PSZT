package model;

import com.sun.tools.javac.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecisionTree {

    Node root;
    Long maxDepth = 29L;


    public void buildTree(List<Argument.ArgumentType> argumentTypes, List<Sample> samples) {
        root = ID3(argumentTypes, samples, null, 0L);
    }

    private Node ID3(List<Argument.ArgumentType> argumentTypes, List<Sample> samples, Node parent, Long depth) {
        //ilosc probek na tyle mala ze nie warto dzielić, przeuczenie
        //głębokość duża
        if (samples.size() <= 3 || depth >= maxDepth) {
            Node leaf = new Node();
            leaf.setParent(parent);
            leaf.setSamples(samples);
            leaf.countValue();
            return leaf;
        }

        //znalezienie argumentu podzialu
        Argument divisionArgument = findDivisionArgument(argumentTypes, samples);
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
            if (triple.getMiddle() < minCF) {
                divisionArgument = triple.getLeft();
                minCF = triple.getMiddle();
                divisionPoint = triple.getRight();
            }
        }

        return new Argument(divisionArgument, divisionPoint);
    }

    private Double findDivision(Argument.ArgumentType argumentType, List<Sample> samples) {
        List<Pair<Sample, Argument>> sampleArgumentPairs = new ArrayList<>();
        //prepare division points by adding every value of argument into hash set (it sorts list and remove duplicates)
        Set<Double> divisionPoints = new HashSet<>();
        for (Sample sample : samples) {
            Argument arg = sample.getArgumentByType(argumentType);
            sampleArgumentPairs.add(new Pair<>(sample, arg));
            Double value = arg.getValue();
            divisionPoints.add(value);
        }

        List<Sample> left = new ArrayList<>();
        List<Sample> right = new ArrayList<>();

        Pair<Double, Double> divisionWithMinCF = new Pair<>(0.0, Double.MAX_VALUE); //division point, cost function for this division

        for (Double divisionPoint : divisionPoints) {
            left.clear();
            right.clear();

            for (Pair<Sample, Argument> sampleArgumentPair : sampleArgumentPairs) {
                if (sampleArgumentPair.snd.getValue() < divisionPoint) {
                    left.add(sampleArgumentPair.fst);
                } else {
                    right.add(sampleArgumentPair.fst);
                }
            }

            Double costFunction = countCF(left, right);
            if (costFunction < divisionWithMinCF.snd) {
                divisionWithMinCF = new Pair<>(divisionPoint, costFunction);
            }
        }

        return divisionWithMinCF.fst;
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
        int rightTreeSize = leftTree.size();
        int allTreeSize = leftTreeSize + rightTreeSize;
        Double left = countMSE(leftTree) * (double) (leftTreeSize / allTreeSize);
        Double right = countMSE(rightTree) * (double) (rightTreeSize / allTreeSize);
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
