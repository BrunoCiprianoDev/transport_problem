package org.application;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
//        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
//        mainTable.add(new ArrayList<>(Arrays.asList(1D, 0D, 6D, 1D)));
//        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));
//
//        ArrayList<Double> supply = new ArrayList<>(Arrays.asList(6D, 1D, 10D));
//        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 2D));
//
//        Algorithm algorithm = new Algorithm(mainTable, supply, demand);

        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
        mainTable.add(new ArrayList<>(Arrays.asList(40D, 40.5D, 41D, 41.5D)));
        mainTable.add(new ArrayList<>(Arrays.asList(42D, 40D, 40.5D, 41.5D)));
        mainTable.add(new ArrayList<>(Arrays.asList(44D, 42D, 40.0D, 40.5D)));
        mainTable.add(new ArrayList<>(Arrays.asList(46D, 44D, 42D, 40D)));

        ArrayList<Double> supply = new ArrayList<>(Arrays.asList(50D, 180D, 280D, 270D));
        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(100D, 200D, 180D, 30D));

        Algorithm algorithm = new Algorithm(mainTable, supply, demand);

        algorithm.Calculate();


        algorithm.printCurrentTable();

        algorithm.printResponses();

    }
}