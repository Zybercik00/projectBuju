package com.github.zybercik00;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.DefaultListModel;

public class ListLoader {
    

    public DefaultListModel loadList() throws IOException {
        DefaultListModel fileList = new DefaultListModel<>();
        List<String> list = readFile();
        for (String element : list) {
            fileList.addElement(element);
        }
        return fileList;
    }

    public List<String> readFile() throws IOException {
        List<String> list;
        Path file = Path.of("/Users/kamilchmiel/projectBuju/listOfTopics.txt");
        list = Files.readAllLines(file, StandardCharsets.UTF_8);
        return list;
    }
}
