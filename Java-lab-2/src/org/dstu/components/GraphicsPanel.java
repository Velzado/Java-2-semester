package org.dstu.components;
import org.dstu.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class GraphicsPanel extends JPanel {
    private List<Shape> shapes;
    public List<Shape> getShapes() {
        return shapes;
    }
    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
    public GraphicsPanel(List<Shape> shapes) {
        this.shapes = shapes;
    }
    public GraphicsPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape: shapes) {
            shape.draw(g);
        }
    }
}
