package org.application;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AlgorithmTest {


    @Test
    public void calculatePenalitySupplyTest() {
        Algorithm algorithm = this.createInstance();
        algorithm.calculatePenalitySupply();
        ArrayList<Double> sut = algorithm.getPenalitySupply();

        assertEquals(Arrays.asList(1D, 1D, 3D), sut);

    }

    @Test
    public void calculatePenalityDemand() {
        Algorithm algorithm = this.createInstance();
        algorithm.calculatePenalityDemand();
        ArrayList<Double> sut = algorithm.getPenalityDemand();

        assertEquals(Arrays.asList(1D, 3D, 5D, 6D), sut);
    }


    @Test
    public void updateOriginsListTest() {
        Algorithm algorithm = this.createInstance();
        ArrayList<String> sut = algorithm.getOriginsList();

        assertEquals(Arrays.asList("S1", "S2", "S3"), sut);
    }

    @Test
    public void updateDestinationListTest() {
        Algorithm algorithm = this.createInstance();
        ArrayList<String> sut = algorithm.getDestinationList();

        assertEquals(Arrays.asList("D1", "D2", "D3", "D4"), sut);
    }

    @Test
    public void chosePathTest() {
        Algorithm algorithm = this.createInstance();
        algorithm.calculatePenalityDemand();
        algorithm.calculatePenalitySupply();
        ArrayList<Integer> sut = algorithm.choosePath();

        assertEquals(Arrays.asList(3,1), sut);
    }

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
    public void getFirstBetterTest() {
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(1D, 2D, 3D));
        Double sut = Algorithm.getFirstBetter(list);
        assertEquals(1D, sut);
        assertEquals(Arrays.asList(1D, 2D, 3D), list);
    }


    @Test
    public void getSecondBetterTest() {
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(2D, 3D, 11D, 7D));
        Double sut = Algorithm.getSecondBetter(list);
        assertEquals(3D, sut);
        assertEquals( Arrays.asList(2D, 3D, 11D, 7D), list);
    }

    @Test
    public void removeColumnByIndexTest() {
        Algorithm algorithm = this.createInstance();
        algorithm.removeColumnByIndex(1);


        assertEquals(Arrays.asList(
                Arrays.asList(2D, 11D, 7D),
                Arrays.asList(1D, 6D, 1D),
                Arrays.asList(5D, 15D, 9D)), algorithm.getTable());

    }


    @Test
    public void removeLineByIndexTest() {
        Algorithm algorithm = this.createInstance();
        algorithm.removeLineByIndex(1);

        assertEquals(Arrays.asList(
                Arrays.asList(2D, 11D, 7D),
                Arrays.asList(5D, 15D, 9D)), algorithm.getTable());

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
