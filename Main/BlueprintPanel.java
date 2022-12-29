package Main;

import javax.swing.*;
import java.awt.*;

public class BlueprintPanel extends JPanel {
    private JLabel title;
    private Font titleFont;
    private FontMetrics fontMetrics;

    public BlueprintPanel(){
        this.setLayout(null);
        this.setBounds(700, 0, 300, 700);

        titleFont = new Font("Serif", Font.BOLD, 25);
        title = new JLabel("Blueprints");
        fontMetrics = title.getFontMetrics(titleFont);
        title.setFont(titleFont);
        title.setBounds(150-fontMetrics.stringWidth("Blueprints")/2, 10, fontMetrics.stringWidth("Blueprints"), 50);
        this.add(title);
    }

}
