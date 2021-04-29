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
import example.com.finance.controllers.BankController;
import example.com.finance.models.Bank;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankInfoFragment extends Fragment implements View.OnClickListener{

    public BankInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_bank_info, container, false);

        TextView bankInfo = view.findViewById(R.id.bankInfo);
        TextView ownerInfo = view.findViewById(R.id.ownerInfo);
        TextView agencyInfo = view.findViewById(R.id.agencyInfo);

        Bank bankTmp = BankController.get();

        bankInfo.setText(bankTmp.getName());
        ownerInfo.setText(bankTmp.getOwner());
        agencyInfo.setText(bankTmp.getAgency());

        Button depositBtn = view.findViewById(R.id.depositBtn);
        Button withdrawBtn = view.findViewById(R.id.withdrawBTN);

        depositBtn.setOnClickListener(this);
        withdrawBtn.setOnClickListener(this);

        return view;
    }

    public void changeBalance(final boolean isDeposit){
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.valuedialog);
        TextView titleDialog = myDialog.findViewById(R.id.titleDialog);

        if(isDeposit){
            titleDialog.setText("Deposito");
        }else{
            titleDialog.setText("Saque");
        }

        final EditText valueTXT = myDialog.findViewById(R.id.valueFieldDialog);
        Button confirmBtn = myDialog.findViewById(R.id.confirmBTNDialog);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(valueTXT.getText().toString());
                if(isDeposit){
                    BankController.deposit(value);
                }else{
                    BankController.withdraw(value);
                }

                myDialog.cancel();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new BankInfoFragment()).commit();
            }
        });

        myDialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.depositBtn:
                changeBalance(true);
                break;
            case R.id.withdrawBTN:
                changeBalance(false);
                break;
        }
    }
}
