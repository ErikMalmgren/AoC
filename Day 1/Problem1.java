import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Problem1 {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/em/repos/AoC/Day 1/input1.txt");

        int cal = 0;
        ArrayList<Integer> cals = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            
            while(scan.hasNextLine()){
                String temp = scan.nextLine();
                if(temp.equals("")){
                    cals.add(cal);
                    cal = 0;
                } else {
                    cal += Integer.parseInt(temp);
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.out.println("Elf with most cals: " + Collections.max(cals));
        Collections.sort(cals);
        int sumCals = cals.get(cals.size()-1) + cals.get(cals.size()-2) + cals.get(cals.size()-3) ;

        System.out.println("Three elves with most calories: " + sumCals);


    }
}