package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.com.finance.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoCreditCardFragment extends Fragment {


    public NoCreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_no_credit_card, container, false);

        Button newCreditCardBTN = view.findViewById(R.id.newBankBTN);

        newCreditCardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FormCreditCardFragment()).commit();
            }
        });


        return view;
    }

}
