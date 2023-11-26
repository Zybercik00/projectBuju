package com.github.zybercik00;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.SQLException;

import javax.swing.JEditorPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BujuController implements ActionListener {

    private Path path;
    private ListCreator listCreator = new ListCreator();
    private ListLoader listLoader = new ListLoader();
    private JEditorPane editorPane;
    private JTextField textField;
    private DbConnections dbConnections;

    public BujuController(JEditorPane editorPane, JTextField textField)  {
        super();
        this.editorPane = editorPane;
        this.textField = textField;
    }
        

    
    @Override
    public void actionPerformed(ActionEvent event){
        try  {
        BujuFrame bujuFrame = new BujuFrame();
        String action = event.getActionCommand();
        if (action.equals("save")) {
            String newFile = editorPane.getText();
            String textTopic = textField.getText();

            path = Path.of("/Users/kamilchmiel/projectBuju/resource/bujutxtfiles/" + textTopic + ".txt");
            if (bujuFrame.getTextField() != null) {
                try {
                Files.writeString(path, newFile, StandardCharsets.UTF_8);
                bujuFrame.getFileList().addElement(textTopic);
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
            editorPane.setText("");
            textField.setText("");
            }
            try {
            listCreator.launchCreator();
            listLoader.loadList();

            } catch (IOException e) {
                e.printStackTrace();
            }
            
            dbConnections = new DbConnections();
            dbConnections.createConnections(textTopic, newFile, newFile);
            
        }
    } catch (IOException ioException) {
        ioException.printStackTrace();
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }
}
}


