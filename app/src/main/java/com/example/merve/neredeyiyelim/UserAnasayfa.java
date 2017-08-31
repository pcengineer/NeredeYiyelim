package com.example.merve.neredeyiyelim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAnasayfa extends AppCompatActivity {
    ListView lvSonuc;
    EditText etTutar;
    Button btnAra;
    FirebaseDatabase database;
int fiyat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_anasayfa);

        lvSonuc = (ListView) findViewById(R.id.listViewSonuclar);
        etTutar =(EditText) findViewById(R.id.editTextTutar);
        btnAra = (Button) findViewById(R.id.buttonAra);



        database=FirebaseDatabase.getInstance();

        final DatabaseReference dbRef=database.getReference("cafeler");


        final ArrayList<KullaniciMenu> sonucList= new ArrayList<>();


        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiyat =Integer.parseInt(etTutar.getText().toString());
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sonucList.clear();
                        for (final DataSnapshot ds : dataSnapshot.getChildren()) { //cafelerin çocuklarını geziyoruz
                            final String cafeAdi = ds.child("ad").getValue().toString();//cafenin adı
                            final String adres = ds.child("adres").getValue().toString();//cafe adresi
                            final DatabaseReference dbRef2 = ds.child("menuler").getRef();

                            dbRef2.addValueEventListener(new ValueEventListener() {    //cafeler/menuler'i dinliyoruz

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds2 : dataSnapshot.getChildren()) {

                                        int dsfiyat = ds2.child("fiyat").getValue(Integer.class);//menu fiyatı
                                        String dsMenuIsmi = ds2.child("menuAdi").getValue().toString();//menu adı
                                        if (dsfiyat <= fiyat) {//benim paramdan daha az ya da eşit ise
                                            sonucList.add(new KullaniciMenu(dsMenuIsmi, cafeAdi, dsfiyat, adres));//paramın yettiklerini listeye attık
                                            Log.i("sonuçlar", dsfiyat + " " + dsMenuIsmi + " " + cafeAdi + " " + adres);

                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                        final KullaniciAdapter adapter = new KullaniciAdapter(UserAnasayfa.this,sonucList);
                        lvSonuc.setAdapter(adapter);


                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
//cafeler->cafeId->ad,adres,menuler->ad,fiyat


//db 'de cafeleri dinledik


            }


    }

