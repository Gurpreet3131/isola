package com.example.dragneel.isola;

/**
 * Created by dragneel on 11/1/17.
 */

public class botclass
{
    int n = 7, m = 7;
    int me = -1, bot = 1, cross = -2;
    static int maxd = 2;
    int sur[][] = new int[n][m];
    int cxct = 0;
    int fmi=0,fmj=0,fci=0,fcj=0;
    //int board[][] = new int[n][m];
    void surin(int board[][])
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i-1>=0 && j-1>=0) sur[i][j]++;
                if(i-1>=0) sur[i][j]++;
                if(i-1>=0 && j+1<=n) sur[i][j]++;
                if(j-1>=0) sur[i][j]++;
                if(j+1<m) sur[i][j]++;
                if(i+1<n && j-1>=0) sur[i][j]++;
                if(i+1<n) sur[i][j]++;
                if(i+1<n && j+1<m) sur[i][j]++;
                if(sur[i][j] == 3) sur[i][j] =10;
                else if(sur[i][j] == 5) sur[i][j] = 20;
                else sur[i][j] = 30;
                if(board[i][j] == -2) cxct++;
            }
        }
    }


    void movegen(int board[][], int turn)
    {
        int maxscore = Integer.MIN_VALUE, minscore = Integer.MAX_VALUE;
        int movesi[] = new int[10];
        int movesj[] = new int[10];
        int cxi[] = new int[10];
        int cxj[] = new int[10];
        int movesindex = 0, cxindex=0;
        int pi=-1,pj=-1, found=0, ui= -1,uj= -1, fl=0;
        for(int i=0; i<n; i++)
        {
            for(int j=0;j<m;j++)
            {
                if(board[i][j] == turn) {pi=i; pj=j;}
                if(board[i][j] == -turn) {ui=i;uj=j;}
                if(fl==0 && board[i][j] == 0 && cxct>5)
                {
                    fl=1;
                    cxi[cxindex]= i; cxj[cxindex++] = j;
                }
            }
        }
        //cout<<pi<<" "<<pj<<endl;
        if(pi-1>=0 && pj-1>=0) // up-left
            if(board[pi-1][pj-1]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj-1; }
        if(pi-1>=0 && pj>=0) // up
            if(board[pi-1][pj]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj; }
        if(pi-1>=0 && pj+1<m) // up-right
            if(board[pi-1][pj+1]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj+1; }
        if(pi>=0 && pj-1>=0) // left
            if(board[pi][pj-1]==0) { movesi[movesindex] = pi; movesj[movesindex++] = pj-1; }
        if(pi>=0 && pj+1<m) // right
            if(board[pi][pj+1]==0) { movesi[movesindex] = pi; movesj[movesindex++] = pj+1; }
        if(pi+1<n && pj-1>=0) // down-left
            if(board[pi+1][pj-1]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj-1; }
        if(pi+1<n && pj>=0) // down
            if(board[pi+1][pj]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj; }
        if(pi+1<n && pj+1<m) // down-right
            if(board[pi+1][pj+1]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj+1; }

        int val = cross;
        if(ui-1>=0 && uj-1>=0) // up-left
            if(board[ui-1][uj-1]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj-1; }
        if(ui-1>=0 && uj>=0) // up
            if(board[ui-1][uj]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj; }
        if(ui-1>=0 && uj+1<m) // up-right
            if(board[ui-1][uj+1]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj+1; }
        if(ui>=0 && uj-1>=0) // left
            if(board[ui][uj-1]!=val) { cxi[cxindex]= ui; cxj[cxindex++] = uj-1; }
        if(ui>=0 && uj+1<m) // right
            if(board[ui][uj+1]!=val) { cxi[cxindex]= ui; cxj[cxindex++] = uj+1; }
        if(ui+1<n && uj-1>=0) // down-left
            if(board[ui+1][uj-1]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj-1; }
        if(ui+1<n && uj>=0) // down
            if(board[ui+1][uj]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj; }
        if(ui+1<n && uj+1<m) // down-right
            if(board[ui+1][uj+1]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj+1; }

        cxi[cxindex] = pi; cxj[cxindex++] = pj;
        // cout<<"maxscore = " << maxscore<<endl;
        for(int m=0;m<movesindex;m++)
        {
            //	cout<<"maxscore = " << maxscore<<endl;
            int ii = movesi[m], jj = movesj[m];
            board[ii][jj] = turn; board[pi][pj] = 0;
            for(int cc = 0; cc< cxindex; cc++)
            {
                int i = cxi[cc], j = cxj[cc];
                if(board[i][j] == 0)
                {
                    //	cout<<"try move= "<<ii<<" "<<jj<<" "<<i<<" "<<j<<endl;
                    board[i][j] = cross;
                    //	cout<<"before\n"; print_board();
                    int tempscore = minimax(board, -turn, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if(tempscore > maxscore)
                    {
                        maxscore = tempscore; fmi= ii; fmj = jj;
                        fci=i; fcj = j;
                    }
                    else if(tempscore == maxscore)
                    {
                        if(sur[i][j]>sur[fci][fcj])
                        {
                            fmi= ii; fmj = jj;
                            fci = i;
                            fcj = j;
                        }
                    }
                    if(tempscore<minscore)	minscore = tempscore;
                    board[i][j] = 0;
                }
            }
            board[ii][jj] = 0; board[pi][pj] = turn;
        }
        //mx = maxscore;  mn = minscore;
    }

    int minimax(int board[][],int turn, int depth, int alpha,int beta)
    {
        int result = win(board, turn, depth);
        if(result!=0)
        {
            int score = evaluatescore(board,turn, depth);
            return score;
        }
        if(depth>=maxd)
        {
            int score = evaluatescore(board,turn,depth);
            return score;
        }
        //cout<<"depth = "<<depth<<endl;
        int maxscore = Integer.MIN_VALUE, minscore = Integer.MAX_VALUE;
        int movesi[] = new int[10]; int movesj[] = new int[10];
        int cxi[] = new int[10]; int cxj[]= new int[10];
        int pi=-1,pj=-1, found=0, ui = -1,uj= -1,fl=0;
        int movesindex= 0, cxindex=0;
        for(int i=0; i<n; i++)
        {
            for(int j=0;j<m;j++)
            {
                if(board[i][j] == turn) {pi=i; pj=j;}
                if(board[i][j] == -turn) {ui=i;uj=j;}
                if(fl==0 && board[i][j] == 0 && cxct>5)
                {
                    fl=1;
                    cxi[cxindex]= i; cxj[cxindex++] = j;
                }
            }
        }
        //cout<<pi<<" "<<pj<<endl;
        if(pi-1>=0 && pj-1>=0) // up-left
            if(board[pi-1][pj-1]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj-1; }
        if(pi-1>=0 && pj>=0) // up
            if(board[pi-1][pj]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj; }
        if(pi-1>=0 && pj+1<m) // up-right
            if(board[pi-1][pj+1]==0) { movesi[movesindex] = pi-1; movesj[movesindex++] = pj+1; }
        if(pi>=0 && pj-1>=0) // left
            if(board[pi][pj-1]==0) { movesi[movesindex] = pi; movesj[movesindex++] = pj-1; }
        if(pi>=0 && pj+1<m) // right
            if(board[pi][pj+1]==0) { movesi[movesindex] = pi; movesj[movesindex++] = pj+1; }
        if(pi+1<n && pj-1>=0) // down-left
            if(board[pi+1][pj-1]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj-1; }
        if(pi+1<n && pj>=0) // down
            if(board[pi+1][pj]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj; }
        if(pi+1<n && pj+1<m) // down-right
            if(board[pi+1][pj+1]==0) { movesi[movesindex] = pi+1; movesj[movesindex++] = pj+1; }

        int val = cross;
        if(ui-1>=0 && uj-1>=0) // up-left
            if(board[ui-1][uj-1]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj-1; }
        if(ui-1>=0 && uj>=0) // up
            if(board[ui-1][uj]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj; }
        if(ui-1>=0 && uj+1<m) // up-right
            if(board[ui-1][uj+1]!=val) { cxi[cxindex]= ui-1; cxj[cxindex++] = uj+1; }
        if(ui>=0 && uj-1>=0) // left
            if(board[ui][uj-1]!=val) { cxi[cxindex]= ui; cxj[cxindex++] = uj-1; }
        if(ui>=0 && uj+1<m) // right
            if(board[ui][uj+1]!=val) { cxi[cxindex]= ui; cxj[cxindex++] = uj+1; }
        if(ui+1<n && uj-1>=0) // down-left
            if(board[ui+1][uj-1]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj-1; }
        if(ui+1<n && uj>=0) // down
            if(board[ui+1][uj]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj; }
        if(ui+1<n && uj+1<m) // down-right
            if(board[ui+1][uj+1]!=val) { cxi[cxindex]= ui+1; cxj[cxindex++] = uj+1; }

        cxi[cxindex] = pi; cxj[cxindex++] = pj;

        int score;
        if(turn == me) score = Integer.MIN_VALUE;
        else score = Integer.MAX_VALUE;
        for(int m=0;m<movesindex;m++)
        {
            //cout<<"maxscore = " << maxscore<<endl;
            int ii = movesi[m], jj = movesj[m];
            board[ii][jj] = turn; board[pi][pj] = 0;
            for(int cc = 0; cc< cxindex; cc++)
            {
                int i = cxi[cc], j = cxj[cc];
                if(board[i][j] == 0)
                {
                    board[i][j] = cross;
                    int tempscore = minimax(board, -turn, depth+1, alpha, beta);
                    if(tempscore > maxscore) maxscore = tempscore;
                    if(tempscore<minscore) minscore = tempscore;
                    if(turn == me) score = Math.max(score,tempscore);
                    else score = Math.min(score,tempscore);
                    board[i][j] = 0;
                    if(beta <= alpha) break;
                }
            }
            board[ii][jj] = 0; board[pi][pj] = turn;
            if(beta<=alpha) break;
        }
        return score;
    }

    int win(int board[][], int turn, int depth)
    {
        int pi=-1,pj=-1,found=0, mpos=0;
        for(int i=0; i<n; i++)
        {
            for(int j=0;j<m;j++)
            {
                if(board[i][j] == turn)
                {
                    pi=i; pj=j; found=1; break;
                }
            }
            if(found == 1) break;
        }
        if(pi-1>=0 && pj-1>=0) // up-left
            if(board[pi-1][pj-1]==0)  mpos++;
        if(pi-1>=0 && pj>=0) // up
            if(board[pi-1][pj]==0) mpos++;
        if(pi-1>=0 && pj+1<m) // up-right
            if(board[pi-1][pj+1]==0) mpos++;
        if(pi>=0 && pj-1>=0) // left
            if(board[pi][pj-1]==0) mpos++;
        if(pi>=0 && pj+1<m) // right
            if(board[pi][pj+1]==0) mpos++;
        if(pi+1<n && pj-1>=0) // down-left
            if(board[pi+1][pj-1]==0) mpos++;
        if(pi+1<n && pj>=0) // down
            if(board[pi+1][pj]==0) mpos++;
        if(pi+1<n && pj+1<m) // down-right
            if(board[pi+1][pj+1]==0) mpos++;

        if(mpos == 0) return -turn;
        else return 0;
    }

    int evaluatescore(int board[][], int turn, int depth)
    {
        int result = win(board,turn, depth), score=0, mul = maxd - depth+1;
        int mesc=0, usc=0;
        if(result == me) return 10000*mul;
        else if(result == bot) return -10000*mul;
        else
        {
            int pi=0,pj=0,ui=0,uj=0,found=0;
            for(int i=0; i<n; i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(board[i][j] == me)	{pi=i; pj=j;}
                    else if(board[i][j] == -turn) {ui=i;uj=j;}
                }
            }
            mesc+= calcadj(board,me,pi,pj);
            usc+= calcadj(board,bot,ui,uj);

            if(cxct<2 || cxct>5)
            {
                if(pi-1>=0 && pj-1>=0) // up-left
                    if(board[pi-1][pj-1]==0) mesc+=calcadj(board,me,pi-1,pj-1);
                if(pi-1>=0 && pj>=0) // up
                    if(board[pi-1][pj]==0) mesc+=calcadj(board,me,pi-1,pj);
                if(pi-1>=0 && pj+1<m) // up-right
                    if(board[pi-1][pj+1]==0) mesc+=calcadj(board,me,pi-1,pj+1);
                if(pi>=0 && pj-1>=0) // left
                    if(board[pi][pj-1]==0) mesc+=calcadj(board,me,pi,pj-1);
                if(pi>=0 && pj+1<m) // right
                    if(board[pi][pj+1]==0) mesc+=calcadj(board,me,pi,pj+1);
                if(pi+1<n && pj-1>=0) // down-left
                    if(board[pi+1][pj-1]==0) mesc+=calcadj(board,me,pi+1,pj-1);
                if(pi+1<n && pj>=0) // down
                    if(board[pi+1][pj]==0) mesc+=calcadj(board,me,pi+1,pj);
                if(pi+1<n && pj+1<m) // down-right
                    if(board[pi+1][pj+1]==0) mesc+=calcadj(board,me,pi+1,pj+1);


                if(ui-1>=0 && uj-1>=0) // up-left
                    if(board[ui-1][uj-1]==0) usc+=calcadj(board,bot,ui-1,uj-1);
                if(ui-1>=0 && uj>=0) // up
                    if(board[ui-1][uj]==0) usc+=calcadj(board,bot,ui-1,uj);
                if(ui-1>=0 && uj+1<m) // up-right
                    if(board[ui-1][uj+1]==0) usc+=calcadj(board,bot,ui-1,uj+1);
                if(ui>=0 && uj-1>=0) // left
                    if(board[ui][uj-1]==0) usc+=calcadj(board,bot,ui,uj-1);
                if(ui>=0 && uj+1<m) // right
                    if(board[ui][uj+1]==0) usc+=calcadj(board,bot,ui,uj+1);
                if(ui+1<n && uj-1>=0) // down-left
                    if(board[ui+1][uj-1]==0) usc+=calcadj(board,bot,ui+1,uj-1);
                if(ui+1<n && uj>=0) // down
                    if(board[ui+1][uj]==0) usc+=calcadj(board,bot,ui+1,uj);
                if(ui+1<n && uj+1<m) // down-right
                    if(board[ui+1][uj+1]==0) usc+=calcadj(board,bot,ui+1,uj+1);

            }

            //cout<<"mes = "<<mesc<<"  usc= "<<usc<<endl;
            score = mesc - usc;
            return score;
        }
    }

    int calcadj(int board[][], int turn, int pi, int pj)
    {
        int ct=0;
        if(pi-1>=0 && pj-1>=0)
            if(board[pi-1][pj-1] == 0) ct+= sur[pi-1][pj-1];
        if(pi-1>=0)
            if(board[pi-1][pj] == 0) ct+= sur[pi-1][pj];
        if(pi-1>=0 && pj+1<m)
            if(board[pi-1][pj+1] == 0) ct+= sur[pi-1][pj+1];
        if(pj-1>=0)
            if(board[pi][pj-1] == 0) ct+= sur[pi][pj-1];
        if(pj+1<m)
            if(board[pi][pj+1] == 0) ct+= sur[pi][pj+1];
        if(pi+1<n && pj-1>=0)
            if(board[pi+1][pj-1] == 0) ct+= sur[pi][pj-1];
        if(pi+1<n)
            if(board[pi+1][pj] == 0) ct+= sur[pi+1][pj];
        if(pi+1<n && pj+1<m)
            if(board[pi+1][pj+1] == 0) ct+= sur[pi+1][pj+1];

        return ct;
    }
    int getfmi()
    {
        return fmi;
    }
    int getfmj()
    {
        return fmj;
    }
    int getfci()
    {
        return fci;
    }
    int getfcj()
    {
        return fcj;
    }
    void setmaxd(int dp)
    {
        maxd = dp;
    }
}
