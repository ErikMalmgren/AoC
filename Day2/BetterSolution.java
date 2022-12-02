package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BetterSolution {
    public static void main(String[] args) {
        long time1 = System.nanoTime();
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
        long time2 = System.nanoTime();
        System.out.println("Runtime in ms: " + (time2 - time1)/1000000);
    }

    //About same runtime as the bad solution :)
   
    private static int partTwo(){
        int totalScore = 0;
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {3, 4, 8, 1, 5, 9, 2, 6, 7};
        Map<String, Integer> keyValues = combValues(keyStrings, values);

        try {
            Scanner scan = new Scanner(new File("/home/em/repos/AoC/Day2/input.txt"));

            while(scan.hasNextLine()){
                String round = scan.nextLine();

                totalScore += keyValues.get(round);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return totalScore;
    }



    private static int partOne(){
        int totalScore = 0;
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {4, 8, 3, 1, 5, 9, 7, 2, 6};
        Map<String, Integer> keyValues = combValues(keyStrings, values);

        try {
            Scanner scan = new Scanner(new File("/home/em/repos/AoC/Day2/input.txt"));


            while(scan.hasNextLine()){
                String round = scan.nextLine();
                totalScore += keyValues.get(round);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return totalScore;
    }

    private static Map combValues(String[] keyStrings, int[] values){

        Map<String, Integer> keyValues = new HashMap<>();
        for(int i = 0; i < 9; i++){
            keyValues.put(keyStrings[i], values[i]);
        }
        
        return keyValues;
    }
}
