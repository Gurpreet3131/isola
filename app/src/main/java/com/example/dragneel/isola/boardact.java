package com.example.dragneel.isola;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class boardact extends AppCompatActivity implements View.OnClickListener {



    public static GridLayout layout;
    int board[][] = new int[7][7];
    public static int bot = -1, me = 1 , turn = 1, flag = 1, cross= -2;
    public static int mei = 6, mej = 3, boti= 0, botj=3;
    Button bt[][] = new Button[7][7];
    int col = 7, row=7;
    botclass obj;
    public static int select =0;

    Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardact);

        Intent in = getIntent();
        obj = new botclass();
        bot = -1; me = 1; turn = 1; flag = 1; cross= -2;
        mei = 6; mej = 3; boti= 0; botj=3;

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                board[i][j] = 0;
            }
        }
        board[0][3] = bot; // red
        board[6][3] = me; // blue
        layout = (GridLayout) findViewById(R.id.gridlayoutid);

        layout.setColumnCount(col);
        layout.setRowCount(row);
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                bt[i][j] = new Button(this);
                //b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                bt[i][j].setText("");
                //b.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
                bt[i][j].setLayoutParams(new LinearLayout.LayoutParams(93, 135));
                if(i == 0 && j == 3) bt[i][j].setBackgroundResource(R.drawable.red);
                else if(i ==6 && j==3) bt[i][j].setBackgroundResource(R.drawable.blue);
                else bt[i][j].setBackgroundResource(R.drawable.block);
                bt[i][j].setId(row*i+j);
                layout.addView(bt[i][j]);
                //((Button) findViewById(row*i+j)).setOnClickListener(this);
                bt[i][j].setOnClickListener(this);
            }
        }

    }
   // @Override

    public int wincheck(int turn)
    {
        int mect = 0, botct =0;
        for(int i = 0; i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(board[i][j] == 0)
                {
                    int subi = Math.abs(mei - i), subj = Math.abs(mej-j);
                    if(subi<=1 && subj<=1) mect++;
                    subi = Math.abs(boti - i); subj = Math.abs(botj-j);
                    if(subi<=1 && subj<=1) botct++;
                }
            }
        }
        if(turn == me && mect == 0) return bot;
        else if(turn == bot && botct == 0) return me;
        else return 0;
    }
    public void onClick(View view) {

        Button cur = (Button) view;
        int id = cur.getId(), result = 0;
        int curi = id/7, curj = id%7;
        if(turn == me)
        {
            if(board[curi][curj] == 0)
            {
                if(flag == 1)
                {
                    int subi = Math.abs(mei-curi), subj=  Math.abs(mej-curj);
                    if ( subi <=1 && subj <= 1)
                    {
                        bt[mei][mej].setBackgroundResource(R.drawable.block);
                        board[mei][mej] = 0;
                        mei = curi; mej = curj;
                        bt[mei][mej].setBackgroundResource(R.drawable.blue);
                        board[mei][mej] = me;
                        flag = 2;

                    }
                }
                else
                {
                    board[curi][curj] = cross;

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {

                        }
                    };
                    bt[curi][curj].setBackgroundResource(R.drawable.cross);


                    flag = 1;
                    turn *=-1;
                    result = wincheck(turn);
                    // add code here

                    if(select == 2 && result == 0)
                    {
                        obj.surin(board);
                        obj.movegen(board,turn);
                        int mi = obj.getfmi() , mj = obj.getfmj();
                        int ci = obj.getfci(), cj = obj.getfcj();

                        bt[boti][botj].setBackgroundResource(R.drawable.block);
                        board[boti][botj] = 0;
                        boti = mi; botj = mj;
                        bt[boti][botj].setBackgroundResource(R.drawable.red);
                        board[boti][botj] = bot;
                        flag = 2;

                        bt[ci][cj].setBackgroundResource(R.drawable.cross);
                        board[ci][cj] = cross;
                        flag = 1;
                        turn *=-1;
                       result = wincheck(turn);
                    }
                }
            }
        }
        else if(turn == bot)
        {
            if(select == 1)
            {
                if(board[curi][curj] == 0)
                {
                    if(flag == 1)
                    {
                        int subi = Math.abs(boti-curi), subj=  Math.abs(botj-curj);
                        if(subi <=1 && subj <= 1)
                        {
                            bt[boti][botj].setBackgroundResource(R.drawable.block);
                            board[boti][botj] = 0;
                            boti = curi; botj = curj;
                            bt[boti][botj].setBackgroundResource(R.drawable.red);
                            board[boti][botj] = bot;
                            flag = 2;
                        }
                    }
                    else
                    {
                        bt[curi][curj].setBackgroundResource(R.drawable.cross);
                        board[curi][curj] = cross;
                        flag = 1;
                        turn *=-1;
                        result = wincheck(turn);

                    }
                }
            }


        }
        if(result != 0)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            if(select == 1)
            {
                if(result == me) dlgAlert.setMessage("BLUE WINS!!!");
                else if(result == bot) dlgAlert.setMessage("RED WINS!!!");
            }
            else if(select == 2)
            {
                if(result == me) dlgAlert.setMessage("YOU WIN!!!");
                else if(result == bot) dlgAlert.setMessage("COMPUTER WINS!!!");
            }
            dlgAlert.setTitle("GAME ENDED");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            /*dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });*/
            dlgAlert.create().show();
        }
    }

}
