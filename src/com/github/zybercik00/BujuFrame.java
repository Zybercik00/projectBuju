package com.github.zybercik00;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

public class BujuFrame extends JFrame  {

    private JButton saveButton;
    private JEditorPane editorPane;
    private Path path;
    private JTextField textField;
    private DefaultListModel fileList;
    private JList list;
    private JButton applyButton;
    private JButton deleteButton;
    private Path lisPath = Path.of("/Users/kamilchmiel/projectBuju/listOfTopics.txt");
    private static Path directory = Path.of("/Users/kamilchmiel/projectBuju");
    private ListLoader listLoader = new ListLoader();
    
    

    public BujuFrame() throws IOException  {

        fileList = listLoader.loadList();
        

        editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        list = new JList(fileList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        // list.addListSelectionListener(this);
        list.setVisibleRowCount(20);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBackground(Color.GREEN);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setTitle("Bullet Jurnal");

        textField = new JTextField(10);
        textField.setActionCommand("Topic of your texst: ");
        
        JLabel textFieldLabel = new JLabel();
        textFieldLabel.setText("Topic: ");


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.setBackground(new Color(90,128,30));
        topPanel.add(textFieldLabel);
        topPanel.add(textField);
        
        
        BujuController bujuController = new BujuController(editorPane, textField);
        textField.addActionListener(bujuController);
        saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(bujuController);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100,100));
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(saveButton,  BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setPreferredSize(new Dimension(200,200));
        rightPanel.setBackground(Color.GRAY);
        rightPanel.add(listScrollPane);
        this.add(rightPanel, BorderLayout.EAST);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(50,50));
        leftPanel.setBackground(Color.GRAY);
        
        this.add(leftPanel, BorderLayout.WEST);

        
        this.add(scrollPane);
        this.add(topPanel, BorderLayout.NORTH);
        this.setVisible(true);
        
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }
    public JTextField getTextField() {
        return textField;
    }
    public DefaultListModel getFileList() {
        return fileList;
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }
    
}
