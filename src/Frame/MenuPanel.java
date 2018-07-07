package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuPanel extends JMenuBar {
    JMenu menuShape;
    JMenu menuFile;
    JMenu menuEdit;
    JMenu menuColor;

    JMenuItem itemLine;
    JMenuItem itemRect;
    JMenuItem itemRoundRect;
    JMenuItem itemOval;
   // JMenuItem itemTextField;

    JMenuItem itemNew;
    JMenuItem itemSave;
    JMenuItem itemOpen;

    JMenuItem itemCopy;
    JMenuItem itemZhantie;
    JMenuItem itemUndo;
    JMenuItem itemRedo;
    private DrawFrame frame;
    ArrayList<JMenuItem> jMenuItems;
    public MyActionListener myActionListener;
    public MenuPanel(DrawFrame f) {
        myActionListener=new MyActionListener(this);
        frame=f;
        initMenuFile();
        initMenuSharp();
        initMenuEdit();
        initMenuColor();
    }

    public DrawFrame getFrame() {
        return frame;
    }
    void initMenuColor()
    {
        menuColor=new JMenu("颜色");
        jMenuItems=new ArrayList<JMenuItem>();
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Color [] color={Color.BLACK,Color.blue,Color.white,Color.gray,Color.red,Color.CYAN,Color.green,Color.darkGray,Color.pink};
        for(int i=0;i<color.length;i++){
            JMenuItem item=new JMenuItem();
            item.addActionListener(myActionListener);   //添加事件监听机制
            //item.setPreferredSize(new Dimension(30,30));
            item.setBackground(color[i]);
            jMenuItems.add(item);
        }
        for(JMenuItem item:jMenuItems)
            menuColor.add(item);
        this.add(menuColor);
    }
    void initMenuFile()
    {
        //menuBar = new JMenuBar();
        menuFile=new JMenu("文件(F)");
        menuFile.setMnemonic('F');
        itemNew=new JMenuItem("新建");
        itemOpen=new JMenuItem("打开");
        itemSave=new JMenuItem("保存");
        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        this.add(menuFile);
        //设置菜单的键盘操作方式是Alt + F键

        //itemSave = new JMenuItem("保存(S)");

        //设置菜单项的键盘操作方式是Ctrl+O和Ctrl+S键
/*        KeyStroke Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
        itemLine.setAccelerator(Ctrl_cutKey);
        Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
        itemSave.setAccelerator(Ctrl_cutKey);*/
        //menuFile.addSeparator();
        //menuFile.add(itemSave);
        //将菜单添加到菜单条上
        //setJMenuBar(menuBar);
    }
    void initMenuSharp()
    {
        menuShape = new JMenu("绘图(D)");
        menuShape.setMnemonic('D');
        itemLine = new JMenuItem("线段");
        itemLine.addActionListener(myActionListener);

        itemRect =new JMenuItem("矩形");
        itemRect.addActionListener(myActionListener);

        itemOval =new JMenuItem("椭圆形");
        itemOval.addActionListener(myActionListener);

        itemRoundRect =new JMenuItem("正方形");
        itemRoundRect.addActionListener(myActionListener);

        /*itemTextField=new JMenuItem("文本框");
        itemTextField.addActionListener(myActionListener);*/
        menuShape.add(itemLine);
        menuShape.add(itemRect);
        menuShape.add(itemOval);
        menuShape.add(itemRoundRect);
       // menuShape.add(itemTextField);

        this.add(menuShape);
    }
    void initMenuEdit()
    {
        menuEdit=new JMenu("编辑(E)");
        menuEdit.setMnemonic('E');
        itemCopy=new JMenuItem("复制");
        KeyStroke Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK);
        itemCopy.setAccelerator(Ctrl_cutKey);
        itemCopy.addActionListener(myActionListener);
        itemZhantie=new JMenuItem("粘贴");
        Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK);
        itemZhantie.setAccelerator(Ctrl_cutKey);
        itemZhantie.addActionListener(myActionListener);
        itemUndo=new JMenuItem("撤销(Ctrl-Z)");
        Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK);
        itemUndo.setAccelerator(Ctrl_cutKey);
        itemUndo.addActionListener(myActionListener);
        itemRedo=new JMenuItem("恢复(Ctrl-Y)");
        Ctrl_cutKey =
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK);
        itemRedo.setAccelerator(Ctrl_cutKey);
        itemRedo.addActionListener(myActionListener);
        menuEdit.add(itemCopy);
        menuEdit.add(itemZhantie);
        menuEdit.add(itemUndo);
        menuEdit.add(itemRedo);
        this.add(menuEdit);
    }
}
