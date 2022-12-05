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
        for(int i = 0; i < 9; i++) {
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
        for(int i = 0; i < 9; i++) {
            System.out.print(allLists.get(i).get(0));
        }
        System.out.print("\n");
    }

    private static List buildStacks() throws IOException {
        List<List<Character>> allLists = new ArrayList<>();
        //en Queue är nog bättre egentligen
        List<Character> list0 = new ArrayList<>();
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        List<Character> list3 = new ArrayList<>();
        List<Character> list4 = new ArrayList<>();
        List<Character> list5 = new ArrayList<>();
        List<Character> list6 = new ArrayList<>();
        List<Character> list7 = new ArrayList<>();
        List<Character> list8 = new ArrayList<>();

        allLists.add(list0);
        allLists.add(list1);
        allLists.add(list2);
        allLists.add(list3);
        allLists.add(list4);
        allLists.add(list5);
        allLists.add(list6);
        allLists.add(list7);
        allLists.add(list8);
        
        var input = new File("2022/inputs/day5Stacks.txt");
        var br = new BufferedReader(new FileReader(input));

        String currentLine;
        int countStack;

        while((currentLine = br.readLine()) != null){
            var char1 = currentLine.toCharArray();
            countStack= 0;

            for(int i = 1; i < currentLine.length(); i += 4){
            if(char1[i] != ' ') {
                    allLists.get(countStack).add(char1[i]);
                }
            countStack++;
        
            }
        }

        return allLists;
    }

}