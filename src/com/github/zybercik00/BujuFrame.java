package com.github.zybercik00;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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

public class BujuFrame extends JFrame implements ActionListener {

    JButton saveButton;
    JEditorPane editorPane;
    Path path;
    JTextField textField;
    DefaultListModel fileList;
    JList list;
    JButton applyButton;
    JButton deleteButton;
    Path lisPath = Path.of("/Users/kamilchmiel/projectBuju/listOfTopics.txt");
    ListLoader listLoader = new ListLoader();

    public BujuFrame() throws IOException {

        
        fileList = listLoader.loadList();

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
        textField.addActionListener(this);

        JLabel textFieldLabel = new JLabel();
        textFieldLabel.setText("Topic: ");


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.setBackground(new Color(90,128,30));
        topPanel.add(textFieldLabel);
        topPanel.add(textField);
        
        

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100,100));
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(saveButton,  BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        applyButton = new JButton("Apply");
        applyButton.setActionCommand("Aplly");
        applyButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(this);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setPreferredSize(new Dimension(200,200));
        rightPanel.setBackground(Color.GRAY);
        rightPanel.add(listScrollPane);
        rightPanel.add(applyButton);
        rightPanel.add(deleteButton);
        this.add(rightPanel, BorderLayout.EAST);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(50,50));
        leftPanel.setBackground(Color.GRAY);
        
        this.add(leftPanel, BorderLayout.WEST);

        editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
        this.add(topPanel, BorderLayout.NORTH);
        this.setVisible(true);

        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == saveButton) {
            String newFile = editorPane.getText();
            String textTopic = textField.getText();
            path = Path.of(textTopic + ".txt");
            if (textField != null) {
                try {
                Files.writeString(path, newFile, StandardCharsets.UTF_8);
                fileList.addElement(textTopic);
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
            editorPane.setText("");
            textField.setText("");
            }  
        }
    }
}
