import java.io.*;
import java.util.*;

public class Day10Solution {
    private static Map<Integer, Integer> clockCycles; //Första int är klockcykel och andra int är x-värdet
    
    public static void main(String[] args) throws IOException{
        long elapsedTimeSum = 0;
        for (int i = 0; i < 1000; i++){
            long startTime = System.nanoTime();
            calculate();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/1000000);
    }

    private static void calculate() throws IOException{
        readFile();
        sumSignalStrength();
        drawCRT();
    }

    private static void readFile() throws IOException {
        var input = new File("2022/inputs/day10.txt");
        var reader = new BufferedReader(new FileReader(input));

        clockCycles = new HashMap<>();
        int x = 1;
        int cycle = 1;
        

        String line;

        while((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");

            if(parts[0].equals("noop")) {
                clockCycles.put(cycle, x);
                cycle++;
                continue;
            } else {
                clockCycles.put(cycle++, x);
                clockCycles.put(cycle++, x);
                x += Integer.parseInt(parts[1]);
                clockCycles.put(cycle, x);
            }


        }
    }

    private static void sumSignalStrength() {
        int sum = 0;
        for (int i = 20; i < 221; i += 40) {
            sum += clockCycles.get(i) * i;
        }
        System.out.println("Part one: " + sum);
    }

    private static void drawCRT() {
        System.out.println("Part two: ");
        var sb = new StringBuilder();
        int pixel = 0;
        char sign;
        for(int i = 1; i < clockCycles.size(); i++) {
            int x = clockCycles.get(i);
            if(pixel == x-1 || pixel == x || pixel == x+1) {
                sign = '#';
            } else {
                sign = '.';
            }
            sb.append(sign);
            pixel++;

            if(pixel % 40 == 0) {
                sb.append('\n');
                pixel = 0;
            }
        }
        System.out.println(sb.toString());
    }
}
