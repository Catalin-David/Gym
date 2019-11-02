package com.example.gym;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class AskForDetailsDialog extends DialogFragment {

    private EditText timeEditText;
    private Button cancelBtn, addBtn;
    private Spinner spinner;


    public interface GetDetails{
        void onGettingDetailsResult(Plan plan);
    }

    private GetDetails getDetails;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_ask_for_details, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Enter details")
                .setView(view);

        initViews(view);

        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, days);
        spinner.setAdapter(adapter);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        try{
            getDetails = (GetDetails) getActivity();
        }catch (ClassCastException e){
            e.printStackTrace();
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                GymExercise training = bundle.getParcelable("training");
                if (null != training) {
                    Plan plan = new Plan();
                    plan.setAccomplished(false);
                    plan.setDay(spinner.getSelectedItem().toString());
                    plan.setMinutes(Integer.valueOf(timeEditText.getText().toString()));
                    plan.setTraining(training);
                    getDetails.onGettingDetailsResult(plan);
                }
            }
        });

        return builder.create();
    }

    private void initViews(View view){
        timeEditText = view.findViewById(R.id.edtTxtMinutes);
        cancelBtn = view.findViewById(R.id.btnCancel);
        addBtn = view.findViewById(R.id.btnAdd);
        spinner = view.findViewById(R.id.spinnerDay);
    }
}
