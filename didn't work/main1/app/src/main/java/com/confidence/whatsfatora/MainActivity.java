package com.confidence.whatsfatora;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //layout variables
    /*Button frui = findViewById(R.id.fru);
    Button clot = findViewById(R.id.clo);
    Button elec = findViewById(R.id.ele);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button vege = findViewById(R.id.veg);
        final Button frui = findViewById(R.id.fru);
        final Button meat = findViewById(R.id.mea);
        final Button elec = findViewById(R.id.ele);
        final FloatingActionButton bu = findViewById(R.id.cart);





        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,CartActivity.class);
                startActivity(a);
            }
        });





        vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,categoryActivity.class);
                intent.putExtra("1","Cucumber");
                intent.putExtra("1p","18");
                intent.putExtra("2","Tomato");
                intent.putExtra("2p","15");
                intent.putExtra("3","Potato");
                intent.putExtra("3p","20");
                MainActivity.this.startActivity(intent);
            }
        });
        frui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,categoryActivity.class);
                intent.putExtra("1","Banana");
                intent.putExtra("1p","18");
                intent.putExtra("2","Apple");
                intent.putExtra("2p","15");
                intent.putExtra("3","Mango");
                intent.putExtra("3p","20");
                MainActivity.this.startActivity(intent);
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,categoryActivity.class);
                intent.putExtra("1","Beef");
                intent.putExtra("1p","110");
                intent.putExtra("2","Chicken");
                intent.putExtra("2p","70");
                intent.putExtra("3","Duck");
                intent.putExtra("3p","70");
                MainActivity.this.startActivity(intent);
            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,categoryActivity.class);
                intent.putExtra("1","Huawei Y7");
                intent.putExtra("1p","3000");
                intent.putExtra("2","Xiaomi Mi 8 Lite");
                intent.putExtra("2p","4666");
                intent.putExtra("3","Pocophone F1");
                intent.putExtra("3p","6400");
                MainActivity.this.startActivity(intent);
            }
        });




    }

}
