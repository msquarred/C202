/**
 * CS-202 tour.java Purpose: Traveling salesman problem using java api stack
 *
 * @author Mason Mullins
 * @version 1.0 12/02/16
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Stack;

public class Tour {

    int CITI;
    int[][] adjacency;
    long bestCost;
    ArrayList<Integer> bestPath;
    
    public Tour(int n) {
        CITI = n;
        adjacency = new int[n][n];
        bestCost = Integer.MAX_VALUE;
    }

    //populate the distance matrix
    public void populateMatrix(String f) {
        int value, i, j;
        try {
            Scanner input = new Scanner(new File(f));
            for (i = 0; i < CITI && input.hasNext(); i++) { //CITI is a constant  
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;
                    } else {
                        value = input.nextInt();
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e.toString());
            System.out.println("Cannot read file: " + f);
        }
    }

    public void recDFS(ArrayList<Integer> remainingCities) {
        Stack<Integer> pathStack = new Stack<>();
        boolean[] visitedCities = new boolean[remainingCities.size()];
        ArrayList<Integer> path = new ArrayList<>();
        long cost = 0;
        visitedCities[0] = true;
        pathStack.push(remainingCities.get(0));
        int closestCity = 0;
        boolean minFlag = false;
        System.out.println("Start city: " + remainingCities.get(0));
        while (!pathStack.isEmpty()) {
            int currentCity = pathStack.peek();
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < remainingCities.size(); i++) {
                if (adjacency[currentCity][i] != 0 && visitedCities[i] == false) {
                    if (adjacency[currentCity][i] < min) {
                        min = adjacency[currentCity][i];
                        closestCity = i;
                        minFlag = true;
                        cost += adjacency[currentCity][i];
                    }
                }
            }
            if (minFlag) {
                visitedCities[closestCity] = true;
                pathStack.push(closestCity);
                System.out.println("Closest City: " + remainingCities.get(closestCity));
                path.add(remainingCities.get(closestCity));
                minFlag = false;
                continue;
            }
            pathStack.pop();
        }
        System.out.println("Path: " + path.toString());
        System.out.println("Cost: " + cost);
    }
}
