package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    //Scanner är slow af scanner runtime 100 ms
    //BufferedReader runtime 10 ms
   
    private static int partTwo(){
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {3, 4, 8, 1, 5, 9, 2, 6, 7};
        Map<String, Integer> keyValues = combValues(keyStrings, values);
        return calcPoints(keyValues);
    }



    private static int partOne(){
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {4, 8, 3, 1, 5, 9, 7, 2, 6};
        Map<String, Integer> keyValues = combValues(keyStrings, values);
        return calcPoints(keyValues);

    }

    private static int calcPoints(Map<String, Integer> map){
        int totalScore = 0;
        BufferedReader br = null;

        try{
            String currentLine;
            
            br = new BufferedReader(new FileReader("/home/em/repos/AoC/Day2/input.txt"));

            while((currentLine = br.readLine()) != null){
                totalScore += map.get(currentLine);
            }

        } catch (IOException e){
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
