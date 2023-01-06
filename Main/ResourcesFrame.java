package Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ResourcesFrame extends JFrame {
    ResourcesPanel resourcesPanel;
    private static ResourcesFrame instance;

    public static ResourcesFrame getInstance() throws IOException {
        if(instance == null){
            instance = new ResourcesFrame();
        }
        return instance;
    }

    private ResourcesFrame() throws IOException {
        this.setResizable(false);
        this.setTitle("Resources");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });
    }
}
