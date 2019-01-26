package com.example.usama.adhaan;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class duaFragment extends Fragment {

    private Button button;
    private static final String TAG= "duaFragment";

    public ListView list;
    public String message;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_dua_fragment, container, false);


        final String[] urdu= getResources().getStringArray(R.array.urdu);
        int img= R.drawable.duahands;


        customList adapter= new customList(getActivity(),  urdu, img);
        list=view.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent= new Intent(getContext(), dua.class);
               intent.putExtra("get", position);
               startActivity(intent);
            }
        });

        return view;
    }
}
