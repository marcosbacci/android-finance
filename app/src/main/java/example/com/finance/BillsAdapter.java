package example.com.finance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.com.finance.controllers.BillController;
import example.com.finance.models.Bill;

public class BillsAdapter extends RecyclerView.Adapter<BillHolder>{

    private List<Bill> billList;

    public BillsAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @Override
    public BillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item,parent,false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(final BillHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.description.setText(bill.getDescription());
        holder.value.setText(String.format("%.2f",bill.getValue())+" R$");
        holder.date.setText(bill.getDay()+"/"+bill.getMonth()+"/"+bill.getYear());
        holder.billID = bill.getId();
        holder.payBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BillController.setPayed(holder.billID);
                int id = holder.getAdapterPosition();
                billList.remove(id);
                notifyItemRemoved(id);
                notifyItemRangeChanged(id,billList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return billList.size();
    }
}
