package com.example.adnroid_lab;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;
    private MySQLite db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.db = new MySQLite(this);

        String[] values = new String[] {
                "Jabłko", "Pomarańcza", "Gruszka", "Pietruszka",
                "Marchew", "Rzodkiew", "Cebula", "Kapusta",
                "Seler", "Mandarynka", "Śliwka", "Granat",
                "Awokado", "Brzoskwinia", "Nektarynka", "Banan"
        };

        this.target = new ArrayList<>();
        this.target.addAll(Arrays.asList(values));


        this.adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                db.lista(),
                new String[] {"_id", "rodzaj"},
                new int[] {android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);


        ListView listview = findViewById( R.id.listView);
        listview.setAdapter(this.adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(android.R.id.text1);

                Warzywa warzywo = db.pobierz(Integer.parseInt(name.getText().toString()));

                        Intent intencja = new Intent(getApplicationContext(), DodajWpis.class);
                intencja.putExtra("element", warzywo);
                startActivityForResult(intencja,2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void nowyWpis (MenuItem menuItem) {
        Intent intencja = new Intent(this, DodajWpis.class);
        startActivityForResult(intencja, 1);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            Warzywa nowy = (Warzywa) extras.getSerializable("nowy");
            this.db.dodaj(nowy);
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
        }
        if (requestCode==2 && resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            Warzywa nowy = (Warzywa) extras.getSerializable("nowy");
            this.db.aktualizuj(nowy);
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
        }
    }
}
