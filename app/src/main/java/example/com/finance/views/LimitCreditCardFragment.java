package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import example.com.finance.controllers.CreditCardController;
import example.com.finance.models.CreditCard;
import example.com.finance.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LimitCreditCardFragment extends Fragment {


    public LimitCreditCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_limit_credit_card, container, false);

        TextView currentLimitTXT = view.findViewById(R.id.currentLimitTXT);

        CreditCard cc = CreditCardController.get();

        if(cc != null){
            currentLimitTXT.setText(String.format("%.2f", cc.getCurrentLimit()) +" "+getString(R.string.coin));
        }else{
            currentLimitTXT.setText(String.format("%.2f",0.0) + " "+getString(R.string.coin));
        }

        return view;
    }

}
