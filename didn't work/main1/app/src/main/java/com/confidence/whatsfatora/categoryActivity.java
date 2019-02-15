package com.confidence.whatsfatora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class categoryActivity extends AppCompatActivity {


    /*Button two = findViewById(R.id.two);
    Button three = findViewById(R.id.three);
    Button four = findViewById(R.id.four);
    Button five = findViewById(R.id.five);
    Button six = findViewById(R.id.six);
    Button seven = findViewById(R.id.seven);
    Button eight = findViewById(R.id.eight);
    Button nine = findViewById(R.id.nine);*/





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        final Button two = findViewById(R.id.two);
        final Button three = findViewById(R.id.three);
        //Button four = findViewById(R.id.four);
        //Button five = findViewById(R.id.five);
        //Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        final Button one = findViewById(R.id.one);



        final String nm1 = (getIntent().getStringExtra("1"));
        final String pr1 = (getIntent().getStringExtra("1p"));

        String nm2 = (getIntent().getStringExtra("2"));
        String pr2 = (getIntent().getStringExtra("2p"));

        String nm3 = (getIntent().getStringExtra("3"));
        String pr3 = (getIntent().getStringExtra("3p"));

        one.setText(nm1 + pr1);
        //onepr.setText(pr1);

        two.setText(nm2 + pr2);
        //twopr.setText(pr2);

        three.setText(nm3 + pr3);
        //threepr.setText(pr3);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ah = new Intent(categoryActivity.this,CartActivity.class);
                ah.putExtra("first",nm1 +pr1);
                startActivity(ah);
                }
        }
        );
    }
}