package com.example.merve.neredeyiyelim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by Merve on 31.08.2017.
 */

public class KullaniciAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList<KullaniciMenu> sonucList;
    FirebaseUser fUser;



    public KullaniciAdapter(Activity activity, ArrayList<KullaniciMenu> sonucList, FirebaseUser fUser) {
        layoutInflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sonucList = sonucList;
        this.fUser = fUser;
    }


    @Override
    public int getCount() {
        return sonucList.size();
    }

    @Override
    public Object getItem(int position) {
        return sonucList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        KullaniciMenu kmenu = sonucList.get(position);
        View satir=layoutInflater.inflate(R.layout.custom_satir,null);

        TextView tvmenuAdi= (TextView) satir.findViewById(R.id.textViewAdi);
        tvmenuAdi.setText(kmenu.getMenu());


        TextView tvcafeAdi = (TextView) satir.findViewById(R.id.textViewCafeAdi);
        tvcafeAdi.setText(kmenu.getCafeAdi());

        TextView tvfiyat = (TextView) satir.findViewById(R.id.textViewFiyati);
        tvfiyat.setText(kmenu.getFiyat());

        TextView tvadres = (TextView) satir.findViewById(R.id.textViewAdres);
        tvadres.setText(kmenu.getAdres());

        return satir;


    }
}
