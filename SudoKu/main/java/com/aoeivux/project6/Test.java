package com.aoeivux.project6;

import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/2 9:56
 */


class Test extends JFrame implements ActionListener {
    private final JTextField jtf1;
    private final JPasswordField jpf1;




    public Test(){

        setLayout(null);
        setVisible(true);
        setBounds(640,350,586,334);
        setTitle("系统登陆界面");
        setResizable(false);

        JLabel jl1 = new JLabel("用户名:");
        JLabel jl2 = new JLabel("密码:");
        jtf1 = new JTextField(15);
        jpf1 = new JPasswordField(15);

        jl1.setBounds(110,70,80,26);
        jl2.setBounds(110,100,80,26);
        jtf1.setBounds(220,70,150,26);
        jpf1.setBounds(220,100,150,26);

        //登录按钮
        JButton jb1 = new JButton("登录");
        //退出按钮
        JButton jb2 = new JButton("退出");
//
        jb1.setBounds(110,220,100,50);
        jb2.setBounds(373,220,100,50);

        jb1.addActionListener(this);
        jb2.addActionListener(this);


        this.add(jl1);
        this.add(jtf1);

        this.add(jl2);
        this.add(jpf1);

        this.add(jb1);
        this.add(jb2);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        java.lang.String userName = new java.lang.String(this.jtf1.getText());
        java.lang.String passwd = new java.lang.String(this.jpf1.getPassword());

        if(e.getActionCommand().equals("登录")){
            if(userName.equals("admin")){
                if(passwd.equals("123456")){
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "密码错误");

                    //清空并返回上一个窗口
                    this.jtf1.setText("");
                    this.jpf1.setText("");
                }
            }else {
                JOptionPane.showMessageDialog(null, "用户名错误");
                this.jtf1.setText("");
                this.jpf1.setText("");
            }
        }else if(e.getActionCommand().equals("退出")){
//            System.exit(0);
            dispose();
        }
    }



}
