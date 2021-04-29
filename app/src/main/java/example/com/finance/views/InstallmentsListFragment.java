package example.com.finance.views;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.finance.InstallmentAdapter;
import example.com.finance.R;
import example.com.finance.controllers.InstallmentController;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstallmentsListFragment extends Fragment {


    public InstallmentsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_installments_list, container, false);

        Button addNew = view.findViewById(R.id.addNewBillBTN);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });


        RecyclerView installmentRec = view.findViewById(R.id.billsList);
        installmentRec.setLayoutManager(new LinearLayoutManager(getContext()));
        InstallmentAdapter adapter = new InstallmentAdapter(InstallmentController.getAll());
        installmentRec.setAdapter(adapter);

        return view;
    }

    void createDialog(){
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.installment_dialog);

        final EditText descriptionField = myDialog.findViewById(R.id.descriptionInstallmentDialog);
        final EditText valueField = myDialog.findViewById(R.id.valueInstallmentDialog);
        final EditText amountField = myDialog.findViewById(R.id.amountInstallmentDialog);
        Button confirmBTN = myDialog.findViewById(R.id.confirmInstallmentBTN);

        confirmBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = descriptionField.getText().toString();
                int amount = Integer.parseInt(amountField.getText().toString());
                double value = Double.parseDouble(valueField.getText().toString());

                InstallmentController.createNew(description,value,amount);
                Toast.makeText(getActivity().getApplicationContext(),"Parcelamento criado", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new InstallmentsListFragment()).commit();
                myDialog.cancel();
            }
        });


        myDialog.show();
    }


}
