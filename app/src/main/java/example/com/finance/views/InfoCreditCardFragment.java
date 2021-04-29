package example.com.finance.views;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.com.finance.R;
import example.com.finance.controllers.CreditCardController;
import example.com.finance.models.CreditCard;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCreditCardFragment extends Fragment implements View.OnClickListener{


    public InfoCreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_credit_card, container, false);

        TextView flagInfoTXT = view.findViewById(R.id.flagInfoTXT);
        TextView ownerInfoTXT = view.findViewById(R.id.ownerInfoTXT);

        CreditCard cc = CreditCardController.get();

        flagInfoTXT.setText(cc.getFlag());
        ownerInfoTXT.setText(cc.getOwner());

        Button changeLimitBTN = view.findViewById(R.id.changeLimitBTN);
        changeLimitBTN.setOnClickListener(this);
        Button resetLimitBTN = view.findViewById(R.id.resetLimitBTN);
        resetLimitBTN.setOnClickListener(this);

        return view;
    }

    public void manipulateLimit(){
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.valuedialog);
        TextView titleDialog = myDialog.findViewById(R.id.titleDialog);

        titleDialog.setText("Manipular limite");

        final EditText valueTXT = myDialog.findViewById(R.id.valueFieldDialog);
        Button confirmBtn = myDialog.findViewById(R.id.confirmBTNDialog);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(valueTXT.getText().toString());

                CreditCardController.changeLimit(value);

                myDialog.cancel();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new InfoCreditCardFragment()).commit();
            }
        });

        myDialog.show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.changeLimitBTN){
            manipulateLimit();
        }else if(view.getId() == R.id.resetLimitBTN){
            CreditCardController.resetLimit();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new InfoCreditCardFragment()).commit();
        }
    }
}
