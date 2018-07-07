package Frame;

import javax.swing.*;

import Factory.PainterFactory;
import Shapes.*;
import Painter.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import SaveRead.*;

public class DrawComponent extends JComponent {
    /*
     *JComponent是所有Swing组件的基类,比如JMenuBar(菜单栏)就继承自JComponent.
     */
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 650;
    private DrawFrame drawFrame;
    private ArrayList<MyShape> shapes;
    private ArrayList<JTextArea> jTextAreas;
    private JTextArea jTextArea;
    private MyShape drawingShape;
    public MyShape copyShape;
    private MyShape currentShape = null;
    private MyPainter painter=new MyLinePainter();
    private Point2D[] movePoint = new Point2D[2];
    private String state=new String("null");
    private Point2D point;
    private EditPanel editPanel;
    public void setState(String s)
    {
        state=new String(s);
    }
    public DrawComponent(DrawFrame f) {
        drawFrame=f;
        shapes = new ArrayList<>();
        jTextAreas=new ArrayList<>();
        try {
            shapes = SaveRead.Read();
        } catch (EOFException e) {
            try {
                SaveRead.Save(shapes);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseHandler(this));
        addMouseMotionListener(new MouseMotionHandler());
        /*
         *另一种方法是让DrawComponent实现接口MouseListener和MouseMotionListener,
         *然后在实现接口中的方法时把MouseHandler和MouseMotionHandler里封装的鼠标
         *事件处理代码写到里面去.
         */
    }

    public ArrayList<MyShape> getShapes() {
        return shapes;
    }

    public ArrayList<JTextArea> getjTextAreas() {
        return jTextAreas;
    }

    public void setPainter(String shapeName) {
        String painterName = "Painter.My" + shapeName + "Painter";
        painter = PainterFactory.createPainterInstance(painterName);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (MyShape s : shapes) {
            System.out.println(s.getClassName()+" ");
            s.draw(g);
           // System.out.println(s.jTextArea.getBounds().x);
            this.add(s.jTextArea);
            jTextAreas.add(s.jTextArea);
        }
        System.out.println("\n");
    }
    void clear() {
        shapes.clear();
        repaint();//转向调用paintComponent()
    }

    public MyShape find(Point2D p) {
        for (MyShape s : shapes) {
            if (s.contains(p))
                return s;
        }
        return null;
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // java.awt.Dimension封装了用户界面组件的宽度和高度
    }

    private class MouseHandler extends MouseAdapter {
        public DrawComponent drawComponent;
        public MouseHandler(DrawComponent d) {
            drawComponent=d;
        }
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getButton()==MouseEvent.BUTTON3){
                System.out.println("!!!!!!!!!!!!!!!!");
                currentShape = find(e.getPoint());
                editPanel=new EditPanel(e.getX(),e.getY());
                String s1=editPanel.textField1.getText();
                String s2=editPanel.textField2.getText();
                repaint();
            }
            System.out.println(state);
            if(state.compareTo("copy")==0) {
                copyShape = find(e.getPoint());
                //MyPainter temp = PainterFactory.createPainterInstance(copyShape.getClassName());

                point=e.getPoint();
                state=new String("null");
                System.out.println("copy---------");
            }
            else if(state.compareTo("paste")==0)// if(this.drawComponent.drawFrame.menuPanel.myActionListener.getMyShape()!=null)
            {
               // currentShape=this.drawComponent.drawFrame.menuPanel.myActionListener.getMyShape();
                //currentShape.move(point,e.getPoint());
                shapes.add(copyShape.copy());
                MyShape pasteShape=shapes.get(shapes.size()-1);
                pasteShape.move(point,e.getPoint());
                repaint();
                point=e.getPoint();
                state=new String("null");
                System.out.println("paste---------");
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("press----------");
            if(state.compareTo("copy")==0||state.compareTo("paste")==0)
                return;
            currentShape = find(e.getPoint());
/*            if(this.drawComponent.drawFrame.menuPanel.myActionListener.shape.equals("TextField"))
            {
                jTextArea=new JTextArea();
                jTextArea.setBounds(e.getX(),e.getY(),100,40);
                jTextArea.setBackground(this.drawComponent.drawFrame.menuPanel.myActionListener.color);
                jTextArea.setVisible(true);
                this.drawComponent.add(jTextArea);
                jTextAreas.add(jTextArea);
                repaint();
                return;
            }*/
            if (currentShape == null) {
               String shape=this.drawComponent.drawFrame.menuPanel.myActionListener.shape;
                setPainter(shape);
                drawingShape = painter.mousePressed(e,this.drawComponent.drawFrame.menuPanel.myActionListener);// 返回一个MyShape子类对象
                if (drawingShape != null){
                    shapes.add(drawingShape);
                }
                    /*
                     *将绘制出的对象添加到ArrayList中,调用repaint()时将转向调用
                     *paintComponent(),以此实现drawingShape的重绘.
                     */
                repaint();
            } else {
                setPainter(currentShape.getClassName().toString());
                movePoint[0] = e.getPoint();
            }
            /*
             *如果在图形外面按下鼠标,将进入绘画模式;
             *如果在图形内部按下鼠标,将进入拖动模式.
             */
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            painter.mouseReleased(e);
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape == null) {
                drawingShape = painter.mouseDragged(e);
                repaint();
            } else {
                movePoint[1] = e.getPoint();
                currentShape.move(movePoint[0], movePoint[1]);
                // Pay attention to this statement!
                movePoint[0] = movePoint[1];
                /*
                 *该语句的作用非常重要,缺少之则不能正常移动图形.
                 *该语句使得在拖动鼠标的过程中movePoint[0]和
                 *movePoint[1]始终只相差一个像素单位.
                 *如果存在该语句，鼠标移动N个单位,图形上的点移动N个单位;
                 *没有的话鼠标移动N个单位，图形将移动1+2+...+N个单位.
                 */
                repaint();
            }
        }
    }
}
