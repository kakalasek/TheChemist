package Main.MainComponents;

import Main.Panels.ChemicalMaker;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {
    private ChemicalMaker chemicalMaker;

    public TabbedPanel(){
        this.setPreferredSize(new Dimension(1000, 700));

        chemicalMaker = new ChemicalMaker();
        this.addTab("Chemical Maker", chemicalMaker);
    }
}
