import java.io.*;
import java.util.*;

public class DayFiveSolution {

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

    public static void partTwo() throws IOException {
        var input = new File("2022/inputs/day5Moves.txt");
        var br = new BufferedReader(new FileReader(input));
        List<List<Character>> allLists = buildStacks();
        int amount;
        List<Character> listFrom;
        List<Character> listTo;
        List<Character> temp;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            String[] stringParts = currentLine.split(" ");
            amount = Integer.parseInt(stringParts[1]);
            listFrom = allLists.get(Integer.parseInt(stringParts[3])-1);
            listTo = allLists.get(Integer.parseInt(stringParts[5])-1);
            temp = new ArrayList<>();

            for(int i = 0; i < amount; i++) {
                temp.add(0, listFrom.get(0));
                listFrom.remove(0);
            }
            for(char c :temp) {
                listTo.add(0, c);
            }
        }
        System.out.print("Part two: ");
        for(int i = 0; i < allLists.size(); i++) {
            System.out.print(allLists.get(i).get(0));
        }
        System.out.print("\n");
    }

    public static void partOne() throws IOException {
        var input = new File("2022/inputs/day5Moves.txt");
        var br = new BufferedReader(new FileReader(input));
        List<List<Character>> allLists = buildStacks();
        String currentLine;
        List<Character> listFrom;
        List<Character> listTo;

        while((currentLine = br.readLine()) != null) {
            String[] stringParts = currentLine.split(" ");
            int amount = Integer.parseInt(stringParts[1]);
            listFrom = allLists.get(Integer.parseInt(stringParts[3])-1);
            listTo = allLists.get(Integer.parseInt(stringParts[5])-1);


            for(int i = 0; i < amount; i++) {
                listTo.add(0 ,listFrom.get(0));
                listFrom.remove(0);
            }
        }
        System.out.print("Part one: ");
        for(int i = 0; i < allLists.size(); i++) {
            System.out.print(allLists.get(i).get(0));
        }
        System.out.print("\n");
    }

    private static List<List<Character>> buildStacks() throws IOException {

        List<List<Character>> lists = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            lists.add(new ArrayList<>());
        }
        //en Queue eller stack är nog bättre egentligen
        
        var input = new File("2022/inputs/day5Stacks.txt");
        var br = new BufferedReader(new FileReader(input));

        String currentLine;
        int countStack;

        while((currentLine = br.readLine()) != null){
            var char1 = currentLine.toCharArray();
            countStack= 0;

            for(int i = 1; i < currentLine.length(); i += 4){
            if(char1[i] != ' ') {
                    lists.get(countStack).add(char1[i]);
                }
            countStack++;
        
            }
        }

        return lists;
    }

}