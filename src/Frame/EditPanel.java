package Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditPanel extends JFrame {
    public JTextField textField1;
    public JTextField textField2;
    public EditPanel(int x,int y){
        this.setTitle("变量选择");
        this.setBounds(x,y,250,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3,1,5,5));
        JPanel pane1=new JPanel();
        contentPane.add(pane1);
        JPanel pane2=new JPanel();
        contentPane.add(pane2);
        JPanel pane3=new JPanel();
        contentPane.add(pane3);
        JLabel label1=new JLabel("长度：");
        textField1=new JTextField();
        textField1.setColumns(20);
        pane1.add(label1);
        pane1.add(textField1);
        JLabel label2=new JLabel("宽度：");
        textField2=new JTextField();
        textField2.setColumns(20);
        pane2.add(label2);
        pane2.add(textField2);
        this.setVisible(true);
    }
}
