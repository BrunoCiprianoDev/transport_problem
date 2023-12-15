package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmTest {

    @Test
    public void getColumnByIndexTest() {
        Algorithm algorithm = this.createInstance();
        ArrayList<Double> sut = algorithm.getColumnByIndex(1);
        assertEquals(Arrays.asList(3D, 0D, 8D), sut);
    }

    @Test
    public void getLineByIndexTest() {
        Algorithm algorithm = this.createInstance();
        ArrayList<Double> sut = algorithm.getLineByIndex(0);
        assertEquals(Arrays.asList(2D, 3D, 11D, 7D), sut);
    }


    @Test
    public void calculatePenalityProductionTest() {
        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
        mainTable.add(new ArrayList<>(Arrays.asList(0D, 0D, 0D, 1D)));
        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));

        ArrayList<Double> production = new ArrayList<>(Arrays.asList(6D, 0D, 10D));
        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 1D));

        Algorithm algorithm = new Algorithm(mainTable, production, demand);
        algorithm.calculatePenalityProduction();

        assertEquals(Arrays.asList(1D, 0D, 3D), algorithm.getPenalityProduction());

    }

    @Test
    public void getFirstBetterTest() {
        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
        mainTable.add(new ArrayList<>(Arrays.asList(0D, 0D, 0D, 1D)));
        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));

        ArrayList<Double> production = new ArrayList<>(Arrays.asList(6D, 0D, 10D));
        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 1D));

        Algorithm algorithm = new Algorithm(mainTable, production, demand);
        algorithm.calculatePenalityProduction();
        algorithm.calculatePenalityDemand();

        assertEquals(Arrays.asList(3D, 5D, 4D, 2D), algorithm.getPenalityDemand());
    }


    private Algorithm createInstance() {
        ArrayList<ArrayList<Double>> mainTable = new ArrayList<>();
        mainTable.add(new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D)));
        mainTable.add(new ArrayList<>(Arrays.asList(1D, 0D, 6D, 1D)));
        mainTable.add(new ArrayList<>(Arrays.asList(5D, 8D, 15D, 9D)));

        ArrayList<Double> offer = new ArrayList<>(Arrays.asList(6D, 1D, 10D));
        ArrayList<Double> demand = new ArrayList<>(Arrays.asList(7D, 5D, 3D, 2D));

        return new Algorithm(mainTable, offer, demand);
    }

}
