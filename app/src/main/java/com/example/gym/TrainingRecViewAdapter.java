package com.example.gym;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TrainingRecViewAdapter extends RecyclerView.Adapter<TrainingRecViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<GymExercise> trainings = new ArrayList<>();

    public TrainingRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TrainingRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(trainings.get(position).getName());
        holder.txtTags.setText(trainings.get(position).getShortDesc());

        Glide.with(mContext)
                .asBitmap()
                .load(trainings.get(position).getImageUrl())
                .into(holder.image);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TrainingActivity.class);
                intent.putExtra("training", trainings.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtTags;
        private ImageView image;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.exerciseName);
            txtTags = itemView.findViewById(R.id.exerciseTags);
            image = itemView.findViewById(R.id.exerciseImage);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public void setTrainings(ArrayList<GymExercise> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }
}
