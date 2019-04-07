package com.example.adnroid_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

public class DodajWpis extends AppCompatActivity {

    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
        ArrayAdapter rodzaje = new ArrayAdapter( this,
                android.R.layout.simple_spinner_dropdown_item,
                new String [] {"strączkowe", "dyniowe", "cebulowe", "kapustne", "korzeniowe", "psiankowate", "rzepowate", "wieloletnie"});
        Spinner rodzaj = (Spinner) findViewById(R.id.rodzaj);
        rodzaj.setAdapter(rodzaje);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getSerializable("element") != null) {
                Warzywa warzywo = (Warzywa)extras.getSerializable("element");

                EditText kolor = (EditText) findViewById(R.id.kolor);
                EditText wielkosc = (EditText) findViewById(R.id.wielkosc);
                EditText opis = (EditText) findViewById(R.id.opis);

                kolor.setText(warzywo.getKolor());
                wielkosc.setText(Float.toString(warzywo.getWirlkosc()));
                opis.setText(warzywo.getOpis());

                this.modyfi_id = warzywo.getId();
            }
        } catch (Exception ex) {
            this.modyfi_id = 0;
        }

    }

    public void wyslij (View view) {
        Spinner rodzaj = (Spinner) findViewById(R.id.rodzaj);
        EditText kolor = (EditText) findViewById(R.id.kolor);
        EditText wielkosc = (EditText) findViewById(R.id.wielkosc);
        EditText opis = (EditText) findViewById(R.id.opis);

        Warzywa warzywo = new Warzywa(rodzaj.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString());

        warzywo.setId(this.modyfi_id);

        Intent intencja = new Intent();
        intencja.putExtra("nowy", warzywo);
        setResult(RESULT_OK, intencja);
        finish();
    }
}
