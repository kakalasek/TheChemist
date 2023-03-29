package Main.MainComponents;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainFrame extends JFrame {

    private TabbedPanel tabbedPanel;

    public MainFrame(){
        this.setResizable(false);
        this.setTitle("TheChemist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPanel = new TabbedPanel();
        this.add(tabbedPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new FileOutputStream("Main/Files/Blueprints").close();
                    new FileOutputStream("Main/Files/Resources").close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
