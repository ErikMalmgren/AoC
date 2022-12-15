import java.io.*;
import java.util.*;



public class Day11Solution {
    private static List<Monkey> monkeys;
    public static void main(String[] args) throws IOException {
        parseInput();
        rounds(20);
        System.out.println("Part one: " + monkeyBusiness());
    }

    public static long monkeyBusiness(){
        PriorityQueue<Long> pqInspections = new PriorityQueue<>((a,b) -> b.compareTo(a));
        for (Monkey monkey : monkeys) {
            pqInspections.offer(monkey.itemsInspected);
        }
        return pqInspections.poll() * pqInspections.poll();
    }

    public static void rounds(int rounds) {
        for (int i = 0; i < rounds; i++) { //Number of rounds
            for (Monkey monkey : monkeys) {
                int size = monkey.inventory.size();
                for (int j = 0; j < size; j++) {
                long[] res = monkey.action();
                    monkeys.get((int) res[1]).addItem(res[0]);
                }
            }
            
        }
    }

    public static void parseInput() throws IOException{
        var input = new File("2022/inputs/day11.txt");
        var reader = new BufferedReader(new FileReader(input));
        String line;
        monkeys = new ArrayList<>();

        while((line = reader.readLine()) != null) {

            //Parse starting items
            line = reader.readLine();
            String[] startingItems = line.split(":");
            String[] individualItems = startingItems[1].split(",");
            LinkedList<Long> inventory = new LinkedList<>();

            for(int i = 0; i < individualItems.length; i++) { //Trim whitespace and add to inventory
                individualItems[i] = individualItems[i].trim();
                inventory.add(Long.parseLong(individualItems[i]));
            }

            //Parse operation
            line = reader.readLine();
            String[] operationParts = line.split("=");
            String[] operationPartsExpression = operationParts[1].split(" ");
            String[] operation = new String[2];
            operation[0] = operationPartsExpression[2];
            operation[1] = operationPartsExpression[3];

            //Parse test
            line = reader.readLine();
            String[] testParts = line.split(" ");
            int divNumber = Integer.parseInt(testParts[testParts.length-1]);


            //Parse ifTrue
            line = reader.readLine();
            String[] ifTrueParts = line.split(" ");
            int ifTrue = Integer.parseInt(ifTrueParts[ifTrueParts.length-1]);

            //Parse ifFalse
            line = reader.readLine();
            String[] ifFalseParts = line.split(" ");
            int ifFalse = Integer.parseInt(ifFalseParts[ifFalseParts.length-1]);
            
            //Skip blank line
            reader.readLine();

            //Add monkey to List
            Monkey tempMonkey = new Monkey(inventory, operation, divNumber, ifTrue, ifFalse);
            monkeys.add(tempMonkey);
        }
        
    }
}


class Monkey {
    LinkedList<Long> inventory;
    String[] operation;
    int divNumber;
    int ifTrue;
    int ifFalse;
    long itemsInspected;

    public Monkey(LinkedList<Long> inventory, String[] operation, int divNumber, int ifTrue, int ifFalse) {
        this.inventory = inventory;
        this.operation = operation;
        this.divNumber = divNumber;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        itemsInspected = 0;
    }

    public long[] action() {
        long[] res = new long[2];
        int nextMonkey;

        long a = inventory.poll();
        itemsInspected++;

        a = utilities.parseOperation(a, operation);
        a = a/3;
        if(a % divNumber == 0) {
            nextMonkey = ifTrue;
        } else {
            nextMonkey = ifFalse;
        }
        res[0] = a;
        res[1] = nextMonkey;

        return res;
    }

    public void addItem(long item) {
        inventory.add(item);
    }

    

    
}

final class utilities {

    public static boolean isNumerical(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        }

        public static long parseOperation(long a, String[] operation) {
            long res = 0;
            if(operation[0].equals("+")) { //Math operand
                if(isNumerical(operation[1])) { //Rhs is number
                    res = a + Long.parseLong(operation[1]);
                } else {
                    res = a + a; 
                }
                
            } else {
                if(isNumerical(operation[1])) {
                    res = a * Long.parseLong(operation[1]);
                } else {
                    res = a * a;
                }
    
            }
            
            return res;
        }
}
