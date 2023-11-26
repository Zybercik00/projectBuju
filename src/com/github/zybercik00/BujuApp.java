package com.github.zybercik00;

import java.io.IOException;
import java.sql.SQLException;

public class BujuApp {
    
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        ListCreator listCreator = new ListCreator();
        listCreator.launchCreator();
        ListLoader listLoader = new ListLoader();
        listLoader.loadList();
        BujuFrame bujuFrame = new BujuFrame();
    }
}
