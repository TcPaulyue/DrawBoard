package Frame;

import Shapes.MyShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyActionListener implements ActionListener {
    public Color color;
    public String shape;
    private MenuPanel menuPanel;
    private ArrayList<MyShape> deletedOnes;
    private ArrayList<JTextArea> deletedTextAreas;
    private MyShape myShape;

    public MyShape getMyShape() {
        return myShape;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("")) {
            JMenuItem item=(JMenuItem)e.getSource();
            this.color=item.getBackground();
            System.out.println("color="+this.color);
        }
        else if(e.getActionCommand().equals("线段")){
            System.out.println("线段");
            this.shape="Line";
            System.out.println(this.shape);
        }
        else if(e.getActionCommand().equals("矩形")){
            this.shape="Rectangle";
            System.out.println(this.shape);
        }
        else if(e.getActionCommand().equals("椭圆形")) {
            this.shape="Ellipse";
            System.out.println(this.shape);
        }
        else if(e.getActionCommand().equals("正方形")){
            this.shape="Square";
            System.out.println(this.shape);
        }
        else if(e.getActionCommand().equals("文本框")){
            this.shape="TextField";
            this.menuPanel.getFrame().drawComponent.setState("TextField");
            System.out.println(this.shape);
        }
       else if(e.getActionCommand().equals("撤销(Ctrl-Z)"))
        {
            System.out.println("撤销");
            this.delelteShapes(this.menuPanel.getFrame().drawComponent.getShapes(),this.deletedOnes);
            //if(this.shape.equals("TextField")) {
                deleteTextAreas(this.menuPanel.getFrame().drawComponent.getjTextAreas(),this.deletedTextAreas);
            //}
           // else {

          //  }
        }
        else if(e.getActionCommand().equals("恢复(Ctrl-Y)")){
            System.out.println("恢复");
           // if(this.shape.equals("TextField")) {
                repaintTextAreas(this.menuPanel.getFrame().drawComponent.getjTextAreas(),this.deletedTextAreas);
           // }
           // else {
                this.repaintShapes(this.menuPanel.getFrame().drawComponent.getShapes(),this.deletedOnes);
           // }
        }
        else if(e.getActionCommand().equals("复制")) {
                this.menuPanel.getFrame().drawComponent.setState("copy");
        }
        else if(e.getActionCommand().equals("粘贴")){
            this.menuPanel.getFrame().drawComponent.setState("paste");
        }
    }
    public MyActionListener(MenuPanel m)
    {
        this.menuPanel=m;
        deletedOnes=new ArrayList();
        deletedTextAreas=new ArrayList<>();
        //undoManager=new UndoManager();
    }
    public void delelteShapes(ArrayList<MyShape> myShapes,ArrayList<MyShape> deletedOnes)
    {
        int i=myShapes.size();
        if(i!=0){
            System.out.println(i);
            deletedOnes.add(myShapes.get(i-1));
            myShapes.remove(i-1);
            this.menuPanel.getFrame().drawComponent.repaint();
        }
    }
    public void deleteTextAreas(ArrayList<JTextArea> jTextAreas,ArrayList<JTextArea> deletedTextAreas)
    {
        int i=jTextAreas.size();
        if(i!=0){
            System.out.println(i);
            deletedTextAreas.add(jTextAreas.get(i-1));
            this.menuPanel.getFrame().drawComponent.remove(jTextAreas.get(i-1));
            jTextAreas.remove(i-1);
            this.menuPanel.getFrame().drawComponent.repaint();
        }
    }
    public void repaintShapes(ArrayList<MyShape> myShapes,ArrayList<MyShape> deletedOnes){
        if (deletedOnes.size()!=0){
            int i=deletedOnes.size();
            myShapes.add(deletedOnes.get(i-1));
            deletedOnes.remove(i-1);
            this.menuPanel.getFrame().drawComponent.repaint();
        }
    }
    public void repaintTextAreas(ArrayList<JTextArea> jTextAreas,ArrayList<JTextArea> deletedTextAreas){
        if (deletedTextAreas.size()!=0){
            int i=deletedTextAreas.size();
            jTextAreas.add(deletedTextAreas.get(i-1));
            this.menuPanel.getFrame().drawComponent.add(deletedTextAreas.get(i-1));
            deletedTextAreas.remove(i-1);
            this.menuPanel.getFrame().drawComponent.repaint();
        }
    }
}
