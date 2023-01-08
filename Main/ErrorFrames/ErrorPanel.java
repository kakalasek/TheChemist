package Main.ErrorFrames;

import javax.swing.*;
import java.awt.*;

public class ErrorPanel extends JPanel {
    private JLabel errorMessageLabel;
    private Font labelfont;

    public ErrorPanel(String errorMessage){
        this.setPreferredSize(new Dimension(300, 200));
        this.setLayout(null);

        labelfont = new Font("Serif", Font.BOLD, 20);

        errorMessageLabel = new JLabel(errorMessage);
        errorMessageLabel.setFont(labelfont);
        errorMessageLabel.setBounds(3, 62, 250, 50);
        this.add(errorMessageLabel);
    }

}
