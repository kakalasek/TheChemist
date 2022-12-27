package Main;

import javax.swing.*;

public class Frame extends JFrame {

    TabbedPanel tabbedPanel;

    public Frame(){
        this.setResizable(false);
        this.setTitle("TheChemist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPanel = new TabbedPanel();
        this.add(tabbedPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
