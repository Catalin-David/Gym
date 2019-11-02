package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements AskForDetailsDialog.GetDetails {

    @Override
    public void onGettingDetailsResult(Plan plan) {
        Utils.addToUserPlan(plan);
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("plan", plan);
        startActivity(intent);
    }

    private Button btnAdd;
    private TextView txtName, txtDesc;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();

        Intent intent = getIntent();
        try{
            final GymExercise incomingTraining = intent.getParcelableExtra("training");
            txtName.setText(incomingTraining.getName());
            txtDesc.setText(incomingTraining.getLongDesc());
            Glide.with(this)
                    .asBitmap()
                    .load(incomingTraining.getImageUrl())
                    .into(image);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AskForDetailsDialog dialog = new AskForDetailsDialog();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("training", incomingTraining);
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(), "ask for details");
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void initViews(){
        btnAdd = findViewById(R.id.btnAddToPlan);
        txtName = findViewById(R.id.trainingName);
        image = findViewById(R.id.trainingImg);
        txtDesc = findViewById(R.id.trainingDesc);
    }
}
