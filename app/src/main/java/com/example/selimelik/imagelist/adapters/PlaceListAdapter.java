package com.example.selimelik.imagelist.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selimelik.imagelist.R;
import com.example.selimelik.imagelist.pojos.place.PlaceResponse;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {

    private PlaceResponse places;
    private int rowLayout;
    private Context context;

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        LinearLayout linearLayout;

        public PlaceViewHolder(View v) {
            super(v);
            text1 = v.findViewById(R.id.textView1);
            text2 = v.findViewById(R.id.textView2);
            linearLayout = v.findViewById(R.id.customlinear);
            //   text3 = v.findViewById(R.id.textView3);
            //    text4 = v.findViewById(R.id.textView4);

        }
    }

    public PlaceListAdapter(PlaceResponse places, int rowLayout, Context context) {
        this.places = places;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceListAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListAdapter.PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.text1.setText(places.getVenues().get(i).getName());
        placeViewHolder.text2.setText(places.getVenues().get(i).getLocation().getState());
      placeViewHolder.linearLayout.setTag(placeViewHolder);
      placeViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              PlaceViewHolder viewHolder = (PlaceViewHolder) v.getTag();
              int position = viewHolder.getAdapterPosition();
              Toast.makeText(context,"sasa: "+places.getVenues().get(position).getName()+"",Toast.LENGTH_LONG).show();
          }
      });
        // placeViewHolder.text3.setText(places.getVenues().get(i).getLocation().getLat().toString());
        // placeViewHolder.text4.setText(places.getVenues().get(i).getLocation().getLng().toString());
    }

    @Override
    public int getItemCount() {
        return places.getVenues().size();
    }


}
