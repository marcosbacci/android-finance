package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import example.com.finance.R;
import example.com.finance.controllers.CreditCardController;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormCreditCardFragment extends Fragment {

    EditText flagField, ownerField,limitField;

    public FormCreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_form_credit_card, container, false);

        flagField = view.findViewById(R.id.flagCreditCardField);
        ownerField = view.findViewById(R.id.ownerCreditCardField);
        limitField = view.findViewById(R.id.limitCreditCardField);

        Button saveBtn = view.findViewById(R.id.saveCreditCardBTN);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInfo();
                showInfo();
            }
        });

        return view;
    }

    public void getInfo(){
        String flag = flagField.getText().toString();
        String owner = ownerField.getText().toString();
        double limit = Double.parseDouble(limitField.getText().toString());
        CreditCardController.create(flag,owner,limit);
    }

    public void showInfo(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new InfoCreditCardFragment()).commit();
    }
}
