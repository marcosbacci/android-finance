package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.finance.R;
import example.com.finance.controllers.EarningController;
import example.com.finance.controllers.ExpenseController;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container, false);


        TextView expensesTXT = view.findViewById(R.id.expensesTXT);
        float totalExpenses = ExpenseController.getMonthTotal();
        String totalExpensesTXT = String.format("%.2f",totalExpenses);
        expensesTXT.setText(totalExpensesTXT + " " + getString(R.string.coin));

        TextView earningsTXT = view.findViewById(R.id.earningsTXT);
        float totalEarnings = EarningController.getMonthTotal();
        String totalEarningsTXT = String.format("%.2f",totalEarnings);
        earningsTXT.setText(totalEarningsTXT + " " + getString(R.string.coin));

        TextView economyTXT = view.findViewById(R.id.economyTXT);
        float economyTotal = totalEarnings - totalExpenses;
        String economyTotalTXT = String.format("%.2f",economyTotal);
        economyTXT.setText(economyTotalTXT + " " + getString(R.string.coin));

        if(economyTotal > 0){
            // Define a cor positiva
            economyTXT.setTextColor(getResources().getColor(R.color.economy_positive));
        }else if(economyTotal < 0){
            // Define a cor negativa
            economyTXT.setTextColor(getResources().getColor(R.color.economy_negative));
        }

       return view;
    }

}
