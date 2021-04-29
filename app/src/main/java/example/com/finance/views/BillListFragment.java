package example.com.finance.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.com.finance.BillsAdapter;
import example.com.finance.R;
import example.com.finance.controllers.BillController;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillListFragment extends Fragment {


    public BillListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_list, container, false);

        Button addNew = view.findViewById(R.id.addNewBillBTN);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BillFormFragment()).commit();
            }
        });

        RecyclerView billRecycler = view.findViewById(R.id.billsList);
        billRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        BillsAdapter ba = new BillsAdapter(BillController.getAll());
        billRecycler.setAdapter(ba);

        return view;
    }

}
