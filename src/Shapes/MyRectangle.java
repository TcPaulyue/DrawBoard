package Shapes;

import util.Mover;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

public class MyRectangle extends MyShape implements Serializable {
    private Rectangle2D rectangle2D = new Rectangle2D.Double();
    private Point2D[] points = new Point2D[2];

    @Override
    public String getButtonName() {
        return "Rectangle";
    }

    @Override
    public String getClassName() {
        return "MyRectangle";
    }
    public MyShape copy()
    {
        MyRectangle temp = new MyRectangle();
        temp.points[0]=new Point2D.Double(this.points[0].getX(),this.points[0].getY());
        temp.points[1]=new Point2D.Double(this.points[1].getX(),this.points[1].getY());
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        jTextArea.setBounds((int) points[0].getX()+20,(int) points[0].getY()+20,(int) width-20,(int) len-20);
        temp.jTextArea.setText(jTextArea.getText());
        temp.jTextArea.setVisible(true);
        temp.init();
        return temp;
    }
    @Override
    public void init() {
        rectangle2D.setFrameFromDiagonal(points[0], points[1]);
        jTextArea.setBackground(new Color(238,238,238));
        jTextArea.setVisible(true);
        /*
         *setFrameFromDiagonal()是Rectangle2D的超类RectangularShape的公有方法,
         *注释文档云:
         * Sets the diagonal of the framing rectangle of this <code>Shape</code>
         * based on two specified <code>Point2D</code> objects.  The framing
         * rectangle is used by the subclasses of <code>RectangularShape</code>
         * to define their geometry.
         */
    }

    public double getWidth() {
        return rectangle2D.getWidth();
    }

    public double getHeight() {
        return rectangle2D.getHeight();
    }

    @Override
    public void setPoints(Vector<Point2D> p) {
        p.toArray(points);
        double width = Math.abs(points[1].getX()-points[0].getX())-2<0?0:Math.abs(points[1].getX()-points[0].getX())-2;
        double len = Math.abs(points[1].getY()-points[0].getY())-2<0?0:Math.abs(points[1].getY()-points[0].getY())-2;
        //System.out.println(len+"-----"+width);
        jTextArea.setBounds((int) points[0].getX()+20,(int) points[0].getY()+20,(int) width-20,(int) len-20);
        System.out.println(jTextArea.getBounds().x+"++++++ "+jTextArea.getBounds().y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(pen);
        graphics2D.setColor(color);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.draw(rectangle2D);
        System.out.println(jTextArea.getHeight()+"===== "+jTextArea.getWidth());
        System.out.println(getHeight()+"===== "+getWidth());
    }

    @Override
    public boolean contains(Point2D p) {
        return rectangle2D.contains(p);
        //判断点是否在rectangle内
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

/*    @Override
    public double getPointX(){
        return points[0].getX();
    }
    @Override
    public double getPointY(){
        return points[0].getY();
    }*/
}
