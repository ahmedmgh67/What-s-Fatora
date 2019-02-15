package com.confidence.whatsfatora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView first = findViewById(R.id.first);
        TextView second = findViewById(R.id.second);
        TextView third = findViewById(R.id.third);
        TextView fourth = findViewById(R.id.fourth);
        TextView fifth = findViewById(R.id.fifth);
        Button ch = findViewById(R.id.bu);

        String nm1 = (getIntent().getStringExtra("one"));
        first.setText(nm1);



    }
}
