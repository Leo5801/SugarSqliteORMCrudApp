package com.example.sugarsqliteormcrudapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sugarsqliteormcrudapp.R;
import com.example.sugarsqliteormcrudapp.entitys.Populations;

import java.util.ArrayList;

public class PopulationAdapters extends BaseAdapter {

    private Context context;
    private ArrayList<Populations> arrayList;

    public PopulationAdapters(Context context, ArrayList<Populations> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context , R.layout.list_display_record,null);
        }

        TextView countryName = view.findViewById(R.id.county_name_text_view);
        TextView countryPopulatiuon = view.findViewById(R.id.county_population_text_view);
        TextView countryRank = view.findViewById(R.id.county_rank_text_view);

        Populations populations = arrayList.get(i);

        countryName.setText(populations.getCountryName());
        countryPopulatiuon.setText("" + populations.getCountryPopulation());
        countryRank.setText("" + populations.getCountryRanking());

        return view;
    }
}
