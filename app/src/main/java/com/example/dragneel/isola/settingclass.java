package com.example.dragneel.isola;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class settingclass extends AppCompatActivity implements View.OnClickListener {

    Button easybutton, hardbutton;
    TextView difftextview;
    botclass obj;
    MainActivity mobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingclass);

        Intent in = getIntent();

        easybutton = (Button) findViewById(R.id.easybutton);
        easybutton.setOnClickListener(this);

        hardbutton = (Button) findViewById(R.id.hardbutton);
        hardbutton.setOnClickListener(this);

        difftextview = (TextView) findViewById(R.id.difftextview);
        obj = new botclass();
        mobj = new MainActivity();
        if(mobj.dif == 1)
        {
            easybutton.setEnabled(false);
            hardbutton.setEnabled(true);
        }
        else if(mobj.dif == 2)
        {
            easybutton.setEnabled(true);
            hardbutton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.easybutton)
        {
            obj.setmaxd(2);
            easybutton.setEnabled(false);
            hardbutton.setEnabled(true);
            mobj.dif = 1;
        }
        else if(view.getId() == R.id.hardbutton)
        {
            mobj.dif = 2;
            obj.setmaxd(4);
            hardbutton.setEnabled(false);
            easybutton.setEnabled(true);
        }
    }
}
