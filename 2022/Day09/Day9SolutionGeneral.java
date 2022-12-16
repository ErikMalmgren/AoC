import java.io.*;
import java.util.*;

public class Day9SolutionGeneral {
    public static void main(String[] args) throws IOException {
        long elapsedTimeSum = 0;
        for (int i = 0; i < 1000; i++){
            long startTime = System.nanoTime();
            calculate();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/1000000);
    }

    private static void calculate() throws IOException {
        var input = new File("2022/inputs/day09.txt");
        var reader = new BufferedReader(new FileReader(input));
        String line;
        var knotOneVisited = new HashSet<>();
        var knotTenVisited = new HashSet<>();

        var knots = new TwoDPoint[10];

        for(int i = 0; i < knots.length; i++) {
            knots[i] = new TwoDPoint(0, 0);
        }

        var head = knots[0];

        
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String direction = parts[0];
            int moves = Integer.parseInt(parts[1]);

            for(int i = 0; i < moves; i++) {
                switch (direction) {
                    case "D" -> head.y--;
                    case "U" -> head.y++;
                    case "R" -> head.x++;
                    case "L" -> head.x--;
                }

                for (int j = 1; j < knots.length; j++) {
                    if(!knots[j].bordering(knots[j-1])) {
                        int dx = knots[j-1].x - knots[j].x;
                        int dy = knots[j-1].y - knots[j].y;

                        if(dx < 0) knots[j].x--;
                        else if(dx > 0) knots[j].x++;

                        if(dy < 0) knots[j].y--;
                        else if(dy > 0) knots[j].y++;
                    }
                }
                knotOneVisited.add(knots[1].toString());
                knotTenVisited.add(knots[9].toString());

            }
        }

        //System.out.println("Part one: " + knotOneVisited.size());
        //System.out.println("Part two: " + knotTenVisited.size());

    }
}

class TwoDPoint {
    int x;
    int y;

    public TwoDPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean bordering(TwoDPoint p) {
        return Math.abs(p.x - x) <= 1 && Math.abs(p.y -y) <= 1;
    }
    
    @Override
    public String toString() {
        return x + " " + y;
    }
}