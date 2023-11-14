package com.github.zybercik00;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ListCreator  extends SimpleFileVisitor<Path> {

    private static Path directory = Path.of("/Users/kamilchmiel/projectBuju");
    private static Path listDirectory = Path.of("/Users/kamilchmiel/projectBuju/listOfTopics.txt");
    List<String> namesList = new ArrayList<>();


    
     @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {
        
        if (attributes.isRegularFile()) {
            String filename = path.getFileName().toString();
            int indexOfDot = filename.lastIndexOf(".");
            if (indexOfDot < 0) {
                return FileVisitResult.CONTINUE; 
            } 
            String fileExtention = filename.substring(indexOfDot + 1);
            if (fileExtention.equals("txt")) {
                addToList(filename);
            }
           } 
           Files.write(listDirectory, namesList, StandardCharsets.UTF_8);
        return FileVisitResult.CONTINUE; 
    }

    public void addToList(String name) {
        if (name != "listOfTopics.txt"){
        namesList.add(name);
        }
    }
}
