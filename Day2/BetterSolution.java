package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BetterSolution {
    public static void main(String[] args) throws IOException {
        long time1 = System.nanoTime();
        for(int i = 0; i < 1000; i++) {
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
        }
        long time2 = System.nanoTime();
        System.out.println("Runtime in ms: " + (time2 - time1)/1000000);
    }
    //Scanner är slow af scanner runtime 100 ms
    //BufferedReader runtime 10 ms
   
    private static int partTwo() throws IOException{
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {3, 4, 8, 1, 5, 9, 2, 6, 7};
        Map<String, Integer> keyValues = combValues(keyStrings, values);
        return calcPoints(keyValues);
    }

    private static int partOne() throws IOException{
        String[] keyStrings = {"A X", "A Y", "A Z", "B X", "B Y", "B Z", "C X", "C Y", "C Z"};
        int[] values = {4, 8, 3, 1, 5, 9, 7, 2, 6};
        Map<String, Integer> keyValues = combValues(keyStrings, values);
        return calcPoints(keyValues);
    }

    private static int calcPoints(Map<String, Integer> map) throws IOException{
        int totalScore = 0;
        BufferedReader br = new BufferedReader(new FileReader("inputs/day2.txt"));
        String currentLine;

        while((currentLine = br.readLine()) != null){
            totalScore += map.get(currentLine);
        }
        
        return totalScore;
    }

    private static Map<String, Integer> combValues(String[] keyStrings, int[] values){

        Map<String, Integer> keyValues = new HashMap<>();
        for(int i = 0; i < 9; i++){
            keyValues.put(keyStrings[i], values[i]);
        }
        return keyValues;
    }
}
