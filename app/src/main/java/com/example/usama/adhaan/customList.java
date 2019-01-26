package com.example.usama.adhaan;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] english;
    private final int imgid;

    public customList(Activity context, String[] maintitle,  int imgid) {
        super(context, R.layout.activity_custom_list, maintitle);

        this.context=context;
        this.english=maintitle;
        //this.urdu=subtitle;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_custom_list, null,true);

        TextView titleText = rowView.findViewById(R.id.title);
        ImageView imageView = rowView.findViewById(R.id.icon);

        titleText.setText(english[position]);
        imageView.setImageResource(imgid);
        //subtitleText.setText(urdu[position]);

        return rowView;
    }
}
