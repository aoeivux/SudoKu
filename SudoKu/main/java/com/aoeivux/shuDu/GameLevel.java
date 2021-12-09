//package com.aoeivux.shuDu;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.util.Vector;
//
//
///***************关卡解锁****************/
//class GameLevel extends JFrame implements ActionListener {
//    private JButton buttons[]=new JButton[16];
//    private JPanel panel3;
//
//    Vector<JButton> jb=new Vector<JButton>(16);
//    private int holeNumber;
//    private String string;
//    private String wenjian;
//    private File file;
//    private FileReader fr;
//    private FileWriter fw;
//    private int txtread;
//    private int k;//判断文件长度是否为空
//    private final JLabel backGround = new JLabel(new ImageIcon("src/com/aoeivuvx/staticData/23.jpg"));
//
//
//    public GameLevel(){}
//    public GameLevel(int holeNumber,String string,String wenjian){
//
//        super("数独游戏");
//        this.setBounds(0,0,400,400);
//        this.setLocationRelativeTo(null);
//        backGround.setBounds(0,0,400,400);
//        this.add(backGround);
//
//
//        panel3=new JPanel();
//        panel3.setLayout(null);
//        panel3.setLayout(new GridLayout(4,4,5,5));
//        for(int i=0;i<16;i++){
//            buttons[i]=new JButton("第"+(i+1)+"关");
//            buttons[i].setFont(new Font("华文新魏",Font.PLAIN|Font.BOLD,18));
//            buttons[i].addActionListener(this);
//            panel3.add(buttons[i]);
//            buttons[i].setEnabled(false);
//            jb.add(buttons[i]);
//        }
//        this.add(panel3);
//        file=new File("src/com/aoeivux/shuDu/file/"+wenjian);
//        try{
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            char[] data = new char[20];
//
//            fr = new FileReader(file);
//
//            int length = fr.read(data);
//
//            if(length == -1){    //文件为空，k赋予值0
//                k=0;
//            }
//            else{
//                k=1;
//                String str=new String(data,0,length);
//                txtread=Integer.parseInt(str);
//                fr.close();
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        if(k==0){
//            jb.elementAt(0).setEnabled(true);
//            try{
//                fw=new FileWriter(file);
//                String str="1";
//                fw.write(str);
//                fw.close();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        else{
//            for(int i=0;i<txtread;i++){
//                jb.elementAt(i).setEnabled(true);
//            }
//        }
//        this.holeNumber=holeNumber;
//        this.string=string;
//        this.wenjian=wenjian;
//    }
//
//    public void actionPerformed(ActionEvent e){
//        for(int i=0;i<16;i++){
//            //
//            if(e.getSource()==jb.elementAt(i)){
//                this.setVisible(false);
//                Game G = null;
//                try {
//                    G = new Game(holeNumber,string,wenjian);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                G.setVisible(true);
//                G.setResizable(false);
//                G.setTitle("数独游戏");
//                G.setLocationRelativeTo(null);
//            }
//        }
//    }
//}
//
