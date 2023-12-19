package org.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Algorithm {

    private ArrayList<ArrayList<Double>> table;
    private ArrayList<Double> supply;
    private ArrayList<String> originsList;
    private ArrayList<Double> penalitySupply;
    private ArrayList<Double> demand;
    private ArrayList<Double> penalityDemand;
    private ArrayList<String> destinationList;
    private ArrayList<String> response;

    public Algorithm(ArrayList<ArrayList<Double>> table, ArrayList<Double> supply, ArrayList<Double> demand) {
        this.response = new ArrayList<>();
        this.table = new ArrayList<>(table);
        this.supply = new ArrayList<>(supply);
        this.updateOriginsList();
        this.demand = new ArrayList<>(demand);
        this.updateDestinationList();
    }

    /* Algorithm implementations */
    public void calculatePenalitySupply() {
        this.penalitySupply = new ArrayList<>();
        for(int count = 0; count < this.table.size(); count ++) {
            Double firstBetter = Algorithm.getFirstBetter(this.table.get(count));
            Double secondBetter = Algorithm.getSecondBetter(this.table.get(count));
            this.penalitySupply.add(secondBetter - firstBetter);
        }
    }

    public void calculatePenalityDemand() {
        this.penalityDemand = new ArrayList<>();
        for(int countColumns = 0; countColumns < table.get(0).size(); countColumns ++) {
            ArrayList<Double> column = this.getColumnByIndex(countColumns);
            Double firstBetter = Algorithm.getFirstBetter(column);
            Double secondBetter = Algorithm.getSecondBetter(column);
            this.penalityDemand.add(secondBetter - firstBetter);
        }
    }

    public void updateOriginsList() {
        this.originsList = new ArrayList<>();
        for(int count= 0; count< this.supply.size(); count++){
            this.originsList.add("S"+(count+1));
        }
    }

    public void updateDestinationList() {
        this.destinationList = new ArrayList<>();
        for(int count= 0; count< this.demand.size(); count++){
            this.destinationList.add("D"+(count+1));
        }
    }

    public ArrayList<Integer> choosePath() {
        int cY = 0;
        int cX = 0;
        Double higherOfferPenalityValue = Collections.max(this.penalitySupply);
        int indexHighestPenaltyOffer = this.penalitySupply.indexOf(higherOfferPenalityValue);

        Double higherDemandPenalityValue = Collections.max(this.penalityDemand);
        int indexHighestPenaltyDemand = this.penalityDemand.indexOf(higherDemandPenalityValue);

        if(higherOfferPenalityValue > higherDemandPenalityValue) {
            cY = indexHighestPenaltyOffer;
            Double min = Collections.min(table.get(cY));
            cX = this.table.get(cY).indexOf(min);
        } else {
            cX = indexHighestPenaltyDemand;
            ArrayList<Double> column = this.getColumnByIndex(cX);
            Double min = Collections.min(column);
            cY = column.indexOf(min);
        }
        return new ArrayList<>(Arrays.asList(cX, cY));
    }

    public void updateTable(Integer cX, Integer cY) {

    }

    /**
     * Returns the smallest value in the list
     * @param list  It is the list or column of the table
     * @return The smallest value in the list.
     */
    public static Double getFirstBetter(ArrayList<Double> list) {
        return Collections.min(list);
    }

    /**
     * Returns the second smallest value in the list
     * @param list  It is the list or column of the table
     * @return The second smallest value in the list
     */
    public static Double getSecondBetter(ArrayList<Double> list) {
        ArrayList<Double> listCopy = new ArrayList<>(list);
        Double firstBiggest = Collections.min(listCopy);
        listCopy.remove(firstBiggest);
        return Collections.min(listCopy);
    }

    /* Access methods only */
    public ArrayList<Double> getColumnByIndex(Integer columnIndex) {
        ArrayList<Double> column = new ArrayList<>();
        for(int count = 0; count < this.table.size(); count++) {
            ArrayList<Double> line = table.get(count);
            column.add(line.get(columnIndex));
        }
        return column;
    }

    public ArrayList<Double> getLineByIndex(Integer lineIndex) {
        return this.table.get(lineIndex);
    }

    public ArrayList<ArrayList<Double>> getTable() {
        return new ArrayList<>(this.table);
    }

    public ArrayList<Double> getSupply() {
        return new ArrayList<>(this.supply);
    }

    public ArrayList<Double> getPenalitySupply() {
        return new ArrayList<>(this.penalitySupply);
    }

    public ArrayList<Double> getDemand() {
        return new ArrayList<>(this.demand);
    }

    public ArrayList<Double> getPenalityDemand() {
        return new ArrayList<>(this.penalityDemand);
    }

    public ArrayList<String> getResponse() {
        return new ArrayList<>(this.response);
    }

    public ArrayList<String> getOriginsList() {
        return new ArrayList<>(this.originsList);
    }

    public ArrayList<String> getDestinationList() {
        return new ArrayList<>(this.destinationList);
    }
}