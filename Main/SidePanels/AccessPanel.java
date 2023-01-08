package Main.SidePanels;

import Main.SidePanels.AP_Features.BlueprintsFrame;
import Main.SidePanels.AP_Features.ResourcesFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AccessPanel extends JPanel {
    private Font buttonFont;
    private JButton resources;
    private JButton blueprints;
    private ResourcesFrame resourcesFrame;
    private BlueprintsFrame blueprintsFrame;

    public AccessPanel(){
        this.setLayout(null);
        this.setBounds(700, 0, 300, 700);

        this.manageButtons();
        this.setUpActionListeners();
    }

    /**
     * Renders buttons, their text and position
     */
    private void manageButtons(){
        buttonFont = new Font("Serif", Font.BOLD, 28);

        resources = new JButton("Resources");
        blueprints = new JButton("Blueprints");

        resources.setBounds(10, 30, 275, 50);
        blueprints.setBounds(10, 100, 275, 50);

        resources.setFont(buttonFont);
        blueprints.setFont(buttonFont);

        this.add(resources);
        this.add(blueprints);
    }

    /**
     * Adds an action listener to each button on the panel
     */
    private void setUpActionListeners(){
        ActionListener resourcesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ResourcesFrame.getInstance();
                } catch (IOException e) {
                    System.out.println("Error while opening resources tab!");
                    throw new RuntimeException(e);
                }
            }
        };
        resources.addActionListener(resourcesListener);

        ActionListener blueprintsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BlueprintsFrame.getInstance();
                } catch (IOException e) {
                    System.out.println("Error while opening blueprints tab!");
                    throw new RuntimeException(e);
                }
            }
        };
        blueprints.addActionListener(blueprintsListener);
    }
}
