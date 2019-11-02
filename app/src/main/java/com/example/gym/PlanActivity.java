package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    private RecyclerView mondayRecView, tuesdayRecView, wednesdayRecView, thursdayRecView, fridayRecView;
    private Button btnAddPlan, btnEditMonday, btnEditTuesday, btnEditWednesday, btnEditThursday, btnEditFriday;
    private NestedScrollView nestedScrollView;
    private RelativeLayout notAddedPlanRelLayout;

    private PlanRecViewAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initAdapters();
        initViews();
        initRecViews();

        if(Utils.getUserPlans().size()>0){
            notAddedPlanRelLayout.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
        }
        else{
            notAddedPlanRelLayout.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
        }

        setOnClickListeners();
    }

    private void setOnClickListeners(){
        btnEditMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Monday");
                startActivity(intent);
            }
        });
        btnEditTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Tuesday");
                startActivity(intent);
            }
        });
        btnEditWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Wednesday");
                startActivity(intent);
            }
        });
        btnEditThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Thursday");
                startActivity(intent);
            }
        });
        btnEditFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Friday");
                startActivity(intent);
            }
        });
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, AllTrainingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initRecViews(){
        mondayRecView.setAdapter(mondayAdapter);
        mondayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> mondayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals("Monday")){
                mondayPlans.add(plan);
            }
        }
        mondayAdapter.setPlans(mondayPlans);

        tuesdayRecView.setAdapter(tuesdayAdapter);
        tuesdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> tuesdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals("Tuesday")){
                tuesdayPlans.add(plan);
            }
        }
        tuesdayAdapter.setPlans(tuesdayPlans);

        wednesdayRecView.setAdapter(wednesdayAdapter);
        wednesdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> wednesdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals("Wednesday")){
                wednesdayPlans.add(plan);
            }
        }
        wednesdayAdapter.setPlans(wednesdayPlans);

        thursdayRecView.setAdapter(thursdayAdapter);
        thursdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> thursdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals("Thursday")){
                thursdayPlans.add(plan);
            }
        }
        thursdayAdapter.setPlans(thursdayPlans);

        fridayRecView.setAdapter(fridayAdapter);
        fridayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> fridayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUserPlans()){
            if(plan.getDay().equals("Friday")){
                fridayPlans.add(plan);
            }
        }
        fridayAdapter.setPlans(fridayPlans);
    }

    private void initAdapters(){
        mondayAdapter = new PlanRecViewAdapter(this);
        tuesdayAdapter = new PlanRecViewAdapter(this);
        wednesdayAdapter = new PlanRecViewAdapter(this);
        thursdayAdapter = new PlanRecViewAdapter(this);
        fridayAdapter = new PlanRecViewAdapter(this);
    }

    private void initViews(){
        mondayRecView = findViewById(R.id.recyclerViewMonday);
        tuesdayRecView = findViewById(R.id.recyclerViewTuesday);
        wednesdayRecView = findViewById(R.id.recyclerViewWednesday);
        thursdayRecView = findViewById(R.id.recyclerViewThursday);
        fridayRecView = findViewById(R.id.recyclerViewFriday);

        btnEditMonday = findViewById(R.id.BtnEditMonday);
        btnEditTuesday = findViewById(R.id.BtnEditTuesday);
        btnEditWednesday = findViewById(R.id.BtnEditWednesday);
        btnEditThursday = findViewById(R.id.BtnEditThursday);
        btnEditFriday = findViewById(R.id.BtnEditFriday);

        nestedScrollView = findViewById(R.id.nestedScrollView);
        notAddedPlanRelLayout = findViewById(R.id.notAddedPlanRelLayout);
        btnAddPlan = findViewById(R.id.btnAddPlan);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
