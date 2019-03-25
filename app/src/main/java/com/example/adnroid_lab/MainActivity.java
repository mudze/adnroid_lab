package com.example.adnroid_lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] values = new String[] {
                "Jabłko", "Pomarańcza", "Gruszka", "Pietruszka",
                "Marchew", "Rzodkiew", "Cebula", "Kapusta",
                "Seler", "Mandarynka", "Śliwka", "Granat",
                "Awokado", "Brzoskwinia", "Nektarynka", "Banan"
        };

        this.target = new ArrayList<>();
        this.target.addAll(Arrays.asList(values));

        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);

        ListView listview = findViewById( R.id.listView);
        listview.setAdapter(this.adapter);
    }
}
