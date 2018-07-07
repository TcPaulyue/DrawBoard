package Painter;

import Frame.MyActionListener;
import Shapes.MyEllipse;
import Shapes.MyShape;
import util.Dragger;

import java.awt.event.MouseEvent;

public class MyEllipsePainter extends MyPainter {
    private MyEllipse drawingShape;

    @Override
    public MyShape mousePressed(MouseEvent e, MyActionListener myActionListener) {
        points.add(e.getPoint());
        drawingShape = new MyEllipse();
        drawingShape.color=myActionListener.color;
        drawingShape.shape=myActionListener.shape;
        return drawingShape;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        points.clear();
    }

    @Override
    public MyShape mouseDragged(MouseEvent e) {
        Dragger.drag(e, points, drawingShape);
        return drawingShape;
    }
}