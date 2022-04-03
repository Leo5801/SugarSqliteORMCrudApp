package com.example.sugarsqliteormcrudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sugarsqliteormcrudapp.adapters.PopulationAdapters;
import com.example.sugarsqliteormcrudapp.entitys.Populations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Populations populations;
    private PopulationAdapters adapters;
    private ArrayList<Populations> populationsArrayList;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(this, R.style.app);
        listView = findViewById(R.id.population_list_view);
        listView.setOnItemClickListener(this);
        displayRecords();
    }
    // axaa

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        populations = populationsArrayList.get(i);
        dialog.setTitle("Edit Records");
        dialog.setContentView(R.layout.edit_records_layout);
        dialog.setCanceledOnTouchOutside(true);
        EditText countryNames = dialog.findViewById(R.id.edit_country_name_edit_text);
        EditText countryPopulation = dialog.findViewById(R.id.edit_country_population_edit_text);
        EditText countryRank = dialog.findViewById(R.id.edit_country_rank_edit_text);
        Button edtiBtn = dialog.findViewById(R.id.editButton);

        countryNames.setText(populations.getCountryName());
        countryPopulation.setText("" + populations.getCountryPopulation());
        countryRank.setText("" + populations.getCountryRanking());

        edtiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String countryName = countryNames.getText().toString();
                String cPopulation = countryPopulation.getText().toString();
                String cRanking = countryRank.getText().toString();

                populations.setCountryName(countryName);
                populations.setCountryPopulation(Double.parseDouble(cPopulation));
                populations.setCountryRanking(Integer.parseInt(cRanking));

                populations.save();
                populationsArrayList.remove(i);
                populationsArrayList.add(populations);
                
                setResult(RESULT_OK);

                Toast.makeText(MainActivity.this, "Edit Record Succcess", Toast.LENGTH_LONG).show();

                dialog.dismiss();
                listView.setAdapter(adapters);
                adapters.notifyDataSetChanged();

            }
        });

        dialog.show();

    }
}