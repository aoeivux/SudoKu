package com.aoeivux.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/****
 *
 * 点击完确认鼠标立即移走，否则卡死
 *
 */
class ShuDuGame extends JFrame implements ActionListener,MouseListener{
    private JLabel Lab1;
    private JLabel lab;
    private JLabel lab2;
    private JLabel lab3;
    private JTextField T1,T2;
    private JButton B1,B2;
    private JPanel p;
    private ImageIcon pic=new ImageIcon("src/img/2.jpg");
    private MenuBar menubar;         //src
    //字体  setForeground(Color.red);(颜色)

    /*****************登录界面****************/
    public void init(){
        p=new JPanel();
        p.setLayout(null);//重中之重
        /*this 调用JFrame的构造方法*/
        this.setBounds(450,200,340,250);//x,y,w,h JFrame下的JPanel

        Lab1=new JLabel("用户名:");
        Lab1.setFont(new Font("华文新魏",Font.BOLD,20));
        Lab1.setBounds(20,30,80,26);p.add(Lab1);
        T1=new JTextField(15);
        T1.setBounds(100,30,180,30);p.add(T1);
        JLabel lab21 = new JLabel("密码:");
        lab21.setFont(new Font("华文新魏",Font.BOLD,20));
        p.add(lab21);
        lab21.setBounds(40,70,80,26);
        T2=new JTextField(15);
        T2.setBounds(100,70,180,30);p.add(T2);
        B1=new JButton("取消");
        B1.setBounds(80,150,60,30);B1.addActionListener(this);
        this.add(B1);
        B2=new JButton("确定");
        B2.setBounds(220,150,60,30);
        B2.addActionListener(this);
        this.add(B2);

        /*******控件在越前面就越先出现**********
         ****lab3在lab2之前，所以lab3不会被图片遮住****/
        lab3=new JLabel("用户名或密码错误，请重新登录！");
        lab3.setBounds(100,10,250,15);

        p.add(lab3);
        lab2=new JLabel(new ImageIcon("src/img/3.jpg"));
        lab2.setBounds(0,0,340,250);
        p.add(lab2);

        menubar=new MenuBar();
        setMenuBar(menubar);
        Menu menu=new Menu("帮助");
        menu.setFont(new Font("华文新魏",Font.PLAIN,10));
        MenuItem mi1=new MenuItem("读我");
        MenuItem mi2=new MenuItem("退出");
        menu.addActionListener(this);
        menu.add(mi1);menu.add(mi2);
        menubar.add(menu);

        lab3.setVisible(false);
        this.setResizable(false);//固定大小位置，不可拖动
        this.setTitle("登录界面");

        //登录界面头像
        this.setIconImage(pic.getImage());

        this.add(p);
        this.setVisible(true);
        p.addMouseListener(this);
    }

