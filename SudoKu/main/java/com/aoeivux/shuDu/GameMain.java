package com.aoeivux.shuDu;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/2 10:27
 */


class GameMain extends JFrame implements ActionListener {
    private JLabel lab1,lab2;
    private JButton btn1,btn2,btn3,btn4,btn5;

    //数独游戏中未知的数字
    private int holeNumber=25;
    private String string;
    private JLabel lab4 = new JLabel(new ImageIcon("G:\\ProjectsAll\\SuperMario\\src\\main\\java\\com\\aoeivux\\shuDu\\staticData\\2.jpg"));  //背景图片
    private String wenjian=null;


    public GameMain(){
        setVisible(true);
        setLayout(null);
        setTitle("游戏难度选择...");
        setResizable(false);
        setBounds(620,350,586,334);


        lab1=new JLabel("主菜单:");
        lab1.setBounds(80,14,130,40);
        lab1.setFont(new Font("华文新魏",Font.BOLD,30));
        lab1.setForeground(Color.WHITE);

        this.add(lab1);

        lab2=new JLabel("选项:");
        lab2.setFont(new Font("华文新魏",Font.BOLD,25));
        lab2.setBounds(30,42,80,40);
        this.add(lab2);
        lab2.setForeground(Color.WHITE);


        btn1=new JButton("容易模式");
        btn1.setBounds(60,80,130,30);
        btn1.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn1.setFont(new Font("华文新魏",Font.BOLD,25));
        btn1.addActionListener(this);
        this.add(btn1);

        btn2=new JButton("一般模式");
        btn2.setBounds(60,130,130,30);
        btn2.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn2.setFont(new Font("华文新魏",Font.BOLD,25));
        btn2.addActionListener(this);
        this.add(btn2);

        btn3=new JButton("困难模式");
        btn3.setBounds(60,180,130,30);
        btn3.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn3.setFont(new Font("华文新魏",Font.BOLD,25));
        btn3.addActionListener(this);
        this.add(btn3);

        btn5=new JButton("开始");
        btn5.setBounds(290,130,100,80);
        btn5.setFont(new Font("华文新魏",Font.BOLD,30));
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
            string="容易模式";
            btn1.setBackground(Color.gray);
            btn2.setBackground(null);
            btn3.setBackground(null);
        }

        else if(e.getSource()==btn2){
            wenjian="N.txt";
            holeNumber=35;
            string="一般模式";
            btn2.setBackground(Color.gray);
            btn1.setBackground(null);
            btn3.setBackground(null);
        }

        else if(e.getSource()==btn3){
            wenjian="D.txt";
            holeNumber=45;
            string="困难模式";
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