package com.example.usama.adhaan;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class prayerFragment extends Fragment {

    private Button button;
    private static final String TAG= "prayerFragment";
    public ListView list;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_prayer_fragment, container, false);

        list=view.findViewById(R.id.list);

        String[] names={"الفجر", "الظہر", "العصر", "المغرب", "العشاء" , "الجممہ"};
        Integer[] icons= {R.drawable.fajricon, R.drawable.zuhuricon, R.drawable.asricon, R.drawable.maghribicon, R.drawable.ishaicon, R.drawable.jummaicon};
        customListPrayer adapter= new customListPrayer(getActivity(), names, icons);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent= new Intent(getContext(), prayer.class);
                intent.putExtra("get", position);
                startActivity(intent);
            }
        });

        return view;
    }
}
