package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChemicalPanel extends JPanel {
    private Font mainFont;
    private JButton resources;
    private JButton blueprints;
    private ResourcesFrame resourcesFrame;
    private BlueprintsFrame blueprintsFrame;

    public ChemicalPanel(){
        this.setLayout(null);
        this.setBounds(700, 0, 300, 700);
        this.setUpButtons();
        this.setUpActionListeners();
    }

    private void setUpButtons(){
        mainFont = new Font("Serif", Font.BOLD, 28);

        resources = new JButton("Resources");
        resources.setBounds(10, 30, 275, 50);
        resources.setFont(mainFont);
        this.add(resources);

        blueprints = new JButton("Blueprints");
        blueprints.setBounds(10, 100, 275, 50);
        blueprints.setFont(mainFont);
        this.add(blueprints);
    }

    private void setUpActionListeners(){
        ActionListener resourcesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               resourcesFrame = new ResourcesFrame();
            }
        };
        resources.addActionListener(resourcesListener);

        ActionListener blueprintsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blueprintsFrame = new BlueprintsFrame();
            }
        };
        blueprints.addActionListener(blueprintsListener);
    }
}
