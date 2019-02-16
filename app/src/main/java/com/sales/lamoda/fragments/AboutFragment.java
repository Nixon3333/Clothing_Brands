package com.sales.lamoda.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sales.lamoda.MainActivity;
import com.sales.lamoda.R;

public class AboutFragment extends Fragment {

    private TextView tvAbout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.counter = 3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        tvAbout = v.findViewById(R.id.tvAbout);
        Log.d("count", String.valueOf(MainActivity.counter));
        return v;
    }
}
