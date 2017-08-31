package com.example.merve.neredeyiyelim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CafeAnaSayfa extends AppCompatActivity {
    ListView lvMenu;
    EditText etMenuAdi,etFiyat;
    Button btnEkle;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_ana_sayfa);
        lvMenu =(ListView) findViewById(R.id.listViewMenuler);
        etMenuAdi = (EditText) findViewById(R.id.editTextMenuAdi);
        etFiyat= (EditText) findViewById(R.id.editTextFiyati);
        btnEkle = (Button) findViewById(R.id.buttonKaydet);

        final ArrayList<Menu> menuList= new ArrayList<>();

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
      

        database = FirebaseDatabase.getInstance();

        final DatabaseReference dbRef=database.getReference("cafeler/"+firebaseUser.getUid());

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuAdi= etMenuAdi.getText().toString();
                int fiyat =Integer.parseInt(etFiyat.getText().toString());


                dbRef.push().setValue(new Menu(menuAdi,fiyat));
                etMenuAdi.setText("");
                etFiyat.setText("");
            }
        });



        final CustomAdapter adapter = new CustomAdapter(this,menuList,firebaseUser);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                    menuList.add(ds.getValue(Menu.class));
                }

                lvMenu.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
