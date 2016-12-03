/*
 * Mason Mullins
 * Programming Assignment 6
 * TSP Problem Using Java API Stacks
 * December 2, 2016
 */

import java.util.ArrayList;

public class ProgrammingAssignment6 {

    public static void main(String[] args) {
        int citi_num = Integer.parseInt(args[0]);
        //int citi_num = Integer.parseInt("12");
        String file = args[1];
        //String file = "tsp12.txt";
        Tour t = new Tour(citi_num);
        t.populateMatrix(file);
        ArrayList<Integer> partial = new ArrayList<>();
        ArrayList<Integer> remainingCities = new ArrayList<>();
        partial.add(0);
        for (int i = 1; i < citi_num + 1; i++) { //for
            remainingCities.add(i);
        } //for
        //partialTour, remainingCities
        long start_time = System.nanoTime();
        t.recDFS(remainingCities);
        long diff_time = System.nanoTime() - start_time;
        System.out.println("Operation has been running for: " + diff_time + " nanoseconds.");
    }

}
