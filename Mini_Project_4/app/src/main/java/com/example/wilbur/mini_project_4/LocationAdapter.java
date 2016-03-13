package com.example.wilbur.mini_project_4;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wilbur on 3/10/2016.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<Location> locationArrayList;

    public LocationAdapter(Context context, ArrayList<Location> locations) {
        this.context = context;
        locationArrayList = locations;
    }

    public void setList(ArrayList<Location> locs) {
        locationArrayList = locs;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        Location location = locationArrayList.get(position);
        viewHolder.nameTextView.setText(location.name);
        viewHolder.descriptionTextView.setText(location.description);
        byte[] uploadedImage = location.imageByteArray;
        if (uploadedImage != null) {
            Bitmap bMap = BitmapFactory.decodeByteArray(uploadedImage, 0, uploadedImage.length);
            viewHolder.locationImageView.setImageBitmap(bMap);
        }
    }

    @Override
    public int getItemCount() {
        return locationArrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView locationImageView;
        public CustomViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
            locationImageView = (ImageView) view.findViewById(R.id.locationImageView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: DO PARSE STUFF?
                    Intent displayIntent = new Intent(context, DisplayLocationDetailsActivity.class);
                    displayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // TODO: Somehow tell the intent what position this view is so that if they edit info, it will save
                    Bundle bundle = new Bundle();
                    bundle.putString("name", nameTextView.getText().toString());
                    bundle.putString("description", descriptionTextView.getText().toString());
                   //FIXME: CHANGE SO THAT IT GETS THE IMAGE FROM THE PARSE OBJECT? bundle.putInt("imageId", locationImageView.get)
                    displayIntent.putExtras(bundle);
                    if (Build.VERSION.SDK_INT > 21) {
                        //ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(context, nameTextView, "agreedname");
                    }
                    else {
                        //context.startActivity(displayIntent);
                    }
                    context.startActivity(displayIntent);

                }
            });
        }

    }
}
