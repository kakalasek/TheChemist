package Main;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {
    private SubstanceMaker substanceMaker;

    public TabbedPanel(){
        this.setPreferredSize(new Dimension(1000, 700));
        substanceMaker = new SubstanceMaker();
        this.addTab("Maker", substanceMaker);
    }
}
