import java.io.*;
import java.util.*;


//Något är cursed i denna lösningen

public class Day7Solution {
    private static Map<String, Directory> dirs = new HashMap<>();
    public static void main(String[] args) throws IOException {
        partOne(); 
    }

    private static void partOne() throws IOException {
        partOneBuild();
        System.out.println("Part one: " + partOneV3());

    }
//Svaret ska bli 1367870

    private static long partOneV3() {
        long sum = 0;
        for(var entry : dirs.entrySet()) {
            var dir = entry.getValue();
            if(dir.size() <= 100000) {
                sum += dir.size();
            }
        }
        return sum;
    }

    private static long calculateOne() {
        long totalSum = 0;
        for(var entry : dirs.entrySet()) {
            long value = calcSizeRec(entry.getValue());
            if(value <= 100000){
                totalSum += value;
            }
        }
        return totalSum;
    }
    
    private static long calcSizeRec(Directory dir) {
        long sumLong = 0;
        if(!dir.subDirectories.isEmpty()){
            for(Directory d : dir.subDirectories) { 
                sumLong += calcSizeRec(d);
            }
        } 
        return dir.size + sumLong;
    }

    private static void partOneBuild() throws IOException {
        var input = new File("2022/inputs/day07.txt");
        var br = new BufferedReader(new FileReader(input));

        String currentLine;
        String currentFilePath = "";
        Directory dir;

        while((currentLine = br.readLine()) != null) {
            String[] parts = currentLine.split(" ");

            //Fixa pathen
            if(parts[1].equals("cd")) { 
                currentFilePath = getFilePath(parts[2], currentFilePath);
            }
            //Hämta eller skapa nytt directory
            dir = getDir(currentFilePath);
            //Kolla om det är en subdirectory och lägg till det i subDirs
            if(parts[0].equals("dir")) {
                dir.addSubDir(new Directory(currentFilePath + parts[1] + "/"));
            }

            // Om det är en siffra vet vi att det är en filstorlek
            if(isNumeric(parts[0])) {
                dir.increaseSize(Long.parseLong(parts[0]));
            }
            
        }
    }

    private static boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Directory getDir(String filePath) {
        if(dirs.containsKey(filePath)) {
            return dirs.get(filePath);
        } else {
            Directory dir = new Directory(filePath);
            dirs.put(filePath, dir);
            return dir;
        }
    }

    private static String getFilePath(String command, String currentFilePath) {
        if(command.equals("/")) {
            return "/";

        } else if(command.equals("..")) {
            String[] parts = currentFilePath.split("/");
            String res = "/";
            for(int i = 1; i < parts.length -1; i++) {
                res += parts[i] + "/";
                
            }
            return res;
        } else {
            return currentFilePath.concat(command + "/");
        }
    }
}

class Directory {
    long size;
    List<Directory> subDirectories;
    String dirPath;

        public Directory(String dirPath) {
            this.dirPath = dirPath;
            subDirectories = new ArrayList<>();
            this.size = 0;
        }

        public void addSubDir(Directory d) {
            subDirectories.add(d);
        }

        public void increaseSize(long size) {
            this.size += size;
        }
        @Override
        public String toString() {
            return this.size + "\n";
        }
        public long size(){
            long sumSize = size;

            for (Directory child : subDirectories) {
                size += child.size();
            }
    
            return sumSize;
        }
}