package com.ruhul.qrattendencesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.budiyev.android.codescanner.CodeScanner;

public class MainActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private Button pushscan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //project create by ruhul-----------------
        pushscan_btn=findViewById(R.id.scan_id);

        pushscan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Scan_id_Qrcode.class));
            }
        });
    }
}