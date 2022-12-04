package Day4;

import java.io.*;
import java.util.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        partOne();
        partTwo();
    }


    public static void partOne() throws IOException {
        int res = 0;
        var input = new File("inputs/day4.txt");
        var br  = new BufferedReader(new FileReader(input));
        String currentLine;

        while((currentLine = br.readLine())!= null){
            String[] ranges = currentLine.split(",");
            String[] rangeOne = ranges[0].split("-");
            String[] rangeTwo = ranges[1].split("-");
            int rangeOneStart = Integer.valueOf(rangeOne[0]);
            int rangeOneFinish = Integer.valueOf(rangeOne[1]);
            int rangeTwoStart = Integer.valueOf(rangeTwo[0]);
            int rangeTwoFinish = Integer.valueOf(rangeTwo[1]);

            if(rangeOneStart <= rangeTwoStart && rangeOneFinish >= rangeTwoFinish) {
                res += 1;
            } else if(rangeTwoStart <= rangeOneStart && rangeTwoFinish >= rangeOneFinish) {
                res += 1;
            }
            
        }


        System.out.println("Part one: " + res);
    }

    public static void partTwo() throws IOException {
        int res = 0;
        var input = new File("inputs/day4.txt");
        var br  = new BufferedReader(new FileReader(input));
        String currentLine;

        while((currentLine = br.readLine())!= null){
            String[] ranges = currentLine.split(",");
            String[] rangeOne = ranges[0].split("-");
            String[] rangeTwo = ranges[1].split("-");
            int rangeOneStart = Integer.valueOf(rangeOne[0]);
            int rangeOneFinish = Integer.valueOf(rangeOne[1]);
            int rangeTwoStart = Integer.valueOf(rangeTwo[0]);
            int rangeTwoFinish = Integer.valueOf(rangeTwo[1]);

            if(rangeOneStart >= rangeTwoStart && rangeOneStart < rangeTwoFinish) {
                res += 1;
            } else if(rangeOneFinish >= rangeTwoStart && rangeOneFinish <= rangeTwoFinish) {
                res += 1;
            } else if(rangeTwoStart >= rangeOneStart && rangeTwoStart <= rangeOneFinish) {
                res += 1;
            } else if(rangeTwoFinish >= rangeOneStart && rangeTwoFinish <= rangeOneFinish) {
                res += 1;
            }
            
        }


        System.out.println("Part two: " + res);
    }
    
}
