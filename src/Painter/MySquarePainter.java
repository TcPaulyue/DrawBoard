package Painter;

import Frame.MyActionListener;
import Shapes.MyShape;
import Shapes.MySquare;
import util.Dragger;

import java.awt.event.MouseEvent;

public class MySquarePainter extends MyPainter{
    private MySquare drawingShape;

    @Override
    public MyShape mousePressed(MouseEvent e, MyActionListener myActionListener) {
        points.add(e.getPoint());
        drawingShape = new MySquare();
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
