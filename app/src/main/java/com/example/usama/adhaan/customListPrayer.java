package com.example.usama.adhaan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customListPrayer extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] names;
    private final Integer[] imgid;

    public customListPrayer(Activity context, String[] names, Integer[] imgid) {
        super(context, R.layout.activity_custom_list_prayer, names);

        this.context=context;
        this.names = names;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_custom_list_prayer, null,true);

        TextView titleText = rowView.findViewById(R.id.title);
        ImageView imageView = rowView.findViewById(R.id.icon);

        titleText.setText(names[position]);
        imageView.setImageResource(imgid[position]);
        //subtitleText.setText(urdu[position]);

        return rowView;
    }
}
