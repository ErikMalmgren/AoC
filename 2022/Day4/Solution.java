import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        //partOne();
        //partTwo();
        long elapsedTimeSum = 0;
        for(int i = 0; i < 1000; i++){
            long startTime = System.nanoTime();
            partOneTwo();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/1000000);
    }

    public static void partOneTwo() throws IOException {
        int res1 = 0;
        int res2 = 0;
        var input = new File("2022/inputs/day4.txt");
        var br  = new BufferedReader(new FileReader(input));
        
        String currentLine;
        String[] ranges;
        String[] rangeOne;
        String [] rangeTwo;

        int rangeOneStart;
        int rangeOneFinish;
        int rangeTwoStart;
        int rangeTwoFinish;


        while((currentLine = br.readLine())!= null){
            ranges = currentLine.split(",");
            rangeOne = ranges[0].split("-");
            rangeTwo = ranges[1].split("-");

            rangeOneStart = Integer.parseInt(rangeOne[0]);
            rangeOneFinish = Integer.parseInt(rangeOne[1]);
            rangeTwoStart = Integer.parseInt(rangeTwo[0]);
            rangeTwoFinish = Integer.parseInt(rangeTwo[1]);

            //Part one
            if(rangeOneStart <= rangeTwoStart && rangeOneFinish >= rangeTwoFinish || rangeTwoStart <= rangeOneStart && rangeTwoFinish >= rangeOneFinish) {
                res1++;
            }
            //Part two
            if(rangeOneStart <= rangeTwoFinish && rangeTwoStart <= rangeOneFinish) {
                res2++;
            }
        }
        //System.out.println("Part one: " + res1);
        //System.out.println("Part two: " + res2);
    }


    public static void partOne() throws IOException {
        int res = 0;
        var input = new File("2022/inputs/day4.txt");
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

            //Part one
            if(rangeOneStart <= rangeTwoStart && rangeOneFinish >= rangeTwoFinish) {
                res += 1;
            } else if(rangeTwoStart <= rangeOneStart && rangeTwoFinish >= rangeOneFinish) {
                res += 1;
        }
    

        System.out.println("Part one: " + res);
        }
    }

    public static void partTwo() throws IOException {
        int res = 0;
        var input = new File("2022/inputs/day4.txt");
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
