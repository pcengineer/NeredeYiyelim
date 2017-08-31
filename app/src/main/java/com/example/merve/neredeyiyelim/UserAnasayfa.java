package com.example.merve.neredeyiyelim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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
    ListView lvMenu;
    EditText etTutar;
    Button btnAra;
    FirebaseDatabase database;
    ArrayList<Menu> menuList;
    FirebaseUser fUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_anasayfa);

        lvSonuc = (ListView) findViewById(R.id.listViewSonuclar);
        etTutar =(EditText) findViewById(R.id.editTextTutar);
        btnAra = (Button) findViewById(R.id.buttonAra);

        final ArrayList<KullaniciMenu> sonucList= new ArrayList<>();



        final DatabaseReference dbRef=database.getReference("cafeler/");
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        database = FirebaseDatabase.getInstance();

        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fiyat =Integer.parseInt(etTutar.getText().toString());


                dbRef.push().child("fiyat").setValue(fiyat);

            }
        });


        final KullaniciAdapter adapter = new KullaniciAdapter(this,sonucList,firebaseUser);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                menuList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                    menuList.add(ds.getValue(Menu.class));



                    if(etTutar.getText()<=menuList.getFiyat()){
                        sonucList.add(ds.getValue(KullaniciMenu.class));


                    }
                }

                lvSonuc.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}
