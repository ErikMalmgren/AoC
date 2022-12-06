import java.io.*;
import java.util.*;

public class DaySixSolution {
    public static void main(String[] args) throws IOException {
        long elapsedTimeSum = 0;
        for (int i = 0; i < 1000; i++){
            long startTime = System.nanoTime();
            partOne();
            partTwo();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/1000000);
    }

    private static void partOne() throws IOException {
        var input = new File("2022/inputs/day6.txt");
        var br = new BufferedReader(new FileReader(input));
        int counter = 0;
        int read;
        List<Integer> listInts = new LinkedList<>();

        for(int i = 0; i < 4; i++) {
            listInts.add(br.read());
        }

        while((read = br.read()) != -1 ) {
            
            
            listInts.add(read);
            if(hasDupes(listInts)) {
                listInts.remove(0);
                counter++;
            } else {
                break;
            }
        }
        counter += 4;
        System.out.println("Part two: " + counter);
    }

    private static void partTwo() throws IOException {
        var input = new File("2022/inputs/day6.txt");
        var br = new BufferedReader(new FileReader(input));
        int counter = 0;
        int read;
        List<Integer> listInts = new LinkedList<>();

        for(int i = 0; i < 13; i++) {
            listInts.add(br.read());
        }

        while((read = br.read()) != -1 ) {
            listInts.add(read);
            if(hasDupes(listInts)) {
                listInts.remove(0);
                counter++;
            } else {
                break;
            }
            
        }
        counter += 14;
        System.out.println("Part two: " + counter);
    }


    private static <T> boolean hasDupes(Iterable<T> all) {
        Set<T> set = new HashSet<>();
        for(T each : all) {
            if(!set.add(each)){
                return true;
            }
        }
        return false;
    }
}
