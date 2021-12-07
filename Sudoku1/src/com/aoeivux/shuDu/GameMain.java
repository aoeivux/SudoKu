package com.aoeivux.shuDu;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class GameMain extends JFrame implements ActionListener {
    private JLabel lab1,lab2;
    private JButton btn1,btn2,btn3,btn4,btn5;

    //数独游戏中未知的数字
    private int holeNumber=25;
    private String string;
    private JLabel lab4 = new JLabel(new ImageIcon("src/aoeivux/shuDu/backImage/1.jpg"));  //背景图片
    private String wenjian=null;


    public GameMain(){
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setBounds(620,350,586,334);


        btn1=new JButton("easy");
        btn1.setBounds(60,80,130,30);
        btn1.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn1.setFont(new Font("楷体",Font.BOLD,25));
        btn1.addActionListener(this);
        this.add(btn1);

        btn2=new JButton("Normal");
        btn2.setBounds(150,80,130,30);
        btn2.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn2.setFont(new Font("楷体",Font.BOLD,25));
        btn2.addActionListener(this);
        this.add(btn2);

        btn3=new JButton("difficult");
        btn3.setBounds(240,80,130,30);
        btn3.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn3.setFont(new Font("楷体",Font.BOLD,25));
        btn3.addActionListener(this);
        this.add(btn3);

        btn5=new JButton("start");
        btn5.setBounds(290,130,100,80);
        btn5.setFont(new Font("楷体",Font.BOLD,30));
        btn5.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn5.addActionListener(this);
        this.add(btn5);

        lab4.setBounds(0,0,586,334);
        this.add(lab4);

    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==btn1){
            wenjian="E.txt";
            holeNumber=25;
            string="easy";
            btn1.setBackground(Color.gray);
            btn2.setBackground(null);
            btn3.setBackground(null);
        }

        else if(e.getSource()==btn2){
            wenjian="N.txt";
            holeNumber=35;
            string="normal";
            btn2.setBackground(Color.gray);
            btn1.setBackground(null);
            btn3.setBackground(null);
        }

        else if(e.getSource()==btn3){
            wenjian="D.txt";
            holeNumber=45;
            string="difficult";
            btn3.setBackground(Color.gray);
            btn1.setBackground(null);
            btn2.setBackground(null);
        }

        else if(e.getSource()==btn5){
            if(wenjian==null){
                JOptionPane.showMessageDialog(this, "请选择游戏");
            }
            else{
                this.setVisible(false);
                Game gameStart = null;

                try {
                    gameStart = new Game(holeNumber,string,wenjian);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                assert gameStart != null;
                gameStart.setVisible(true);
            }
        }
    }

}