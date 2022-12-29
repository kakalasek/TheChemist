package Main;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {
    SubstanceMaker substanceMaker = new SubstanceMaker();

    public TabbedPanel(){
        this.setPreferredSize(new Dimension(1000, 700));
        this.addTab("Maker", substanceMaker);
    }
}
