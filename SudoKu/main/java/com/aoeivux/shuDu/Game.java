package com.aoeivux.shuDu;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

//游戏主界面
class Game extends JFrame implements ActionListener{

    private final Vector<JButton> buttonsVector; //存放81个按钮    Vector<E>
    private final Vector<JButton> numberButtonVector; //存放9个数字键

    private JButton chosenButton; //重新开始按钮 //当前被选中的按钮，表示将要被修改
    private final GameCore core; //游戏内核，含有游戏算法
    private final int[][] backup; //用来备份游戏，用于清除后重新导入
    private int T,M,S;//计时器

    private final int holeNumber;
    private final String string;
    private final String fileDemo;

    private final JPanel panel_2;
    private final JButton[] btnButton=new JButton[81];
    private final JButton btnButton1;
    private final JButton btnButton2;
    private Thread thread;
    private final JMenu mnNewMenu2 ;
    private int txtReader;

    private int Grades;

    private final Color[] colors={
            Color.MAGENTA,
            Color.WHITE,
            Color.PINK,
            Color.PINK,

    };


    /**
     * Create the frame.
     */


    public Game(int holeNumber,String string,String fileDemo) throws InterruptedException {

        //BackGround Image settings

        JLabel lbBg = new JLabel(new ImageIcon("src/com/aoeivux/shuDu/staticData/3.jpg"));
        lbBg.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(lbBg);


        this.holeNumber =holeNumber;
        this.string=string;
        this.fileDemo = fileDemo;



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 600, 700);
        this.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //计时器
        JMenu mnNewMenu1 = new JMenu("计时:");
        mnNewMenu1.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        menuBar.add(mnNewMenu1);
        mnNewMenu2 = new JMenu("00:00 ");
        mnNewMenu2.setFont(new Font("华文行楷", Font.PLAIN|Font.BOLD, 20));
        menuBar.add(mnNewMenu2);

        JPanel contentPane = new JPanel();//大容器

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//页边空白
        setContentPane(contentPane);
        setLayout(null);

        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();//小容器1
        contentPane.add(panel, BorderLayout.SOUTH);//上北下南
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //hgap  vgap

        String[] j={"1","2","3","4","5","6","7","8","9"};

        //所需要填入的数字
        JButton[] button =new JButton[9];

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
        btnButton1.setFont(new Font("华文新魏", Font.PLAIN|Font.BOLD, 20));
        btnButton1.addActionListener(this);
        panel_1.add(btnButton1);

        btnButton2=new JButton("开始游戏");
        btnButton2.setFont(new Font("华文新魏", Font.PLAIN|Font.BOLD, 20));
        btnButton2.addActionListener(this);
        panel_1.add(btnButton2);

        JButton btnNewButton_1 = new JButton("清除已填");
        btnNewButton_1.setFont(new Font("华文新魏", Font.PLAIN|Font.BOLD, 20));
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
        JLabel lab1 = new JLabel("等级：" + string);
        lab1.setFont(new Font("华文新魏", Font.PLAIN|Font.BOLD, 20));
        panel_1.add(lab1);

        panel_1.setVisible(true);



        //中间的数独游戏界面部分
        panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(9, 9, 5, 5));//水平与垂直间隔5像素
        // cows columns h_gap v_gap


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

        //数字键
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

        // background music
        Thread BgmThread = new Thread(new Runnable() {
            //通过匿名内部类实现多线程

            @Override
            public void run() {
                new MusicPlayer().playMusic(new File("G:\\ProjectsAll\\SuperMario\\src\\main\\java\\com\\aoeivux\\shuDu\\AudioData\\不眠之夜_looperman.wav"));
            }
        });
        //实现Runner接口开始线程的方法
        BgmThread.start();
    }

    //监视器
    public void actionPerformed(ActionEvent e) {

        //控制81个显示按钮
        JButton button = null;

        for (int i = 0; i < 81; i ++) {
            if (e.getSource() == buttonsVector.elementAt(i)) {
                button = buttonsVector.elementAt(i);
                if (button.getText()==null) {
                    if (chosenButton != null) {
                        setButtonColor(chosenButton);
                    }
                    /*监听事件，选定空按钮*/
                    chosenButton = buttonsVector.elementAt(i);
                    buttonsVector.elementAt(i).setBackground(Color.BLACK);
                }
            }
        }

        if (e.getSource()==btnButton1){
            this.setVisible(false);
            new GameMain().setVisible(true);
        }


        //计时器
        if (e.getSource() == btnButton2){
            panel_2.setVisible(true);
            thread=new Thread(new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(1000);
                            T++;
                            M=T/60;
                            S=T%60;
                            mnNewMenu2.setText(M+":"+S);
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
                    chosenButton.setText(numberStr);  //如果符合规则则填入所选数字
                    chosenButton.setForeground(Color.GREEN);
                    //修改Test布局表
                    index = buttonsVector.indexOf(chosenButton);
                    x = index / 9;
                    y = index % 9;
                    core.test[x][y] = num;//备份

                    //判读是否成功了
                    if (isFinish()) {
                        thread.stop();
                        JOptionPane.showMessageDialog(null, "用时"+M+"分"+S+"秒   得分："+(1000-T)+"分\n");
                        this.setVisible(false);

                        File file = new File("src/com/aoeivux/shuDu/file/" + fileDemo);//相对路径
//                        GameLevel ran=new GameLevel(holeNumber,string, fileDemo);
                        //读出文件txt的数据（字符串）
                        FileReader fr;
                        try{
                            char[] data=new char[20];
                            fr =new FileReader(file);
                            int length= fr.read(data);
                            String str=new String(data,0,length);//读取文件file内容
                            txtReader =Integer.parseInt(str);
                            fr.close();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }

                        /**************分数存储*************/
                        File fileGrades = new File("src/com/aoeivux/shuDu/grade" + fileDemo);
                        //将数据（字符串）写到文件中，方便调用
                        FileWriter fw;
                        try{
                            if(!fileGrades.exists()){
                                fileGrades.createNewFile();
                            }//若文件找不到则创建新文件
                            fr =new FileReader(fileGrades);
                            char[] data=new char[20];  //文件界定长度20字节
                            int length= fr.read(data);
                            if(length!=-1){       //分数文件长度不为空
                                String str=new String(data,0,length);//读出分数
                                Grades=Integer.parseInt(str);
                            }
                            else{
                                Grades=0;
                            }
                            Grades+=(1000-T);
                            String s=Integer.toString(Grades);//整型转换为字符串
                            fw =new FileWriter(fileGrades);
                            fw.write(s);  //写入分数s到文件file
                            fr.close();  //关闭
                            fw.close();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }

                        /*****************三个等级通关成功******************/
                        if((txtReader == 16)&&(fileDemo.equals("E.txt"))){
                            JOptionPane.showMessageDialog(null,"简单模式您拿到"+Grades+"分");
                            new GameMain().setVisible(true);
                            this.setVisible(false);
                        }
                        else if((txtReader ==16)&&(fileDemo.equals("N.txt"))){
                            JOptionPane.showMessageDialog(null,"一般模式您拿到"+Grades+"分");
                            new GameMain().setVisible(true);
                            this.setVisible(false);
                        }
                        else if((txtReader ==16)&&(fileDemo.equals("D.txt"))){
                            JOptionPane.showMessageDialog(null,"困难级别您拿到"+Grades+"分");
                            new GameMain().setVisible(true);
                            this.setVisible(false);
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
        int index = buttonsVector.indexOf(chosenButton);
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

        //错误的为红色
        buttonsVector.elementAt(index).setBackground(Color.RED);


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


