package Main.ErrorFrames;

import javax.swing.*;

public class ErrorFrame extends JFrame {
    private ErrorPanel errorPanel;

    public ErrorFrame(String errorMessage){
        this.setResizable(false);
        this.setTitle("ERROR");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        errorPanel = new ErrorPanel(errorMessage);
        this.add(errorPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
