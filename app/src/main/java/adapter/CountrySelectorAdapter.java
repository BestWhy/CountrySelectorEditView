package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.countryselectoreditview.R;

import java.util.ArrayList;

import bean.Country;

/******************************************
 * 类描述：
 *
 * @author: WangHaiYu
 * @time: 2018/5/22 09:45
 ******************************************/
public class CountrySelectorAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Country> mCountryArrayList;


    public CountrySelectorAdapter(Context context, ArrayList<Country> countryArrayList) {
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
