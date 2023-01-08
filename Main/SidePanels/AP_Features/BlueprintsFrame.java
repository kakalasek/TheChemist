package Main.SidePanels.AP_Features;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class BlueprintsFrame extends JFrame {
    private BlueprintsPanel blueprintsPanel;
    private static BlueprintsFrame instance;

    public static BlueprintsFrame getInstance() throws IOException {
        if(instance == null){
            instance = new BlueprintsFrame();
        }
        return instance;
    }

    private BlueprintsFrame() throws IOException {
        this.setResizable(false);
        this.setTitle("Blueprints");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        blueprintsPanel = new BlueprintsPanel();
        this.add(blueprintsPanel);
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
