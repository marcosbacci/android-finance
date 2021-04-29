package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.finance.R;
import example.com.finance.controllers.BankController;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBalanceFragment extends Fragment {


    public CurrentBalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_balance, container, false);

        TextView balanceResultTXT = view.findViewById(R.id.balanceResultTXT);
        balanceResultTXT.setText(String.format("%.2f",BankController.getBalance()) +" "+getString(R.string.coin));

        return  view;
    }

}
