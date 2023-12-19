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

    public void Calculate() {
        printCurrentTable();
        while (this.table.size() != 1 && this.demand.size() != 1) {
            this.calculatePenalitySupply();
            this.calculatePenalityDemand();
            ArrayList<Integer> path = this.choosePath();
            this.updateTable(path.get(0), path.get(1));
        }

        if (this.table.size() == 1) {
            ArrayList<Double> lastLine = this.table.get(0);
            for (int count = 0; count < lastLine.size(); count++) {
                this.response.add("(" + this.demand.get(count) + " units) " + this.originsList.get(0) + " -> " + this.destinationList.get(count));
            }
        }
        if (this.demand.size() == 1) {
            ArrayList<Double> lastColumn = this.getColumnByIndex(0);
            for (int count = 0; count < lastColumn.size(); count++) {
                this.response.add("(" + this.supply.get(count) + " units) " + this.originsList.get(count) + " -> " + this.destinationList.get(0));
            }
        }

//        printCurrentTable();
//        this.calculatePenalitySupply();
//        this.calculatePenalityDemand();
//        ArrayList<Integer> path = this.choosePath();
//        this.updateTable(path.get(0), path.get(1));


    }

    /* Algorithm implementations */
    public void calculatePenalitySupply() {
        this.penalitySupply = new ArrayList<>();
        for (int count = 0; count < this.table.size(); count++) {
            Double firstBetter = Algorithm.getFirstBetter(this.table.get(count));
            Double secondBetter = Algorithm.getSecondBetter(this.table.get(count));
            this.penalitySupply.add(secondBetter - firstBetter);
        }
    }

    public void calculatePenalityDemand() {
        this.penalityDemand = new ArrayList<>();
        for (int countColumns = 0; countColumns < table.get(0).size(); countColumns++) {
            ArrayList<Double> column = this.getColumnByIndex(countColumns);
            Double firstBetter = Algorithm.getFirstBetter(column);
            Double secondBetter = Algorithm.getSecondBetter(column);
            this.penalityDemand.add(secondBetter - firstBetter);
        }
    }

    public void updateOriginsList() {
        this.originsList = new ArrayList<>();
        for (int count = 0; count < this.supply.size(); count++) {
            this.originsList.add("S" + (count + 1));
        }
    }

    public void updateDestinationList() {
        this.destinationList = new ArrayList<>();
        for (int count = 0; count < this.demand.size(); count++) {
            this.destinationList.add("D" + (count + 1));
        }
    }

    public ArrayList<Integer> choosePath() {
        int cY = 0;
        int cX = 0;
        Double higherSupplyPenalityValue = Collections.max(this.penalitySupply);
        int indexHighestPenaltyOffer = this.penalitySupply.indexOf(higherSupplyPenalityValue);

        Double higherDemandPenalityValue = Collections.max(this.penalityDemand);
        int indexHighestPenaltyDemand = this.penalityDemand.indexOf(higherDemandPenalityValue);

        if (higherSupplyPenalityValue > higherDemandPenalityValue) {
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

        Double demandLocal = this.demand.get(cX);
        Double supplyLocal = this.supply.get(cY);

        Double quantityTransported = (supplyLocal - demandLocal) > 0 ? demandLocal : supplyLocal;

        Double updatedDemand = (demandLocal - quantityTransported);
        Double updatedSupply = (supplyLocal - quantityTransported);

        this.demand.set(cX, updatedDemand);
        this.supply.set(cY, updatedSupply);

        this.response.add("(" + quantityTransported + " units) " + this.originsList.get(cY) + " -> " + this.destinationList.get(cX));

        if (updatedDemand == 0) {
            this.removeColumnByIndex(cX);
            this.destinationList.remove(cX);
        }

        if (updatedSupply == 0) {
            this.removeLineByIndex(cY);
            this.originsList.remove(cY);
        }

    }

    /**
     * Atualiza a lista de destinos e remove a coluna que não há mais demanda
     */
    public void removeColumnByIndex(Integer columnIndex) {
        Double demandElementRemove = this.demand.get(columnIndex);
        this.demand.remove(demandElementRemove);

        String destinationRemove = this.destinationList.get(columnIndex);
        this.destinationList.remove(destinationRemove);

        for (int countY = 0; countY < this.table.size(); countY++) {
            ArrayList<Double> tmp = this.table.get(countY);
            Double element = tmp.get(columnIndex);
            tmp.remove(element);
            this.table.set(countY, tmp);
        }
    }

    /**
     * Atualiza a lista de origens e remove a linha que não há mais fabricação
     */
    public void removeLineByIndex(Integer lineIndex) {
        Double elementSupplyRemove = this.supply.get(lineIndex);
        this.supply.remove(elementSupplyRemove);

        String originRemove = this.originsList.get(lineIndex);
        this.originsList.remove(originRemove);

        ArrayList<Double> lineRemove = this.table.get(lineIndex);
        this.table.remove(lineRemove);
    }


    /**
     * Returns the smallest value in the list
     *
     * @param list It is the list or column of the table
     * @return The smallest value in the list.
     */
    public static Double getFirstBetter(ArrayList<Double> list) {
        return Collections.min(list);
    }

    /**
     * Returns the second smallest value in the list
     *
     * @param list It is the list or column of the table
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
        for (int count = 0; count < this.table.size(); count++) {
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

    public void printCurrentTable() {
        String print = "";
        for (int countY = 0; countY < this.table.size(); countY++) {
            print = print.concat(this.originsList.get(countY) + " | ");
            ArrayList<Double> line = this.table.get(countY);
            for (int countX = 0; countX < line.size(); countX++) {
                print = print.concat(line.get(countX) + " | ");
            }
            print = print.concat(this.supply.get(countY) + " | \n");
        }

        print = print.concat("De>| ");
        for (int countX = 0; countX < this.demand.size(); countX++) {
            print = print.concat(this.demand.get(countX) + " | ");
        }
        print = print.concat("Su^ | \n    | ");

        for (int countX = 0; countX < this.destinationList.size(); countX++) {
            print = print.concat(this.destinationList.get(countX) + " | ");
        }
        System.out.println(print);

//        System.out.println("Penalidades oferta:"+this.penalitySupply);
//        System.out.println("Penalidades demanda:"+this.penalityDemand);
    }

    public void printResponses() {
        for (String response : this.response) {
            System.out.println(response);
        }
    }
}
