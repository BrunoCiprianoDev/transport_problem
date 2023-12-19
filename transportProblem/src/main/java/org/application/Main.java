package org.application;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
        mainTable.add(new ArrayList<>(Arrays.asList(1D, 0D, 6D, 1D)));
        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));

        ArrayList<Double> supply = new ArrayList<>(Arrays.asList(6D, 1D, 10D));
        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 2D));

        Algorithm algorithm = new Algorithm(mainTable, supply, demand);

//        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
//        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
//        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));
//
//        ArrayList<Double> supply = new ArrayList<>(Arrays.asList(6D, 10D));
//        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 1D));
//
//        Algorithm algorithm = new Algorithm(mainTable, supply, demand);

        algorithm.Calculate();

        algorithm.printResponses();

    }
}