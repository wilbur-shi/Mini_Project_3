package com.example.wilbur.mini_project_3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Wilbur on 3/4/2016.
 */
public class CarCardAdapter extends RecyclerView.Adapter<CarCardAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<CarCard> carCardArray;

    public CarCardAdapter(Context context, ArrayList<CarCard> carCards) {
        this.context = context;
        carCardArray = carCards;
    }

    /* In simplified terms, a ViewHolder is an object that holds the pointers to the views in each
    each row. What does that mean? Every row has a TextView, ImageView, and CheckBox. Each row has
    a ViewHolder, and that ViewHolder holder these 3 views in it (hence "view holder").
    This function returns a single ViewHolder; it is called once for every row.
    */
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        This "inflates" the views, using the layout R.layout.row_view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    /* This function takes the previously made ViewHolder and uses it to actually display the
    data on the screen. Remember how the holder contains (pointers to) the 3 views? By doing, for
    example, "holder.imageView" we are accessing the imageView for that row and setting the
    ImageResource to be the corresponding image for that subject.
     */
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        CarCard carCard = carCardArray.get(position);

        holder.modelTextView.setText(carCard.model);
        holder.descriptionTextView.setText(carCard.description);
        holder.carImgView.setImageResource(carCard.imageId);
        holder.isUsedCheckBox.setChecked(carCard.isUsed);
    }

    @Override
    public int getItemCount() {
        return carCardArray.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView modelTextView;
        TextView descriptionTextView;
        ImageView carImgView;
        CheckBox isUsedCheckBox;

        public CustomViewHolder (View view) {
            super(view);
            this.modelTextView = (TextView) view.findViewById(R.id.modelTextView);
            this.descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
            this.carImgView = (ImageView) view.findViewById(R.id.carImgView);
            this.isUsedCheckBox = (CheckBox) view.findViewById(R.id.isUsedCheckBox);
            /*Think about what we said in the comment above onCreateViewHolder to determine the
            purpose of the ViewHolder. Does it make sense why we are doing this in the constructor?
            */

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Get adapter position is getting the number of the row that was clicked,
                    starting at 0
                    */
//                    Hint: Use getAdapterPosition();
                    String carModel = carCardArray.get(getAdapterPosition()).model;
                    Toast toast = Toast.makeText(context, carModel, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }
}
