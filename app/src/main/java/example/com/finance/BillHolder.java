package example.com.finance;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillHolder extends RecyclerView.ViewHolder{

    public String billID;
    public TextView description, value, date;
    public Button payBTN;

    public BillHolder(View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.billItemDescription);
        value = itemView.findViewById(R.id.billItemValue);
        date = itemView.findViewById(R.id.billItemDate);
        payBTN = itemView.findViewById(R.id.payBTN);

    }

}
