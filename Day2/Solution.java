package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {


    public static void main(String[] args) {
        long time1 = System.nanoTime();
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
        long time2 = System.nanoTime();
        System.out.println("Runtime in ms: " + (time2 - time1)/1000000);
    }

    public static int partTwo(){
        int totalScore = 0;

        // A = rock, B = papper, C = scissor
        // X = lose, Y = draw, Z = win

        try {
            Scanner scan = new Scanner(new File("inputs/day2.txt"));
            
            while(scan.hasNextLine()){
                String temp = scan.nextLine();
                String opponentMove = temp.substring(0,1);
                String strategy = temp.substring(2, 3);
                String myMove = moveHelper(opponentMove, strategy);
                totalScore += pointCalc(myMove, strategy);

            }


        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return totalScore;
    }


    public static int pointCalc(String myMove, String strategy){
        int res = 0;
        if(myMove.equals("A")){
            res += 1;
        }
        if(myMove.equals("B")){
            res += 2;
        }
        if(myMove.equals("C")){
            res += 3;
        }
        if(strategy.equals("Y")){
            res += 3;
        }
        if(strategy.equals("Z")){
            res += 6;
        }


        return res;
    }

    public static String moveHelper(String opponent, String strategy){
        String res = "";

        if(strategy.equals("X")){
            if(opponent.equals("A")){
                res = "C";
            }
            if(opponent.equals("B")){
                res = "A";
            }
            if(opponent.equals("C")){
                res = "B";
            }
        }
        if(strategy.equals("Y")){
            res = opponent;
        }
        if(strategy.equals("Z")){
            if(opponent.equals("A")){
                res = "B";
            }
            if(opponent.equals("B")){
                res = "C";
            }
            if(opponent.equals("C")){
                res = "A";
            }
        }

        return res;
    }

    public static int partOne(){
        int totalScore = 0;
        // A = rock, B = paper, C = scissor
        // X = rock, Y = paper, Z = scissor
        try{
            Scanner scan = new Scanner(new File("inputs/day2.txt"));
            
            while(scan.hasNextLine()){
                String temp = scan.nextLine();
                String opponentMove = temp.substring(0, 1);
                String myMove = temp.substring(2, 3);
                
                if(myMove.equals("X")){
                    totalScore += 1 + rockHelperOne(opponentMove);
                }

                if(myMove.equals("Y")){
                    totalScore += 2 + paperHelperOne(opponentMove);
                }

                if(myMove.equals("Z")){
                    totalScore += 3 + scissorHelperOne(opponentMove);
                }
            }
        } catch (FileNotFoundException e1){
            e1.printStackTrace();
        }

        return totalScore;
    }


    public static int rockHelperOne(String opponent){
        int res = -1;
        if(opponent.equals("A")){
            res = 3;
        }
        if(opponent.equals("B")){
            res = 0;
        }
        if(opponent.equals("C")){
            res = 6;
        }
        return res;
    }

    public static int paperHelperOne(String opponent){
        int res = -1;
        if(opponent.equals("A")){
            res = 6;
        }
        if(opponent.equals("B")){
            res = 3;
        }
        if(opponent.equals("C")){
            res = 0;
        }
        return res;
    }

    public static int scissorHelperOne(String opponent){
        int res = -1;
        if(opponent.equals("A")){
            res = 0;
        }
        if(opponent.equals("B")){
            res = 6;
        }
        if(opponent.equals("C")){
            res = 3;
        }
        return res;
    }
}
