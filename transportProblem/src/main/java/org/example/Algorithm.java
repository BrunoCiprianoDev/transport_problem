package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Algorithm {

    private ArrayList<ArrayList<Double>> table;
    private ArrayList<Double> production;
    private ArrayList<Double> demand;
    private ArrayList<String> response;
    private ArrayList<Double> penalityProduction;
    private ArrayList<Double> penalityDemand;

    public Algorithm(ArrayList<ArrayList<Double>> table, ArrayList<Double> production, ArrayList<Double> demand) {
        this.response = new ArrayList<>();
        this.table = table;
        this.production = production;
        this.demand = demand;
    }

    public ArrayList<Integer> choosePath() {
        int cY = 0;
        int cX = 0;
        Double higherOfferPenalityValue = Collections.max(this.penalityProduction);
        int indexHighestPenaltyOffer = this.penalityProduction.indexOf(higherOfferPenalityValue);

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

    public void calculatePenalityProduction() {
        this.penalityProduction = new ArrayList<>();
        for(int count = 0; count < table.size(); count ++) {
            if(this.production.get(count) != 0) { // Deve desconsiderar a linha cuja produção já foi zerada
                Double firstBetter = Algorithm.getFirstBetter(table.get(count));
                Double secondBetter = Algorithm.getSecondBetter(table.get(count));
                this.penalityProduction.add(secondBetter - firstBetter);
            } else {
                this.penalityProduction.add(0D);
            }
        }
    }

    public void calculatePenalityDemand() {
        this.penalityDemand = new ArrayList<>();
        for(int countColumns = 0; countColumns < table.get(0).size(); countColumns ++) {
            ArrayList<Double> column = this.getColumnByIndex(countColumns);

            for(int i= 0; i< this.production.size(); i++) { // Deve desconsiderar a linha cuja produção já foi zerada
                if(this.production.get(i) == 0) {
                    column.remove(i);
                }
            }

            Double firstBetter = Algorithm.getFirstBetter(column);
            Double secondBetter = Algorithm.getSecondBetter(column);
            this.penalityDemand.add(secondBetter - firstBetter);
        }
    }

    public static Double getFirstBetter(ArrayList<Double> list) {
        return Collections.min(list);
    }

    public static Double getSecondBetter(ArrayList<Double> list) {
        if(list.size() < 2) {
            throw new RuntimeException("Error GetSecondBetter (list.size < 2).");
        }
        ArrayList<Double> listCopy = new ArrayList<>();
        listCopy.addAll(list);
        Double firstBiggest = Collections.min(listCopy);
        listCopy.remove(firstBiggest);
        return Collections.min(listCopy);
    }


    public ArrayList<Double> getColumnByIndex(int columnIndex) {
        ArrayList<Double> column = new ArrayList<>();
        for(int count = 0; count < this.table.size(); count++) {
            ArrayList<Double> line = table.get(count);
            column.add(line.get(columnIndex));
        }
        return column;
    }

    public ArrayList<Double> getLineByIndex(int lineIndex) {
        return this.table.get(lineIndex);
    }



    /* ----------------------  GETTERS ---------------------------------------*/

    public ArrayList<ArrayList<Double>> getTable() {
        return table;
    }

    public ArrayList<Double> getProduction() {
        return production;
    }

    public ArrayList<Double> getDemand() {
        return demand;
    }

    public ArrayList<String> getResponse() {
        return response;
    }

    public ArrayList<Double> getPenalityProduction() {
        return penalityProduction;
    }

    public ArrayList<Double> getPenalityDemand() {
        return penalityDemand;
    }
}

