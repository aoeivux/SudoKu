package com.aoeivux.shuDu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;


public class GamePanel extends JFrame implements ActionListener {
    private JPanel jp;
    private final JTextField jtf1;
    private final JPasswordField jpf1;
    private JLabel jl3;

    ArrayList<String> UserName;
    ArrayList<String> PassWord;
    public  String name = "admin";
    public  String pass = "123456";
    
    private final int FLAG_USERNAME = 0;
    private final int FLAG_PASSWORD = 0;

    private boolean is_Right = false;

    public GamePanel (){
        JPanel jp = new JPanel();
        jp.setLayout(null);
        setBounds(620,350,586,334);

        JLabel jl1 = new JLabel("用户名:");
        jl1.setBounds(110,70,80,26);
        jl1.setFont(new Font("华文新魏",Font.BOLD,20));

        JLabel jl2 = new JLabel("密   码:");
        jl2.setFont(new Font("华文新魏",Font.BOLD,20));
        jl2.setBounds(110,100,80,26);

        jtf1 = new JTextField(15);
        jtf1.setBounds(220,70,150,26);

        jpf1 = new JPasswordField( 20);
        jpf1.setBounds(220,100,150,26);


        //按钮布局
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("清空");
        JButton jb3 = new JButton("注册");

        jb1.setBounds(110,220,100,50);
        jb2.setBounds(370,220,100,50);
        jb3.setBounds(240,220,100,50);


        //监听器
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);

        Menu jm = new Menu("Help");
        jm.addActionListener(this);
        jm.setFont(new Font("华文新魏",Font.PLAIN,16));

        MenuItem jmi1 = new MenuItem("README");
        MenuItem jmi2 = new MenuItem("EXIT");

        //背景图片
        JLabel backGround = new JLabel(new ImageIcon("com/aoeivux/shuDu/backImage/login.jpg"));
        backGround.setBounds(0,0,586,354);

        //逐级添加Menu
        jm.add(jmi1);
        jm.add(jmi2);
        mb.add(jm);


        jp.add(jl1);
        jp.add(jtf1);

        jp.add(jl2);
        jp.add(jpf1);

        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(backGround);

        this.add(jp);
        setVisible(true);
        setResizable(false);
        setTitle("登录界面");


    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (Objects.equals(e.getActionCommand(), "README")) {
            JOptionPane.showMessageDialog(null, "默认用户名:" + "admin\n" + "默认密码:admin");
        }
        else if (Objects.equals(e.getActionCommand(), "EXIT")) {
            System.exit(0);
        }

        //清空按钮的监听器行为
        else if (Objects.equals(e.getActionCommand(), "清空")) {
            jtf1.setText("");
            jpf1.setText("");

        }

        //登录
        else if (Objects.equals(e.getActionCommand(), "确认")) {

            if(this.jtf1.getText().equals(this.name)){
                if (new String(this.jpf1.getPassword()).equals(pass)){
                    JOptionPane.showMessageDialog(null,"登录成功");
                    new GameMain();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "密码错误");
                }
            }else{
                JOptionPane.showMessageDialog(null, "用户名错误");
            }

            jtf1.setText("");
            jpf1.setText("");

        }

        else if (Objects.equals(e.getActionCommand(), "注册")){
            new RegFrame();
        }
    }


}