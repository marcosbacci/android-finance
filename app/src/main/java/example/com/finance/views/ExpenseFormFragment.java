package example.com.finance.views;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import example.com.finance.R;
import example.com.finance.controllers.ExpenseController;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFormFragment extends Fragment implements View.OnClickListener{

    private EditText valueField;
    private EditText descriptionField;
    private Spinner categoriesList;
    private EditText dateField;
    private CheckBox consolidated;
    private Button saveBtn;
    private DatePickerDialog.OnDateSetListener dateListner;
    private Button cancelBtn;

    public ExpenseFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_form, container, false);


        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);


        valueField = view.findViewById(R.id.valueField);
        descriptionField = view.findViewById(R.id.descriptionField);
        categoriesList = view.findViewById(R.id.categoriesList);
        setCategorySpinner(view.getContext());
        dateField = view.findViewById(R.id.dateField);
        setDateField();
        consolidated = view.findViewById(R.id.consolidatedField);
        saveBtn = view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.saveBtn){
                if(!valueField.getText().toString().isEmpty() && !descriptionField.getText().toString().isEmpty()){
                    getFieldsData();
                    Toast.makeText(view.getContext(),"Despesa salva", Toast.LENGTH_LONG).show();
                    backToHome();
                }else{
                    Snackbar.make(view,"Nenhum dos campos podem ser vazios!",Snackbar.LENGTH_LONG).setAction("OK",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }
                    ).show();
                    return;
                }
        }else if(view.getId() == R.id.cancelBtn){
            backToHome();
        }
    }

    private void backToHome(){
        /*((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);*/
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MainFragment()).commit();
    }

    private void getFieldsData(){
        float value = Float.parseFloat(valueField.getText().toString());
        String description = descriptionField.getText().toString();
        String date = dateField.getText().toString();
        String category = categoriesList.getSelectedItem().toString();
        boolean checkConsolidated = consolidated.isChecked();
        ExpenseController.newExpense(value,description,date,category,checkConsolidated);
    }

    private void setCategorySpinner(Context c){
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(
                c,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.expense_category)
        );

        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesList.setAdapter(categoriesAdapter);
    }

    private void setDateField(){
        Calendar cal = Calendar.getInstance();

        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        String dateString = Integer.toString(day) + '/' + Integer.toString(month+1) + '/' + Integer.toString(year);

        dateField.setText(dateString);
        dateField.setKeyListener(null);

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListner,
                        year,
                        month,
                        day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // data -> day/month/year
                String dateString = Integer.toString(day) + '/' + Integer.toString(month+1) + '/' + Integer.toString(year);
                dateField.setText(dateString);
            }
        };
    }
}
