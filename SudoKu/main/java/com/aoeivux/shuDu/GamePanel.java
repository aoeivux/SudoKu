package com.aoeivux.shuDu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


public class GamePanel extends JFrame implements ActionListener, MouseListener,KeyListener {
    private JPanel jp;
    private final JTextField jtf1;
    private final JPasswordField jpf1;
    private JLabel jl3;

    ArrayList<String> UserName;
    ArrayList<String> PassWord;

    private final int FLAG_USERNAME = 0;
    private final int FLAG_PASSWORD = 0;

    private boolean is_Right = false;

    public GamePanel (){
        JPanel jp = new JPanel();
        jp.setLayout(null);
        setBounds(620,350,586,334);
        jp.addMouseListener(this);
        ImageIcon image = new ImageIcon("G:\\ProjectsAll\\SuperMario\\src\\main\\java\\com\\aoeivux\\shuDu\\staticData\\1.jpg");
        this.setIconImage(image.getImage());

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
        this.addKeyListener(this);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);

        Menu jm = new Menu("Help");
        jm.addActionListener(this);
        jm.setFont(new Font("华文新魏",Font.PLAIN,16));

        MenuItem jmi1 = new MenuItem("README");
        MenuItem jmi2 = new MenuItem("EXIT");

        //背景图片
        JLabel backGround = new JLabel(new ImageIcon("G:\\ProjectsAll\\SuperMario\\src\\main\\java\\com\\aoeivux\\shuDu\\staticData\\login.jpg"));
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
//      setDefaultCloseOperation();
        setResizable(false);
        setTitle("登录界面");
        this.UserName = new JdbcDemo().UserName;
        this.PassWord = new JdbcDemo().PassWord;

        //监听回车事件的第一种写法
        jpf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    String passwd = new String(jpf1.getPassword());

                    String userName = jtf1.getText();


                    if(new JdbcDemo().UserName.contains(userName)){
                        int len = new JdbcDemo().PassWord.size();

                        for (int i = 0; i < len; i++) {
                            if (new JdbcDemo().PassWord.get(i).equals(passwd)){
                                JOptionPane.showMessageDialog(null,"密码正确");
                                is_Right = true;
                                dispose();
                                new GameMain();
                                break;
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名错误，请重试");
                    }


                    if (!is_Right)
                        JOptionPane.showMessageDialog(null,"密码错误，请重试");

                    jtf1.setText("");
                    jpf1.setText("");

                }
            }
        });

        //监听回车事件的第二种写法
        jtf1.addKeyListener(this);
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


            //确认按钮的监听器行为
            //对用户名和密码进行比对
        }

        //登录
        else if (Objects.equals(e.getActionCommand(), "确认")) {


            String passwd = new String(this.jpf1.getPassword());

            String userName = this.jtf1.getText();


            if(new JdbcDemo().UserName.contains(userName)){
                int len = new JdbcDemo().PassWord.size();

                for (int i = 0; i < len; i++) {
                    if (new JdbcDemo().PassWord.get(i).equals(passwd)){
                        JOptionPane.showMessageDialog(null,"密码正确");
                        is_Right = true;
                        this.dispose();
                        new GameMain();
                        break;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"用户名错误，请重试");
            }


            if (!is_Right)
                JOptionPane.showMessageDialog(null,"密码错误，请重试");



            jtf1.setText("");
            jpf1.setText("");

        }

        else if (Objects.equals(e.getActionCommand(), "注册")){
            new RegFrame();
//            add.sqlAdd = "INSERT INTO manage (UserName, PassWord) VALUES "+;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

//        String passwd = new String(this.jpf1.getPassword());
//
//        String userName = this.jtf1.getText();
//
//
//        if(new JdbcDemo().UserName.contains(userName)){
//            int len = new JdbcDemo().PassWord.size();
//
//            for (int i = 0; i < len; i++) {
//                if (new JdbcDemo().PassWord.get(i).equals(passwd)){
//                    JOptionPane.showMessageDialog(null,"密码正确");
//                    is_Right = true;
//                    this.dispose();
//                    new GameMain();
//                    break;
//                }
//            }
//        }else{
//            JOptionPane.showMessageDialog(null,"用户名错误，请重试");
//        }
//
//
//        if (!is_Right)
//            JOptionPane.showMessageDialog(null,"密码错误，请重试");
//
//
//
//        jtf1.setText("");
//        jpf1.setText("");

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}