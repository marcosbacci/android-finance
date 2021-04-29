package example.com.finance.views;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import example.com.finance.R;
import example.com.finance.controllers.BillController;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillFormFragment extends Fragment implements View.OnClickListener{

    EditText descriptionField, valueField, dateField;
    private DatePickerDialog.OnDateSetListener dateListner;

    public BillFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_form, container, false);

        descriptionField = view.findViewById(R.id.billDescriptionField);
        valueField = view.findViewById(R.id.billValueField);
        dateField = view.findViewById(R.id.billDateField);
        setDateField();

        Button saveBtn = view.findViewById(R.id.billSaveBTN);
        Button cancelBtn = view.findViewById(R.id.billCancelBTN);

        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        return view;
    }

    public void back(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BillListFragment()).commit();
    }

    public void getData(){
        String description = descriptionField.getText().toString();
        double value = Double.parseDouble(valueField.getText().toString());
        String date = dateField.getText().toString();
        BillController.createNew(description,value,date);
        Toast.makeText(getActivity().getApplicationContext(),"Conta criada", Toast.LENGTH_LONG).show();
        back();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.billSaveBTN:
                getData();
                break;
            case R.id.billCancelBTN:
                back();
                break;
        }
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
