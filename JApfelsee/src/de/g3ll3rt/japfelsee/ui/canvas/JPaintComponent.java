package de.g3ll3rt.japfelsee.ui.canvas;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class JPaintComponent extends JComponent {

    private List<PaintableObject> paints = new ArrayList<PaintableObject>();

    public void paintObj(final PaintableObject po) {
        paints.add(po);
    }

    public List<PaintableObject> getObjects() {
        return paints;
    }

    public void removeObj(final PaintableObject po) {
        paints.remove(po);
    }

    public void clear() {
        paints.clear();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        for (PaintableObject po : paints) {
            po.update(g);
        }
    }
}