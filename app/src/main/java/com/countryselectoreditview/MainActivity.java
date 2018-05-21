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


        MyAdapter adapter = new MyAdapter(this, mArrayList);
        mCountrySelector.setCountryListAdapter(adapter);

    }

    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<Country> mCountryArrayList;


        public MyAdapter(Context context, ArrayList<Country> countryArrayList) {
            mContext = context;
            this.mCountryArrayList = countryArrayList;
        }

        @Override
        public int getCount() {
            return mCountryArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mCountryArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_country_list, parent, false);
                viewHolder.mIvCountry = convertView.findViewById(R.id.iv_item_country_selector);
                viewHolder.mTvCountry = convertView.findViewById(R.id.tv_item_country_selector);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            Country country = mCountryArrayList.get(position);
            if (country.countryCode.equals("+44")) {
                viewHolder.mIvCountry.setImageResource(R.mipmap.icon_uk);
                viewHolder.mTvCountry.setText(country.countryCode);
            } else {
                viewHolder.mIvCountry.setImageResource(R.mipmap.icon_fra);
                viewHolder.mTvCountry.setText(country.countryCode);
            }


            return convertView;
        }

        private class ViewHolder {
            ImageView mIvCountry;
            TextView mTvCountry;
        }
    }
}
