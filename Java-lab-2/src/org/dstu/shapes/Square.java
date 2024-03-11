package org.dstu.shapes;

import java.awt.*;
public class Square extends Shape {
    protected int side;
    public Square() {
    }
    public Square(int x, int y, int side, Color color) {
        super(x, y, color);
        this.side = side;
    }
    public int getSide() {
        return side;
    }
    public void setSide(int side) {
        this.side = side;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(x, y, side, side);
        g.fillRect(x, y, side, side);
    }
}
