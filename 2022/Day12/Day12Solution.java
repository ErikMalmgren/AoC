import java.io.*;
import java.util.*;

public class Day12Solution {
    private static int[][] map;
    private static int[][] distance;
    private static int rowStart;
    private static int colStart;
    private static int rowEnd;
    private static int colEnd;
    public static void main(String[] args) throws IOException{
        long elapsedTimeSum = 0;
        for (int i = 0; i < 100; i++){
            long startTime = System.nanoTime();
            calculate();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/100000);
    }
    

    private static void calculate() throws IOException{
        parseInput();
        BSF(rowStart, colStart);
        //System.out.println("Part one: " + distance[rowEnd][colEnd]);
        partTwo();
        //System.out.println("Part two: " + partTwo());
    }

    //BSF från toppen och sen kolla avstånde från alla a-positioner, funkar inte men tanken är god
    private static int partTwoV2() {
        resetDistance();
        BSF(rowEnd, colEnd);

        int shortestDistace = Integer.MAX_VALUE;

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'a') {
                    if(distance[i][j] < shortestDistace && distance[i][j] != -1) {
                        shortestDistace = distance[i][j];
                    }
                    }
                }
            }
        return shortestDistace;
    }

    //BSF från alla a-positioner
    private static int partTwo() {
        int shortestDistace = Integer.MAX_VALUE;

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'a') {
                    resetDistance();
                    BSF(i, j);
                    if(distance[rowEnd][colEnd] < shortestDistace && distance[rowEnd][colEnd] != -1) {
                        shortestDistace = distance[rowEnd][colEnd];
                    }
                    }
                }
            }
        return shortestDistace;
    }

    private static void BSF(int startingRow, int startingCol) {
        LinkedList<int[]> queue = new LinkedList<>();
        int[] currentPos = new int[2];
        currentPos[0] = startingRow;
        currentPos[1] = startingCol;
        queue.add(currentPos);

        distance[currentPos[0]][currentPos[1]] = 0; // Distance to startingposition is zero

        while(!queue.isEmpty()) {

            currentPos = queue.pop();
            int row = currentPos[0];
            int col = currentPos[1];
 

            //Check up
            if(checkInBounds(row - 1, col) && travelable(row, col, row - 1, col) && !visited(row - 1, col)) {
                distance[row - 1][col] = 1 + distance[row][col];
                int[] north = new int[2];
                north[0] = row - 1;
                north[1] = col;
                queue.add(north);

            }
             
            //Check down
            if(checkInBounds(row + 1, col) && travelable(row, col, row + 1, col) && !visited(row + 1, col)) {
                distance[row + 1][col] = 1 + distance[row][col];
                int[] down = new int[2];
                down[0] = row + 1;
                down[1] = col;
                queue.add(down);
            }

            //Check right
            if(checkInBounds(row, col + 1) && travelable(row, col, row, col + 1) && !visited(row, col + 1)) {
                distance[row][col + 1] = 1 + distance[row][col];
                int[] right = new int[2];
                right[0] = row;
                right[1] = col + 1;
                queue.add(right);
            }

            //Check left
            if(checkInBounds(row, col - 1) && travelable(row, col, row, col - 1) && !visited(row, col - 1)) {
                distance[row][col - 1] = 1 + distance[row][col];
                int[] left = new int[2];
                left[0] = row;
                left[1] = col - 1;
                queue.add(left);
            }
        }
    }


    private static void parseInput() throws IOException{

        var input = new File("2022/inputs/day12.txt");
        var reader = new BufferedReader(new FileReader(input));
       
        var tempRead = new BufferedReader(new FileReader(input));

        String tempString = tempRead.readLine();
        int width = tempString.length();
        int length = 1;
        while(tempRead.readLine() != null) {
            length++;
        }
        tempRead.close();
        
        distance = new int[length][width]; 
        resetDistance();
        map = new int[length][width];

        String line;
        int row = 0;
        int column;

        while((line = reader.readLine()) != null) {
            char[] mapHeights = line.toCharArray();
            column = 0;

            for(char a : mapHeights) {
                map[row][column] = a;
                column++;
            }

            row++;
        }
        findStartPosition();
        findEndPosition();

        map[rowStart][colStart] = 'a';
        map[rowEnd][colEnd] = 'z';

    }

    private static void resetDistance(){
        for(int i = 0; i < distance.length;i++) {
            for(int j = 0; j < distance[0].length; j++) {
                distance[i][j] = -1;
            }
        }
    }

    private static void printMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    private static void printDistance() {
        for(int i = 0; i < distance.length; i++) {
            for(int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static void findStartPosition() {
        int[] res = new int[2];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'S') {
                    rowStart = i;
                    colStart = j;
                    break;
                }
            }
        }
    }

    private static void findEndPosition() {
        int[] res = new int[2];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'E') {
                    rowEnd = i;
                    colEnd = j;
                    break;
                }
            }
        }
    }

    private static boolean checkInBounds(int row, int col) {
        if(row < 0 || col < 0) {
            return false;
        }
        if(row > map.length - 1|| col > map[0].length - 1) {
            return false;
        }

        return true;
    }

    private static boolean travelable(int row1, int col1,int row2, int col2) {

        if(map[row2][col2] - map[row1][col1] <= 1) {
            return true;
        }
        return false;
    
    }

    private static boolean visited(int row, int col) {
        if(distance[row][col] == -1) {
            return false;
        }
        return true;
    }
}