    public static void main(String[] args){
        new ShuDuGame().init();
    }
    /**************死循环画图*************
     public void paint(Graphics g) {
     super.paint(g);
     pic=new ImageIcon("bin/img/1.jpg");
     g.drawImage(pic.getImage(), 0,190,400,60,this);
     }
     **********************************/
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="读我"){
            JOptionPane.showMessageDialog(null, "用户名：1\n"+"密码：1");

        }
        else if(e.getActionCommand()=="退出"){
            this.setVisible(false);
        }
        else if(e.getSource()==B1){
            T1.setText("");
            T2.setText("");
            lab3.setVisible(false);
        }
        else if(e.getSource()==B2){
            if(T1.getText().equals("1")&&T2.getText().equals("1")){
                progressbar pg=new progressbar();
                pg.setVisible(true);
                pg.setResizable(false);
                this.setVisible(false);
            }
            else{
                lab3.setVisible(true);
                T1.setText("");
                T2.setText("");
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==p){
            lab3.setVisible(false);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}


/***************关卡解锁****************/
class Rank extends JFrame implements ActionListener{
    private JButton buttons[]=new JButton[16];
    private JPanel panel3;
    Vector<JButton> jb=new Vector<JButton>(16);
    private int holeNumber;
    private String string;
    private String wenjian;
    private File file;
    private FileReader fr;
    private FileWriter fw;
    private int txtread;
    private int k;//判断文件长度是否为空
    public Rank(){

    }
    public Rank(int holeNumber,String string,String wenjian){
        super("数独游戏");
        this.setBounds(450,150,400,400);
        panel3=new JPanel();
        panel3.setLayout(null);
        panel3.setLayout(new GridLayout(4,4,5,5));
        for(int i=0;i<16;i++){
            buttons[i]=new JButton("第"+(i+1)+"关");
            buttons[i].setFont(new Font("华文新魏",Font.PLAIN|Font.BOLD,18));
            buttons[i].addActionListener(this);
            panel3.add(buttons[i]);
            buttons[i].setEnabled(false);
            jb.add(buttons[i]);
        }
        this.add(panel3);
        file=new File("D:/JAVA/javaproject/Game/src/rank/"+wenjian);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            char[] data=new char[20];
            fr=new FileReader(file);
            int length=fr.read(data);
            if(length==-1){    //文件为空，k赋予值0
                k=0;
            }
            else{
                k=1;
                String str=new String(data,0,length);
                txtread=Integer.parseInt(str);
                fr.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        if(k==0){
            jb.elementAt(0).setEnabled(true);
            try{
                fw=new FileWriter(file);
                String str="1";
                fw.write(str);
                fw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            for(int i=0;i<txtread;i++){
                jb.elementAt(i).setEnabled(true);
            }
        }
        this.holeNumber=holeNumber;
        this.string=string;
        this.wenjian=wenjian;
    }

    public void actionPerformed(ActionEvent e){
        for(int i=0;i<16;i++){
            if(e.getSource()==jb.elementAt(i)){
                this.setVisible(false);
                Game2 frame = new Game2(holeNumber,string,wenjian);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setTitle("数独游戏");
            }
        }
    }
}

/****************主菜单等级*************/
class mainmenu extends JFrame implements ActionListener{
    private JLabel lab1,lab2;
    private JButton btn1,btn2,btn3,btn4,btn5;

    private int holeNumber=25;//等级默认为简单
    private String string;
    private JLabel lab4=new JLabel(new ImageIcon("bin/img/1.jpg"));
    private String wenjian=null;                //src
    public mainmenu(){
        this.setLayout(null);
        this.setBounds(450,150,450,400);
        lab1=new JLabel("主菜单:");
        lab1.setBounds(80,50,130,40);
        lab1.setFont(new Font("华文隶书",Font.BOLD,30));
        this.add(lab1);
        lab2=new JLabel("选项:");
        lab2.setFont(new Font("华文隶书",Font.BOLD,25));
        lab2.setBounds(30,100,80,40);
        this.add(lab2);
        btn1=new JButton("Easy");
        btn1.setBounds(60,140,130,30);
        btn1.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn1.setFont(new Font("华文隶书",Font.BOLD,25));
        btn1.addActionListener(this);
        this.add(btn1);
        btn2=new JButton("Normal");
        btn2.setBounds(60,180,130,30);
        btn2.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn2.setFont(new Font("华文隶书",Font.BOLD,25));
        btn2.addActionListener(this);
        this.add(btn2);
        btn3=new JButton("Difficult");
        btn3.setBounds(60,220,130,30);
        btn3.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn3.setFont(new Font("华文隶书",Font.BOLD,25));
        btn3.addActionListener(this);
        this.add(btn3);
        btn4=new JButton("Config");
        btn4.setBounds(60,260,130,30);
        btn4.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn4.setFont(new Font("华文隶书",Font.BOLD,25));
        btn4.addActionListener(this);
        this.add(btn4);
        btn5=new JButton("Start");
        btn5.setBounds(240,130,100,60);
        btn5.setFont(new Font("华文隶书",Font.BOLD,30));
        btn5.setBorder(new BevelBorder(BevelBorder.RAISED));
        btn5.addActionListener(this);
        this.add(btn5);
        lab4.setBounds(0,0,450,400);
        this.add(lab4);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==btn1){
            wenjian="E.txt";
            holeNumber=25;
            string="Easy";
            btn1.setBackground(Color.gray);
            btn2.setBackground(null);
            btn3.setBackground(null);
        }
        else if(e.getSource()==btn2){
            wenjian="N.txt";
            holeNumber=35;
            string="Normol";
            btn2.setBackground(Color.gray);
            btn1.setBackground(null);
            btn3.setBackground(null);
        }
        else if(e.getSource()==btn3){
            wenjian="D.txt";
            holeNumber=45;
            string="Difficult";
            btn3.setBackground(Color.gray);
            btn1.setBackground(null);
            btn2.setBackground(null);
        }
        else if(e.getSource()==btn5){
            if(wenjian==null){
                JOptionPane.showMessageDialog(this, "选择等级才能开始游戏哦！");
            }                              //当前组件
            else{
                this.setVisible(false);
                Rank rank=new Rank(holeNumber,string,wenjian);
                rank.setVisible(true);
            }
        }
        else if(e.getSource()==btn4){
            btn2.setBackground(null);
            btn1.setBackground(null);
            btn3.setBackground(null);
        }

    }

}


/*****************游戏界面***************/
class Game2 extends JFrame implements ActionListener{

    private JPanel contentPane;
    private Vector<JButton> buttonsVector; //存放81个按钮    Vector<E>
    private Vector<JButton> numberButtonVector; //存放9个数字键

    private JButton choosedButton; //重新开始按钮 //当前被选中的按钮，表示将要被修改
    private GameCore core; //游戏内核，含有游戏算法
    private int[][] backup; //用来备份游戏，用于清除后重新导入
    private int T,M,S;//计时器

    private int holenumber;
    private String string;
    private String wenjian;

    private JPanel panel_2;
    private JButton[] btnButton=new JButton[81];
    private JButton btnButton1,btnButton2;
    private JLabel lab1;
    private Thread thread;
    private JMenu mnNewMenu1,mnNewMenu2 ;
    private File file;
    private File fileGrades;
    private FileReader fr;//读出文件txt的数据（字符串）
    private FileWriter fw;//将数据（字符串）写到文件中，方便调用
    private int txtread;

    private int Grades;

    private Color[] colors={
            Color.green,
            Color.white,
            Color.yellow,

    };

    /**
     * Create the frame.
     */

    public Game2(int holeNumber,String string,String wenjian) {

        this.holenumber=holeNumber;
        this.string=string;
        this.wenjian=wenjian;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 20, 600, 700);

        ImageIcon picture2=new ImageIcon("bin/img/1.jpg");
        this.setIconImage(picture2.getImage());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        /***************计时框架**************/
        mnNewMenu1 = new JMenu("***  计时:");
        mnNewMenu1.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        menuBar.add(mnNewMenu1);
        mnNewMenu2 = new JMenu("00:00  ***");
        mnNewMenu2.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        menuBar.add(mnNewMenu2);

        contentPane = new JPanel();//大容器

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//页边空白
        setContentPane(contentPane);
        setLayout(null);

        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();//小容器1
        contentPane.add(panel, BorderLayout.SOUTH);//上北下南
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //hgap  vgap

        String[] j={"1","2","3","4","5","6","7","8","9"};

        /*****最下面一行按钮******/
        JButton button[]=new JButton[9];
        for(int i=0;i<9;i++){
            button[i] = new JButton(j[i]);
            button[i].setFont(new Font("宋体", Font.PLAIN, 20));
            //字体大小
            panel.add(button[i]);
        }


        JPanel panel_1 = new JPanel();//小容器2
        panel_1.setLayout(null);

        contentPane.add(panel_1, BorderLayout.NORTH);
        panel_1.setBounds(0, 0, 600, 40);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnButton1=new JButton("返回主菜单");
        btnButton1.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        btnButton1.addActionListener(this);
        panel_1.add(btnButton1);

        btnButton2=new JButton("开始游戏");
        btnButton2.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        btnButton2.addActionListener(this);
        panel_1.add(btnButton2);

        JButton btnNewButton_1 = new JButton("清除已填");
        btnNewButton_1.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {//清除所有按钮监听器
                for (int i = 0; i < 9; i ++) {
                    for (int j = 0; j < 9; j ++) {
                        core.test[i][j] = backup[i][j];
                    }
                }
                initGame();

            }
        });


        panel_1.add(btnNewButton_1);
        lab1=new JLabel("等级："+string);
        lab1.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        panel_1.add(lab1);

        panel_1.setVisible(true);

        panel_2 = new JPanel();//小容器3
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(9, 9, 5, 5));//水平与垂直间隔5像素
//                                     rows columns hgap vgap

        for(int i=0;i<81;i++){
            btnButton[i] = new JButton("");
            int x = i/ 9;   //行
            int y = i % 9;  //列
            if((x/3==0&&y/3==0)||(x/6==1&&y/3==0)||(y/6==1&&x/3==0)||(x/6==1&&y/6==1)){
                btnButton[i].setBackground(colors[2]);
            }
            else if(x/3==1&&y/3==1){
                btnButton[i].setBackground(colors[1]);
            }
            else{
                btnButton[i].setBackground(colors[0]);
            }
            btnButton[i].setBorder(new BevelBorder(BevelBorder.RAISED));//立体感
            panel_2.add(btnButton[i]);
        }
        panel_2.setVisible(false);

        /***************向量按钮************/
        buttonsVector = new Vector<JButton>(81); //存放九宫格81个按钮
        numberButtonVector = new Vector<JButton>(9); //存放9个数字键

        backup = new int[9][9]; //题目的备份

        //把81个格子放入容器中
        for(int i=0;i<81;i++){
            buttonsVector.add(btnButton[i]);//向量
        }

        for(int i=0;i<9;i++){
            numberButtonVector.add(button[i]);
        }


        core = new GameCore(holeNumber);
        //备份一份题目
        backUpProblem();
        initGame();


        //为81个按钮添加监视器
        for (int i = 0; i < 81; i ++) {
            buttonsVector.elementAt(i).addActionListener(this);
        }
        //为9个数字键添加监视器
        for (int i = 0; i < 9; i ++) {
            button[i].addActionListener(this);
        }


    }

    //监视器
    public void actionPerformed(ActionEvent e) {

        //控制81个显示按钮
        JButton button = null;

        for (int i = 0; i < 81; i ++) {
            if (e.getSource() == buttonsVector.elementAt(i)) {
                button = buttonsVector.elementAt(i);
                if (button.getText()==null) {
                    if (choosedButton != null) {
                        setButtonColor(choosedButton);
                    }
                    /*监听事件，选定空按钮*/	choosedButton = buttonsVector.elementAt(i);
                    buttonsVector.elementAt(i).setBackground(Color.LIGHT_GRAY);
                }
            }
        }
        if(e.getSource()==btnButton1){
            this.setVisible(false);
            new mainmenu().setVisible(true);
        }

        if(e.getSource()==btnButton2){
            panel_2.setVisible(true);
            thread=new Thread(new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(1000);
                            T++;
                            M=T/60;
                            S=T%60;
                            mnNewMenu2.setText(M+":"+S+"  ***");
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }
                    }
                }
            }); //计时
            thread.start();

        }


        //控制9个数字键按钮
        String numberStr;
        int num, index, x, y;;


        /********手动填入数字*************/
        for (int i = 0; i < 9; i ++) {
            if (e.getSource() ==numberButtonVector.elementAt(i)) {
                numberStr = numberButtonVector.elementAt(i).getText().trim();
                num = Integer.parseInt(numberStr);
                if ( isLegal(num) ) {
                    choosedButton.setText(numberStr);  //如果符合规则则填入所选数字
                    choosedButton.setForeground(Color.BLACK);
                    //修改Test布局表
                    index = buttonsVector.indexOf(choosedButton);
                    x = index / 9;
                    y = index % 9;
                    core.test[x][y] = num;//备份

                    //判读是否成功了
                    if (isFinish()) {
                        thread.stop();
                        JOptionPane.showMessageDialog(null, "用时"+M+"分"+S+"秒   得分："+(1000-T)+"分\n"
                                + "好厉害，快点解锁下一关吧");
                        this.setVisible(false);

                        file=new File("D:/JAVA/javaproject/Game/src/rank/"+wenjian);//绝对路径
                        Rank ran=new Rank(holenumber,string,wenjian);
                        try{
                            char[] data=new char[20];
                            fr=new FileReader(file);
                            int length=fr.read(data);
                            String str=new String(data,0,length);//读取文件file内容
                            txtread=Integer.parseInt(str);
                            fr.close();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }

                        /**************分数存储*************/
                        fileGrades=new File("D:/JAVA/javaproject/Game/src/grades/"+wenjian);
                        try{
                            if(!fileGrades.exists()){
                                fileGrades.createNewFile();
                            }//若文件找不到则创建新文件
                            fr=new FileReader(fileGrades);
                            char[] data=new char[20];  //文件界定长度20字节
                            int length=fr.read(data);
                            if(length!=-1){       //分数文件长度不为空
                                String str=new String(data,0,length);//读出分数
                                Grades=Integer.parseInt(str);
                            }
                            else{
                                Grades=0;
                            }
                            Grades+=(1000-T);
                            String s=Integer.toString(Grades);//整型转换为字符串
                            fw=new FileWriter(fileGrades);
                            fw.write(s);  //写入分数s到文件file
                            fr.close();  //关闭
                            fw.close();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }

                        /*****************三个等级通关成功******************/
                        if((txtread==16)&&(wenjian.equals("E.txt"))){
                            JOptionPane.showMessageDialog(null,"简单级别您拿到"+Grades+"分，简直不要太厉害啦，快去解锁下一个等级吧");
                            new mainmenu().setVisible(true);
                            this.setVisible(false);
                        }
                        else if((txtread==16)&&(wenjian.equals("N.txt"))){
                            JOptionPane.showMessageDialog(null,"一般级别您拿到"+Grades+"分，是大佬没错了，快去解锁下一个等级吧");
                            new mainmenu().setVisible(true);
                            this.setVisible(false);
                        }
                        else if((txtread==16)&&(wenjian.equals("D.txt"))){
                            JOptionPane.showMessageDialog(null,"困难级别您拿到"+Grades+"分，大神是你吗？收小弟吗？");
                            new mainmenu().setVisible(true);
                            this.setVisible(false);
                        }


                        else{
                            /**********解锁几关对应使能多少按钮**************/
                            for(int k=0;k<(txtread+1);k++){
                                ran.jb.elementAt(k).setEnabled(true);

                            }
                            try{
                                fw=new FileWriter(file);
                                txtread++;
                                String s=Integer.toString(txtread);//将文本以字符串形式s存到文件file中
                                fw.write(s);
                                fw.close();
                            }catch(Exception e1){
                                e1.printStackTrace();
                            }
                            ran.setVisible(true);

                            initGame();
                        }
                    }


                }

            }
        }


    }


    //初始化函数，负责初始化显示方面的数据
    /*********初始化数字********/
    private void initGame() {
        for(int i=0;i<81;i++){
            int x = i/ 9;   //行
            int y = i % 9;  //列
            if((x/3==0&&y/3==0)||(x/6==1&&y/3==0)||(y/6==1&&x/3==0)||(x/6==1&&y/6==1)){
                btnButton[i].setBackground(colors[2]);
            }
            else if(x/3==1&&y/3==1){
                btnButton[i].setBackground(colors[1]);
            }
            else{
                btnButton[i].setBackground(colors[0]);
            }
        }
        for (int i = 0; i < 81; i ++) {
            buttonsVector.elementAt(i).setEnabled(true);
            buttonsVector.elementAt(i).setFont(new Font("华文新魏", Font.PLAIN|Font.BOLD, 40));
        }


        int index = 0;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {

                if (core.test[i][j] != 0) {    //将所备份的还原到指定按钮
                    buttonsVector.elementAt(index).setText(String.valueOf(core.test[i][j]));
                    buttonsVector.elementAt(index).setForeground(Color.BLACK);
                } else {
                    buttonsVector.elementAt(index).setText(null);
                }

                ++ index;//推进到下一个按钮
            }
        }
    }

    //判断操作是否符合游戏填数规则，符合返回TRHE
    public boolean isLegal(int num) {
        int x, y;
        boolean flag = true;  //默认不含有相同元素
        int index = buttonsVector.indexOf(choosedButton);
        if (index != -1) {
            //找到Button
            x = index / 9;//行
            y = index % 9;//列

            for (int i = 0; i < 9; i ++){  // 判断行和列是否含有num元素
                if (core.test[x][i] == num){ //行判断
                    flag = false;
                    break;
                }
                if (core.test[i][y] == num){  //列判断
                    flag = false;
                    break;
                }
            }

            //判断第（tempa, tempb)小九宫格是否含有num元素
            int tempa = x / 3;//知道在哪个小九宫格里           3X3个小宫格
            int tempb = y / 3;
            for (int i = tempa * 3; i < (3 + tempa * 3); i ++){//扫描该（a，b）所在的小九宫格
                for (int j = tempb * 3; j < (3 + tempb * 3); j ++){
                    if (core.test[i][j] == num){
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }


    //通过按钮设置它应有的颜色
    public void setButtonColor(JButton button){
        int index = buttonsVector.indexOf(button);//indexOf 索引当前指定·字符的位置

        //设置为蓝色
        buttonsVector.elementAt(index).setBackground(Color.CYAN);


    }
    //判断81个格子是否都已填上数，填上则表示结束了，返回TRUE
    public boolean isFinish() {
        boolean flag = true; //默认为填完了
        for (int m = 0; m < 9; m ++) {
            for (int n = 0; n < 9; n ++) {
                if (core.test[m][n] == 0) {
                    flag = false;
                    break; //只要有一个还为0，即表示没有填完
                }
            }
        }
        return flag;
    }
    //用来把Test的值拷贝给a数组
    private void backUpProblem() {

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                backup[i][j] = core.test[i][j];
            }
        }
    }
}




//        // 核心算法文件
//        import java.awt.Color;
//        import java.util.Random;
//
//        import javax.swing.ImageIcon;
//        import javax.swing.JFrame;
//        import javax.swing.JLabel;
//        import javax.swing.JOptionPane;
//        import javax.swing.JProgressBar;
//


class GameCore
{
    private int[] hole = null;
    public int[][] test = null;  //挖过洞后的数组

    private int nHole;
    private int N;
    private int num;
    private int X, Y;
    private int FillNum;
    private int Cnum;
    private int holeNumbers;  //设置挖洞数量

    //构造函数
    public GameCore(int holeNumber) {
        hole = new int[81];
        test = new int[9][9];  //存放挖好洞的布局
        this.holeNumbers = holeNumber;

        initialGame();  //初始化
    }

    //设置挖洞数量
    public void setHoleNumber(int holeNumber) {
        this.holeNumbers = holeNumber;
    }
    //Generator方法    ，产生原始填充数独
    private void Generator() {
        Cnum = 0;
        N = 9;
        nHole = 0;

        int count = 0;  //Math.random()随机生成一个小于1的数，（int）Math.Random()相当于取整符，不会四舍五入
        num = 16 + (int)(Math.random() * 6);  //产生16到22之间的随机数,不包括22,也就是16到21的整数
        //while循环  尝试在数组中填入符合数独规则的数字  以此来减少树的分支,提高效率
        while(count != num) {
            X = (int)(Math.random() * 9); //0~8
            Y = (int)(Math.random() * 9);
            FillNum = 1 + (int)(Math.random() * 9); //1-9
            if(bPack(X, Y, FillNum))
            {
                test[X][Y] = FillNum;
                count++;
            }
        }
    }

    //bPack方法  判断是否可将 n 填入 M[y][x] 里
    private boolean bPack (int x, int y, int n) {
        //for循环  纵横判断
        for (int i=0; i<N; i++)                 //若 M[0~N-1][x]
            if (n == test[x][i] || n == test[i][y])   //或 M[y][0~N-1] 中已存在 n
                //例第一列0-8行                       第一行0-8列        十字
                return false;                   //则返回 false
        //for循环 区域判断
        int D_X, D_Y, OrderNum;
        OrderNum=9*(x+1)+(y+1);
        D_X=((int)(x / 3)) * 3;
        D_Y=((int)(y / 3)) * 3;
        for(int count = 0; count != 9; count++) {
            if(D_X == x && D_Y == y)
                continue;
            if(test[(D_X + count / 3)][(D_Y + count % 3)] == n)
                return false;//退出方法，返回false
        }
        //返回结果
        return true;
    }

    //fill方法  此方法用来填充完整已有数子的数独
    private void fill(int num) {
        if(Cnum == 81){
            return;  //表示填满返回
        }
        if(test[Cnum / 9][Cnum % 9] != 0) {
            ++Cnum;
            fill(Cnum);    //位置不为0,填充下一个
            return;
        } else {
            //从1到9里面选择数字填进去
            for(int x = 1; x != 11; x++) {
                if(Cnum == 81){
                    return;  //表示填满返回
                }
                if(x == 10 && Cnum != 0) {
                    --Cnum;
                    test[(Cnum / 9)][(Cnum % 9)] = 0;
                    return;
                }
                if(bPack((Cnum / 9), (Cnum % 9), x)){
                    test[(Cnum / 9)][(Cnum % 9)] = x;
                    ++Cnum;
                    fill(Cnum);
                }
            }
        }
    }

    public void initialGame() {

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++)
                test[i][j] = 0;
        }
        for (int i = 0; i < 81; i ++) {
            hole[i] = 0;
        }

        Generator();
        fill(Cnum);
        DigHole();
    }

    //随机挖洞，生成游戏布局
    private void DigHole() {
        Random random = new Random();
        int r, x, y;
        for (int i = 0; i < holeNumbers;  ){
            r = random.nextInt(81);
            x = r / 9;
            y = r % 9;
            if (test[x][y] != 0) {
                test[x][y] = 0;
                i ++;
            }
        }
    }


}

