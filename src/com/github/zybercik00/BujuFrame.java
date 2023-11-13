package com.github.zybercik00;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BujuFrame extends JFrame implements ActionListener{

    JButton saveButton;
    JEditorPane editorPane;
    Path path = Path.of("newTxtFile.txt");

    public BujuFrame() {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setTitle("Bullet Jurnal");

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.setBackground(Color.GRAY);
        this.add(topPanel, BorderLayout.NORTH);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100,100));
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(saveButton,  BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        

        editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == saveButton) {
            String newFile = editorPane.getText();
            try {
                Files.writeString(path, newFile, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
            editorPane.setText("");
        }
    }
}
