package com.example.sugarsqliteormcrudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sugarsqliteormcrudapp.adapters.PopulationAdapters;
import com.example.sugarsqliteormcrudapp.entitys.Populations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Populations populations;
    private PopulationAdapters adapters;
    private ArrayList<Populations> populationsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.population_list_view);
        displayRecords();
    }

    private void displayRecords () {
        List<Populations> list = Populations.listAll(Populations.class);

        populationsArrayList = new ArrayList<>();
        populations = new Populations();

        for (int i = 0; i <list.size() ; i++) {
            populations = list.get(i);
            populationsArrayList.add(populations);
        }

        adapters = new PopulationAdapters(MainActivity.this, populationsArrayList);

        listView.setAdapter(adapters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.setting:
                Toast.makeText(MainActivity.this, "Setting Menu", Toast.LENGTH_LONG).show();
                break;
            case R.id.insert:
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                break;
        }
        // asdas

        return super.onOptionsItemSelected(item);
    }
}