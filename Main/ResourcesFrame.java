package Main;

import javax.swing.*;

public class ResourcesFrame extends JFrame {
    ResourcesPanel resourcesPanel;

    public ResourcesFrame(){
        this.setResizable(false);
        this.setTitle("Resources");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