/*********进度条*************/
class progressbar extends JFrame{

    private JProgressBar jp=new JProgressBar();
    private JLabel lab1=new JLabel(new ImageIcon("bin/img/4.jpg"));
    public progressbar(){
        this.setBounds(350,150,600,400);
        this.setVisible(true);
        this.setLayout(null);
        jp.setBounds(50,300,500,15);
        jp.setForeground(Color.BLUE);
        jp.setVisible(true);
        this.add(jp);
        lab1.setBounds(0,0,600,400);
        lab1.setVisible(true);
        this.add(lab1);
        new Thread(new Runnable(){
            public void run(){
                while(true){
                    try
                    {
                        int i =progressbar.this.jp.getValue();//jp需要是全局变量
                        if (i < progressbar.this.jp.getMaximum())
                        {
                            int k = ++i;
                            progressbar.this.jp.setValue(k);
                            progressbar.this.jp.setStringPainted(true);
                            if (k <= 75)
                            {
                                Thread.sleep(10);
                                progressbar.this.jp.setString("正在载入数独游戏......" + k+ "%");
                            }
                            else
                            {
                                java.lang.Thread.sleep(5);
                                progressbar.this.jp.setString("正在缓冲,请稍后......" + k+ "%");
                            }
                        }
                        else
                        {
                            progressbar.this.dispose();	//清除该容器所有组件
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    // 成功登入

                    if (progressbar.this.jp.getValue() == 100)
                    {
                        progressbar.this.jp.setString("完成100%");
                        JOptionPane.showMessageDialog(null,"游戏载入成功,进入游戏!");
                        new mainmenu().setVisible(true);
                        progressbar.this.dispose();
                        break;

                    }

                }

            }

        }).start();
    }
}

