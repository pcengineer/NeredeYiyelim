package com.example.merve.neredeyiyelim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CafeGiris extends AppCompatActivity {

    EditText etCuser,etCpass;
    Button btngiris;
    TextView tvCyazi;

    FirebaseAuth mAuth;
    String mail,sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_giris);



        etCuser = (EditText) findViewById(R.id.editTextCafeUser);
        etCpass = (EditText) findViewById(R.id.editTextCafePass);
        btngiris = (Button) findViewById(R.id.buttonCafeGiris);
        tvCyazi = (TextView) findViewById(R.id.textViewCafeYazi);



        tvCyazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KayitOlCafe.class);
                startActivity(i);
            }
        });

        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mail = etCuser.getText().toString();
                sifre = etCpass.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(mail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(getApplicationContext(),CafeAnaSayfa.class);
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });
    }
}
