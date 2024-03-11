package org.dstu;

import org.dstu.components.GraphicsPanel;
import org.dstu.shapes.Shape;
import org.dstu.shapes.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        int frameWidth = 800;
        int frameHeight = 768;

        int amplitudeScale = frameHeight / 3;
        int phaseScale = frameHeight / 2;
        int periodScale = 2;
        int speedScale = 10;

        List<Shape> shapes = new ArrayList<>();
        for (int x = 0; x <= 100; x += 5)
        {
            shapes.add(new Square(x, 0, 10, Color.RED));
        }

        GraphicsPanel canvas = new GraphicsPanel(shapes);

        Runnable circleController = () -> {
            Color currColor = Color.RED;
            boolean isMovingRight = true;
            boolean isEndReached = false;
            boolean isTimeToChangeColor = false;
            while (true) {
                for(Shape shape : shapes)
                {
                    int deltaX;
                    if (isMovingRight) {
                        deltaX = (int)(Math.random() + 10);
                    } else {
                        deltaX = -(int)(Math.random() + 10);
                    }
                    int x = shape.getX() + deltaX;
                    shape.moveTo(x, (int)(amplitudeScale*Math.sin(Math.toRadians(x*periodScale))) + phaseScale);

                    if ((isMovingRight && x >= frameWidth && !isEndReached) || (!isMovingRight && x <= 0 && !isEndReached)) {
                        isEndReached = true;
                    }
                    if (isTimeToChangeColor) {
                        shape.setColor(currColor);
                    }
                }

                if (isTimeToChangeColor)
                {
                    isTimeToChangeColor = false;
                }

                if (isEndReached) {
                    isEndReached = false;
                    isMovingRight = !isMovingRight;
                    currColor = Color.getHSBColor((float)Math.random(), (float)(0.5 + 0.5*Math.random()), (float)(0.5 + 0.5*Math.random()));
                    isTimeToChangeColor = true;
                }

                canvas.repaint();
                try {
                    Thread.sleep(250 / speedScale);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(canvas);
        frame.setVisible(true);

        Thread animationThread = new Thread(circleController);
        animationThread.start();
    }
}
