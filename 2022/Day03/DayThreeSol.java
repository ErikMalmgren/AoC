import java.io.*;

public class DayThreeSol {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        for(int i = 0 ; i < 1000; i++) {
            System.out.println("Part one: " + partOne());
            System.out.println("Part two: " + partTwo());
        }
       
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Runtime in ms: " + elapsedTime/1000000);
        
    }


    private static int partTwo() throws IOException {
        var input = new File("2022/inputs/day03.txt");
        var br = new BufferedReader(new FileReader(input));
        int sumPriority = 0;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            String s1 = currentLine;
            String s2 = br.readLine();
            String s3 = br.readLine();
            int biggest = 0;

            for(int i = 0; i< s1.length(); i++) {
                char c = s1.charAt(i);
                int prio = Alphabet.getNum(c);

                for(int j = 0; j < s2.length(); j++) {
                    if(c == s2.charAt(j)){
                        for(int k = 0; k < s3.length(); k++) {
                            if(c == s3.charAt(k)){
                                if(prio > biggest) {
                                    biggest = prio;
                                }
                            }
                        }
                    }
                }
            }

            sumPriority += biggest;
            
        }


        return sumPriority;
    }

    private static int partOne() throws IOException {
        var input = new File("2022/inputs/day03.txt");
        var br = new BufferedReader(new FileReader(input));
        int sumPriority = 0;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            int mid = currentLine.length()/2;
            String s1 = currentLine.substring(0, mid);
            String s2 = currentLine.substring(mid);
            int biggest = 0; //Highest priority in backpack

            for(int i = 0; i < s1.length(); i++) {
                
                char c = s1.charAt(i);
                int prio = Alphabet.getNum(c);

                for(int j = 0; j < s2.length(); j++) {
                    if(c == s2.charAt(j)) {
                        if(prio > biggest) {
                            biggest = prio;
                        }
                    }

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
