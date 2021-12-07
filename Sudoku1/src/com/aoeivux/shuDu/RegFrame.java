package com.aoeivux.shuDu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;


class RegFrame extends JFrame implements ActionListener {
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
            new GamePanel().name = jtf1.getText();
            new GamePanel().pass = new String(this.jpf1.getPassword());
            JOptionPane.showMessageDialog(null,"注册成功！");
            
            this.dispose();
        }
    }




}
