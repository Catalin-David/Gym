package com.example.gym;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlanRecViewAdapter extends RecyclerView.Adapter<PlanRecViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Plan> plans = new ArrayList<>();
    private String type="";

    public interface DeletePlan{
        void onDeletePlan (String day);
    }

    private DeletePlan deletePlan;

    public PlanRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public PlanRecViewAdapter(){
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.activityName.setText(plans.get(position).getTraining().getName());
        Glide.with(mContext)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.activityImage);
        holder.activityTime.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.activityShortDesc.setText(plans.get(position).getTraining().getShortDesc());

        if(plans.get(position).isAccomplished()){
            holder.emptyCheckBox.setVisibility(View.GONE);
            holder.filledCheckBox.setVisibility(View.VISIBLE);
        }
        else{
            holder.emptyCheckBox.setVisibility(View.VISIBLE);
            holder.filledCheckBox.setVisibility(View.GONE);
        }

        if(type.equals("edit")){
            holder.emptyCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Accomplished?")
                            .setMessage("Have you finished " + plans.get(position).getTraining().getName() + "?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    plans.get(position).setAccomplished(true);
                                    for(Plan plan: Utils.getUserPlans()){
                                        if(plan.equals(plans.get(position))){
                                            plan.setAccomplished(true);
                                        }
                                    }

                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
            });

            try{
                deletePlan = (DeletePlan) mContext;
            }catch (ClassCastException e){
                e.printStackTrace();
            }

            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete?")
                            .setMessage("Are you sure you want to delete " + plans.get(position).getTraining().getName() + " from your weekly plan?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Utils.removeUserPlan(plans.get(position));
                                    deletePlan.onDeletePlan(plans.get(position).getDay());
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.create().show();

                    return true;
                }
            });
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TrainingActivity.class);
                intent.putExtra("training", plans.get(position).getTraining());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView activityName, activityTime, activityShortDesc;
        private ImageView activityImage, emptyCheckBox, filledCheckBox;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.txtNamePlan);
            activityTime = itemView.findViewById(R.id.txtMinutes);
            activityShortDesc = itemView.findViewById(R.id.txtDescPlan);
            activityImage = itemView.findViewById(R.id.imagePlan);
            emptyCheckBox = itemView.findViewById(R.id.emptyCheckBox);
            filledCheckBox = itemView.findViewById(R.id.filledCheckBox);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }
}
