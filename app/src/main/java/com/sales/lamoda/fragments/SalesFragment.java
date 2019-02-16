package com.sales.lamoda.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sales.lamoda.Offer;
import com.sales.lamoda.R;
import com.sales.lamoda.adapters.RecyclerViewAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SalesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Offer> offerList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            xmlParse();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gridLayoutManager = new GridLayoutManager(getContext(), 2);
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            gridLayoutManager = new GridLayoutManager(getContext(), 3);



        System.out.println("Start size " + offerList.size());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_sales, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(offerList, getContext());



        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(gridLayoutManager);


        return v;
    }

    private void xmlParse() throws XmlPullParserException, IOException {
        String name = "";
        String price = "";
        String url = "";
        String pic;

        ArrayList<String> pictures = new ArrayList<>();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        InputStream fis = getActivity().getAssets().open("top.xml");
        xpp.setInput(fis, null);
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if (eventType == XmlPullParser.START_TAG) {
                System.out.println("Start TAG " + xpp.getName());
                if (xpp.getName().equals("price")) {
                    price = xpp.nextText();
                    System.out.println("Start price " + price);
                } else if (xpp.getName().equals("name")) {
                    name = xpp.nextText();
                    System.out.println("Start name " + name);
                } else if (xpp.getName().equals("picture")) {
                    pic = xpp.nextText();
                    pictures.add(pic);

                } else if (xpp.getName().equals("url"))
                    url = xpp.nextText();
            }
            if (!name.isEmpty() & !price.isEmpty() & !url.isEmpty() & pictures.size() != 0) {
                ArrayList<String> mypic = new ArrayList<>(pictures);
                Offer offer = new Offer(name, price, url, mypic);
                offerList.add(offer);
                name = "";
                price = "";
                url = "";
                pictures.clear();
            }
            eventType = xpp.next();
        }
        System.out.println("End document");
    }
}
