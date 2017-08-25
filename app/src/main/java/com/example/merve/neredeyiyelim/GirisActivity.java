package com.example.merve.neredeyiyelim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisActivity extends AppCompatActivity {


    Button btnCafe,btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);


        btnCafe = (Button) findViewById(R.id.buttonCafe);
        btnUser = (Button) findViewById(R.id.buttonKullanici);


        btnCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),CafeGiris.class);
                startActivity(i);

            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KullaniciLogin.class);
                startActivity(i);
            }
        });
    }
}
