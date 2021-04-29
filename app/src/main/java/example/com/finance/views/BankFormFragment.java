package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import example.com.finance.R;
import example.com.finance.controllers.BankController;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankFormFragment extends Fragment implements View.OnClickListener{

    private EditText bankTXT,ownerTXT,agencyTXT,balanceTXT;
    private Button saveBankBTN, cancelBankBTN;

    public BankFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_form, container, false);


        bankTXT = view.findViewById(R.id.bankTXT);
        ownerTXT = view.findViewById(R.id.ownerTXT);
        agencyTXT = view.findViewById(R.id.agencyTXT);
        balanceTXT = view.findViewById(R.id.balanceTXT);
        saveBankBTN = view.findViewById(R.id.saveBankBTN);
        cancelBankBTN = view.findViewById(R.id.cancelBANKBTN);

        saveBankBTN.setOnClickListener(this);
        cancelBankBTN.setOnClickListener(this);


        return view;
    }

    void saveBankAccount(){
        String bank = bankTXT.getText().toString();
        String owner = ownerTXT.getText().toString();
        String agency = agencyTXT.getText().toString();
        double balance = Double.parseDouble(balanceTXT.getText().toString());
        BankController.create(owner,balance,bank,agency);
        //Toast.makeText(getActivity().getApplicationContext(),"Banco criado", Toast.LENGTH_LONG).show();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BankInfoFragment()).commit();


    }

    void cancel(){
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentContainer,new NoBankFragment()).commit();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.saveBankBTN){
            saveBankAccount();
        }else if(view.getId() == R.id.cancelBANKBTN){
            cancel();
        }
    }
}
