package com.example.activity9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bTambah, bLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTambah = (Button) findViewById(R.id.btnTambah);
        bLihat = (Button) findViewById(R.id.btnLihat);

        bTambah.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahData.class);
                startActivity(intent);
            }
        });

        bLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LihatData.getActIntent(MainActivity.this));
            }
        });
    }
}