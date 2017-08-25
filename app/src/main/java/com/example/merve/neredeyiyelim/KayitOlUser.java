package com.example.merve.neredeyiyelim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOlUser extends AppCompatActivity {
    EditText etUserAdi,etUserSoyadi,etUsersifre,etUsermail;
    Button btnUserKaydol;

    String username,surname,mail,password;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol_user);

        etUserAdi=(EditText) findViewById(R.id.editTextKayitAdi);
        etUserSoyadi = (EditText) findViewById(R.id.editTextKayitSoyadi);
        etUsersifre = (EditText) findViewById(R.id.editTextKayitSifre);
        etUsermail = (EditText) findViewById(R.id.editTextKayitMail);
        btnUserKaydol = (Button) findViewById(R.id.buttonKulaniciKaydol);


        btnUserKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username =etUserAdi.getText().toString();
                surname = etUserSoyadi.getText().toString();
                mail = etUsermail.getText().toString();
                password = etUsersifre.getText().toString();

                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(KayitOlUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(getApplicationContext(),KullaniciLogin.class);
                            startActivity(i);
                            finish();

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
