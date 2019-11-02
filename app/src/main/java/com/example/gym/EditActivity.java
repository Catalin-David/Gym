package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanRecViewAdapter.DeletePlan {

    private RecyclerView recyclerView;
    private Button btnAddMorePlans;
    private TextView txtDay;
    private PlanRecViewAdapter adapter;

    @Override
    public void onDeletePlan(String day) {
        txtDay.setText(day);

        ArrayList<Plan> plans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals(day)){
                plans.add(plan);
            }
        }

        adapter.setPlans(plans);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();
        adapter = new PlanRecViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setType("edit");

        Intent intent = getIntent();
        try{
            String day = intent.getStringExtra("day");
            if(null != day){
                txtDay.setText(day);

                ArrayList<Plan> plans = new ArrayList<>();
                for(Plan plan:Utils.getUserPlans()){
                    if(plan.getDay().equals(day)){
                        plans.add(plan);
                    }
                }

                adapter.setPlans(plans);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        btnAddMorePlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditActivity.this, AllTrainingActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        btnAddMorePlans = findViewById(R.id.btnAddMorePlans);
        txtDay = findViewById(R.id.txtDay);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
