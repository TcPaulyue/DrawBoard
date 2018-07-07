package Shapes;

import util.Mover;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.io.Serializable;
import java.util.Vector;

public class MySquare extends MyShape implements Serializable {
    private  Rectangle2D square = new Rectangle2D.Double();
    private Point2D[] points = new Point2D[2];

    @Override
    public void setPoints(Vector<Point2D> p) {
        p.toArray(points);
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //System.out.println(len+"-----"+width);
        jTextArea.setBounds((int) points[0].getX()-100,(int) points[0].getY()+80,(int) width-100,(int) len-50);
   }

    @Override
    public void init() {
        double centerX = (points[0].getX() + points[0].getX()) / 2;
        double centerY = (points[1].getY() + points[1].getY()) / 2;
        double RadiusOfCircle = (points[0].distance(points[1])) / 2;
        //Point2D的distance()方法返回两点之间的距离.
        square.setFrameFromCenter(centerX, centerY, centerX + RadiusOfCircle, centerY + RadiusOfCircle);
        //jTextArea.setBackground(this.color);
        jTextArea.setBackground(new Color(238,238,238));
        jTextArea.setVisible(true);
        /*
         *setFrameFromCenter(double centerX, double centerY, double cornerX, double cornerY)
         *设置这样一个矩形: 以(centerX, centerY)为几何中心,(cornerX, conerY)是其任意一个顶点.
         *把顶点设置为(centerX + RadiusOfCircle, centerY + RadiusOfCircle)保证squre的几何中心
         *到任意顶点等距离,从而实现了正方形.
         */
    }
    public MyShape copy()
    {
        MySquare temp = new MySquare();
        temp.points[0]=new Point2D.Double(this.points[0].getX(),this.points[0].getY());
        temp.points[1]=new Point2D.Double(this.points[1].getX(),this.points[1].getY());
        temp.init();
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        jTextArea.setBounds((int) points[0].getX()+20,(int) points[0].getY()+20,(int) width-20,(int) len-20);
        temp.jTextArea.setText(jTextArea.getText());
        temp.jTextArea.setVisible(true);
        return temp;
    }
    @Override
    public String getButtonName() {
        return "Square";
    }

    @Override
    public String getClassName() {
        return "MySquare";
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(pen);
        graphics2D.setColor(color);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.draw(square);

    }

    @Override
    public boolean contains(Point2D p) {
        return square.contains(p);
    }

    public double getLength() {
        return square.getWidth();
    }

    @Override
    public void move(Point2D p1, Point2D p2) {
        Mover.move(p1, p2, points);
        init();
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //System.out.println(len+"-----"+width);
        jTextArea.setBounds((int) points[0].getX()+20,(int) points[0].getY()+20,(int) width-20,(int) len-20);

    }
}
