package com.countryselectoreditview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.CountrySelectorAdapter;
import bean.Country;
import view.CountrySelectorEditView;

public class MainActivity extends AppCompatActivity {

    private CountrySelectorEditView mCountrySelector;
    private ArrayList<Country> mArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        mCountrySelector = findViewById(R.id.country_selector);


        for (int i = 0; i < 10; i++) {
            Country country = new Country();
            if (i % 2 == 0) {
                country.countryCode = "+44";
                country.countryName = "GBA";

            } else {
                country.countryCode = "+31";
                country.countryName = "FRA";
            }
            mArrayList.add(country);
        }


        CountrySelectorAdapter adapter = new CountrySelectorAdapter(this, mArrayList);
        mCountrySelector.setCountryListAdapter(adapter);

    }


}
