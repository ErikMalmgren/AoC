import java.io.*;
import java.util.*;

public class Day7TryAgain {
    //Mycket hjälp från https://www.youtube.com/watch?v=vWXtVGQ2B0E
    //Väldigt lik min första lösning förrutom koppling mellan child -> parent
    //Skillnaden är att directoryt själft får räkna ut sin storlek, rimligt

    public static void main(String[] args) throws IOException{
        long elapsedTimeSum = 0;
        for (int i = 0; i < 1000; i++){
            long startTime = System.nanoTime();
            calc();
            long elapsedTime = System.nanoTime() - startTime;
            elapsedTimeSum += elapsedTime;
        }
        System.out.println("Average execution time (µs): " + elapsedTimeSum/1000000);
        
    }

    public static void calc() throws IOException {
        elfDirectory root = buildFileSystem();
        List<elfDirectory> dirs = findAllDirectories(root);

        long sum = 0;

        long freeSpace = 70000000 - dirs.get(0).size();
        long currentFileSize = Long.MAX_VALUE;
        long missingSpace = 30000000 - freeSpace;


        for (elfDirectory dir : dirs) {
            if(dir.size() <= 100000) {
                sum += dir.size();
            }
            if(dir.size() >= missingSpace && dir.size() < currentFileSize) {
                currentFileSize = dir.size();
            }
        }

        System.out.println("Part one: " + sum);
        System.out.println("Part two: " + currentFileSize);

        
    }

    private static List<elfDirectory> findAllDirectories(elfDirectory toSearch){
        
        List<elfDirectory> all = new ArrayList<>();
        all.add(toSearch);

        for (elfDirectory child : toSearch.children) {
            all.addAll(findAllDirectories(child));
        }
        return all;

    }

    private static elfDirectory buildFileSystem() throws IOException{
        var input = new File("2022/inputs/day7.txt");
        var br = new BufferedReader(new FileReader(input));

        elfDirectory root = new elfDirectory("/", null);
        elfDirectory currentDir =  root;
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            String[] parts = currentLine.split(" ");

            if(isNumeric(parts[0])) { //Rad med en fil
                currentDir.elfFiles.add(Long.parseLong(parts[0]));

            } else if (parts[0].equals("dir")) { //Hanter rad med nytt dir
                new elfDirectory(currentDir.name + parts[1] + "/", currentDir);

            } else if (parts[0].equals("$") && parts[1].equals("cd")){ //Hanterar rad med kommando
                currentDir = processDirectoryChange(currentDir, parts[2]);
            }

        }

        return root;

    }

    private static elfDirectory processDirectoryChange(elfDirectory currentDir, String command) {
        if(command.equals("..")) {
            return currentDir.parent;

        } else if (command.equals("/")){
            return currentDir;
        } else {
            String targetName = currentDir.name + command + "/";
            int index = 0;
            for(var d : currentDir.children) {
                if(d.name.equals(targetName)) {
                    break;
                }
                index++;
            }
            return currentDir.children.get(index);
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


}

class elfDirectory {
    String name;
    elfDirectory parent;
    List<elfDirectory> children;
    List<Long> elfFiles;

    public elfDirectory(String name, elfDirectory parent){
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        elfFiles = new ArrayList<>();

        if(parent != null){
            parent.children.add(this);
        }
    }

    public long size(){
        long size = 0;
        for (long a : elfFiles) {
            size += a;
        }

        for (elfDirectory child : children) {
            size += child.size();
        }

        return size;
    }


}