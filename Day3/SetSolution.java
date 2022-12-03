package Day3;
import java.io.*;
import java.util.*;


public class SetSolution {
    
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Runtime in ms: " + elapsedTime/1000000);
    }
    // Ungefär dubbelt så snabb som första lösningen tror O(n) mot minst O(n^2)

    private static int partTwo() throws IOException {
        var input = new File("inputs/day3.txt");
        var br = new BufferedReader(new FileReader(input));
        int sumPriority = 0;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            int biggest = 0; 

            Set<Character> inString1 = new HashSet<>();
            Set<Character> inString2 = new HashSet<>();

            String s1 = currentLine;
            String s2 = br.readLine();
            String s3 = br.readLine();

            var c1 = s1.toCharArray();
            var c2 = s2.toCharArray();
            var c3 = s3.toCharArray();

            for(char c : c1) {
                inString1.add(c);
            }
            for(char c : c2) {
                inString2.add(c);
            }
            for(char c : c3) {
                if(inString1.contains(c) && inString2.contains(c)) {
                    biggest = Math.max(Alphabet.getNum(c), biggest);
                }
            }
            sumPriority += biggest;
        }
        return sumPriority;
    }

    private static int partOne() throws IOException {
        var input = new File("inputs/day3.txt");
        var br = new BufferedReader(new FileReader(input));
        int sumPriority = 0;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            int mid = currentLine.length()/2;
            int biggest = 0; //Highest priority in backpack

            String s1 = currentLine.substring(0, mid);
            String s2 = currentLine.substring(mid);

            char[] c1 = s1.toCharArray(); 
            char[] c2 = s2.toCharArray();
           

            Set<Character> inString = new HashSet<>();

            for(char c : c1) {
                inString.add(c);
            }
            for(char c : c2) {
                if(inString.contains(c)){
                    biggest = Math.max(Alphabet.getNum(c), biggest);
                }
            }
            sumPriority += biggest;
        }
        return sumPriority;
    }

    public enum Alphabet {
        a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

        public static int getNum(char c) {
            return valueOf(String.valueOf(c)).ordinal() + 1;
        }
    }
}
