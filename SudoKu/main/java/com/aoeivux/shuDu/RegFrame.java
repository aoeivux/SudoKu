package com.aoeivux.shuDu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.aoeivux.shuDu.JdbcDemo.sqlAdd;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/5 9:50
 */
class RegFrame extends JFrame implements ActionListener, KeyListener {
    JTextField jtf1 = null;
    JPasswordField jpf1 = null;


    public RegFrame(){
        this.setTitle("注册界面");
        this.setVisible(true);
        JPanel jp = new JPanel();
        jp.setLayout(null);

        setBounds(640,350,586,334);

        JLabel jl1 = new JLabel("用户名:");
        jl1.setBounds(110,70,80,26);
        jl1.setFont(new Font("华文新魏",Font.BOLD,20));

        JLabel jl2 = new JLabel("密   码:");
        jl2.setFont(new Font("华文新魏",Font.BOLD,20));
        jl2.setBounds(110,100,80,26);

        jtf1 = new JTextField(15);
        jtf1.setBounds(220,70,150,26);

        jpf1 = new JPasswordField(20);
        jpf1.setBounds(220,100,150,26);


        //回车监听事件，通过Main类实现接口多继承
        jpf1.addKeyListener(this);
        jtf1.addKeyListener(this);

        //按钮布局
        JButton jb3 = new JButton("注册");
        jb3.addActionListener(this);

        jb3.setBounds(240,220,100,50);

        jp.add(jl1);
        jp.add(jtf1);

        jp.add(jl2);
        jp.add(jpf1);

        jp.add(jb3);


        this.add(jp);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("注册")){
//            sqlAdd = "INSERT INTO manage(username,password) VALUES ("+this.jtf1.getText()+","+new String(this.jpf1.getPassword())+");";
            sqlAdd = "insert into manage(username,password) values('"+this.jtf1.getText()+"','"+new String(this.jpf1.getPassword())+"')";
            System.out.println(sqlAdd);
            new JdbcDemo("add");
            JOptionPane.showMessageDialog(null,"注册成功！");
            this.dispose();
        }
    }


    //实现和鼠标一样的，用回车的功能
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            sqlAdd = "insert into manage(username,password) values('"+this.jtf1.getText()+"','"+new String(this.jpf1.getPassword())+"')";
            System.out.println(sqlAdd);
            new JdbcDemo("add");
            JOptionPane.showMessageDialog(null,"注册成功！");
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }



//    public static void main(String[] args) {
//        new RegFrame();
//    }

}
