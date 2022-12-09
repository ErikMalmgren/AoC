import java.io.*;

public class Day8Solution {
    private static int[][] matrix;
    public static void main(String[] args) throws IOException{
        long elapsedTimeSum = 0;
        for (int i = 0; i < 100000; i++){
            long startTime = System.nanoTime();
            calculate();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/100000000);
    }

    public static void calculate() throws IOException{
        buildIntMatrix();
        int visibleTrees = 2 * matrix.length + 2 * matrix[0].length - 4;
        int maxScenicScore = 0;
        for(int i = 1; i < matrix.length -1; i ++) {
            for(int j = 1; j < matrix[i].length -1; j++) {
                int[] result = checkVisibility(i, j);
                if(result[0] == 1) {
                    visibleTrees++;
                }
                if(result[1] > maxScenicScore) {
                    maxScenicScore = result[1];
                }
            }
        }

        //System.out.println("Part one: " + visibleTrees);
        //System.out.println("Part two: " + maxScenicScore);
    }

    private static int[] checkVisibility(int row, int col) {
        int value = matrix[row][col];
        int scenicScoreUp = 0;
        int scenicScoreDown = 0;
        int scenicScoreLeft = 0;
        int scenicScoreRight = 0;

        boolean visibleUp = true; 
        boolean visibleDown = true;
        boolean visibleLeft = true;
        boolean visibleRight = true;

        //Kolla upp
        for(int i = row-1; i >= 0; i--) {
            scenicScoreUp++;
            if(matrix[i][col] >= value) {
                visibleUp = false;
                break;
            }
            
        }

        //Kolla ner
        for(int i = row+1; i < matrix[0].length; i++) {
            scenicScoreDown++;
            if(matrix[i][col] >= value) {
                visibleDown = false;
                break;
            }
            
        }

        //Kolla vänster
        for(int i = col -1;  i >= 0; i--) {
            scenicScoreLeft++;
            if(matrix[row][i] >= value) {
                visibleLeft = false;
                break;
            }
            
        }

        //Kolla höger
        for(int i = col+1; i < matrix.length; i++) {
            scenicScoreRight++;
            if(matrix[row][i] >= value) {
                visibleRight = false;
                break;
            }
            
        }

        boolean visible = visibleUp || visibleDown || visibleLeft || visibleRight;
        int[] tupp = new int[2];
        tupp[0] = visible ? 1:0;
        tupp[1] = scenicScoreUp*scenicScoreDown*scenicScoreLeft*scenicScoreRight;

        return tupp;
    }
    

    public static void buildIntMatrix() throws IOException {
        var input = new File("2022/inputs/day8.txt");
        var br = new BufferedReader(new FileReader(input));
       
        //var tempRead = new BufferedReader(new FileReader(input));
        //String tempString = tempRead.readLine();
        //int width = tempString.length();
        //int length = 1;
        //while(tempRead.readLine() != null) {
        //    length++;
        //}
        //tempRead.close();
        

        matrix = new int[99][99]; //Ingen märkbartidsbesparing på att hårdkoda storleken på min int matris kanske 0.01 ms

        String line;
        int row = 0;
        int column;

        while((line = br.readLine()) != null) {
            char[] lineHeights = line.toCharArray();
            column = 0;

            for(char a : lineHeights) {
                matrix[row][column] = a - 48;
                column++;
            }

            row++;
        }
    }
}