package Main;

import javax.swing.*;

public class BlueprintsFrame extends JFrame {
    BlueprintsPanel blueprintsPanel;

    public BlueprintsFrame(){
        this.setResizable(false);
        this.setTitle("Blueprints");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        blueprintsPanel = new BlueprintsPanel();
        this.add(blueprintsPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
