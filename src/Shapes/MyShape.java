package Shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

public abstract class MyShape implements Serializable{
    protected BasicStroke pen = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    public Color color;
    public String shape;
    public JTextArea jTextArea=new JTextArea();
    //public abstract double getPointX();
    //public abstract double getPointY();
    public abstract String getButtonName();

    public abstract String getClassName();

    public abstract void setPoints(Vector<Point2D> p);

    public abstract boolean contains(Point2D p);

    public abstract void draw(Graphics g);

    public abstract void move(Point2D p1, Point2D p2);
    public abstract MyShape copy();
    public abstract void init();
}
