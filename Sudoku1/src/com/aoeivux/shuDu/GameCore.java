package com.aoeivux.shuDu;

import java.util.Random;

class GameCore {
    private int[] hole = null;
    public int[][] test = null;  //挖过洞后的数组

    private int N;
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
    
    private void Generator() {
        Cnum = 0;
        N = 9;
        int nHole = 0;

        int count = 0;  //Math.random()随机生成一个小于1的数，（int）Math.Random()相当于取整符，不会四舍五入
        int num = 16 + (int) (Math.random() * 6);  //产生16到22之间的随机数,不包括22,也就是16到21的整数
        //while循环  尝试在数组中填入符合数独规则的数字  以此来减少树的分支,提高效率
        while(count != num) {
            int x = (int) (Math.random() * 9); //0~8
            int y = (int) (Math.random() * 9);
            int fillNum = 1 + (int) (Math.random() * 9); //1-9
            if(bPack(x, y, fillNum))
            {
                test[x][y] = fillNum;
                count++;
            }
        }
    }

   
    private boolean bPack (int x, int y, int n) {
        
        for (int i=0; i<N; i++)                 
            if (n == test[x][i] || n == test[i][y])   
                return false;                   
        
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