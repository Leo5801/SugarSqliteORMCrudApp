package com.example.sugarsqliteormcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sugarsqliteormcrudapp.entitys.Populations;

public class InsertActivity extends AppCompatActivity {


    private EditText countryName, countryPopulation, countyRank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        countryName = findViewById(R.id.country_name_edit_text);
        countryPopulation = findViewById(R.id.country_population_edit_text);
        countyRank = findViewById(R.id.country_rank_edit_text);
    }

    private void insertRecorsd() {


        Populations populations = new Populations();

        String name = countryName.getText().toString();
        String cPopulation = countryPopulation.getText().toString();
        String cRank = countyRank.getText().toString();

        populations.setCountryName(name);
        populations.setCountryPopulation(Double.parseDouble(cPopulation));
        populations.setCountryRanking(Integer.parseInt(cRank));

        populations.save();

        setResult(RESULT_OK);

        Toast.makeText(InsertActivity.this, "Record Insert Success", Toast.LENGTH_LONG).show();

        countryName.setText("");
        countryPopulation.setText("");
        countyRank.setText("");

    }

    public void addRecords(View view) {
        insertRecorsd();

    }
}