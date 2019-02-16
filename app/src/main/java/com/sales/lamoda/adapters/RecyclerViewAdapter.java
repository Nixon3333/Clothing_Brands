package com.sales.lamoda.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.sales.lamoda.Offer;
import com.sales.lamoda.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.sales.lamoda.MainActivity.tabLayout;
import static com.sales.lamoda.fragments.WebFragment.mWebView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private ImageView imageView;
        private RoundedImageView roundedImageView;
        private TextView tvPrice;
        private Button btBuy;


        public ViewHolder(final View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.imagePhoto);
            //roundedImageView = itemView.findViewById(R.id.roundedPhoto);
            btBuy = itemView.findViewById(R.id.btBuy);


        }

    }

    private List<Offer> list;

    private Context context;


    public RecyclerViewAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Offer offer = list.get(i);
        viewHolder.itemName.setText(offer.getName());
        viewHolder.tvPrice.setText(offer.getPrice() + "р.");


        Picasso.get().load(offer.getPictures()
                .get(0))

                /*.transform(new CircleTransform())*/
                //.into(viewHolder.roundedImageView);
        .into(viewHolder.imageView);
        /*viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showPic();
                android.app.FragmentManager f = context.getFragmentManager();
                android.app.FragmentTransaction ft = f.beginTransaction();
                android.app.Fragment prev = f.findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

// Create and show the dialog.
                DialogFragment newFragment = new DetailsDialogFragment();
                newFragment.show(ft, "dialog");
            }
        });*/
        viewHolder.btBuy.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                sendURL(tabLayout, offer.getUrl());

            }

        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void sendURL(TabLayout tabLayout, String s) {

        mWebView.loadUrl(s);
        final TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();


    }

}
