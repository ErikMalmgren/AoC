import java.io.*;
import java.util.*;



public class Day9SolutionP1 {

    private static int headX;
    private static int headY;
    private static int tailX;
    private static int tailY;
    private static Set<String> tailVisited = new HashSet<>();

    public static void main(String[] args) throws IOException{
        readFile();
        System.out.println("Part one: " + tailVisited.size());
    }

    private static void moveHeadAndTail(String direction, int moves) {
        for(int i = 0; i < moves; i++) {
            switch(direction) {

                case "U":
                headY++;

                if(headX == tailX && !bordering()) {
                    tailY++;
                } else if(!bordering()) {
                    tailY++;
                    tailX = headX;
                }
                break;

                case "D":
                headY--;

                if(headX == tailX && !bordering()) {
                    tailY--;
                } else if(!bordering()) {
                    tailY--;
                    tailX = headX;
                }
                break;

                case "R":
                headX++;

                if(headX == tailX && !bordering()) {
                    tailX++;
                } else if(!bordering()) {
                    tailX++;
                    tailY = headY;
                }
                break;

                case "L":
                headX--;

                if(headY == tailY && !bordering()) {
                    tailX--;
                } else if(!bordering()) {
                    tailX--;
                    tailY = headY;
                }
                break;

                }

                tailVisited.add(Integer.toString(tailX) + " " + Integer.toString(tailY));

            }
  
        }
    

    private static boolean bordering() {
        return Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1;
    }


    private static void readFile() throws IOException {
        var input = new File("2022/inputs/day9.txt");
        var reader = new BufferedReader(new FileReader(input));
        headX = 0;
        headY = 0;
        tailX = 0;
        tailY = 0;
        String line;
        tailVisited.add("0 0");

        while((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            moveHeadAndTail(parts[0], Integer.parseInt(parts[1]));
        }
    }

}
