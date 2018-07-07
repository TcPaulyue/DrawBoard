package Shapes;

import util.Mover;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class MyEllipse extends MyShape implements Serializable {
    private Ellipse2D ellipse2D = new Ellipse2D.Double();
    private Point2D[] points = new Point2D[2];

    @Override
    public String getButtonName() {
        return "Ellipse";
    }

    @Override
    public String getClassName() {
        return "MyEllipse";
    }

    @Override
    public void init() {
        ellipse2D.setFrameFromDiagonal(points[0], points[1]);
        //jTextArea.setBackground(this.color);
        jTextArea.setBackground(new Color(238,238,238));
        jTextArea.setVisible(true);
    }

    public double getWidth() {
        return ellipse2D.getWidth();
    }

    public double getHeight() {
        return ellipse2D.getHeight();
    }
    public MyShape copy()
    {
        MyEllipse temp = new MyEllipse();
        temp.points[0]=new Point2D.Double(this.points[0].getX(),this.points[0].getY());
        temp.points[1]=new Point2D.Double(this.points[1].getX(),this.points[1].getY());
        temp.init();
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //jTextArea.setBackground(this.color);
        jTextArea.setBounds((int)( points[0].getX()+points[1].getX())/2-30,(int)( points[0].getY()+points[1].getY())/2-30,(int) width/2,(int) len/2);
        temp.jTextArea.setText(jTextArea.getText());
        temp.jTextArea.setVisible(true);
        return temp;
    }
    @Override
    public void setPoints(Vector<Point2D> p) {
        p.toArray(points);
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //System.out.println(len+"-----"+width);
        jTextArea.setBounds((int)( points[0].getX()+points[1].getX())/2-30,(int)( points[0].getY()+points[1].getY())/2-30,(int) width/2,(int) len/2);

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(pen);
        graphics2D.setColor(color);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.draw(ellipse2D);
    }

    @Override
    public boolean contains(Point2D p) {
        return ellipse2D.contains(p);
    }

    @Override
    public void move(Point2D p1, Point2D p2) {
        Mover.move(p1, p2, points);
        init();
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //System.out.println(len+"-----"+width);
        jTextArea.setBounds((int)( points[0].getX()+points[1].getX())/2-30,(int)( points[0].getY()+points[1].getY())/2-30,(int) width/2,(int) len/2);
    }
}
