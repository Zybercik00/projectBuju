package com.github.zybercik00;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BujuApp {
    private static Path directory = Path.of("/Users/kamilchmiel/projectBuju");
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ListCreator listCreator = new ListCreator();
        Files.walkFileTree(directory, listCreator);
        ListLoader listLoader = new ListLoader();
        listLoader.loadList();
        BujuFrame bujuFrame = new BujuFrame();
    }
}
