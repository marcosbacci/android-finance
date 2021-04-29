package example.com.finance;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import example.com.finance.controllers.EarningController;
import example.com.finance.controllers.ExpenseController;
import example.com.finance.helpers.MonthName;


/**
 * A simple {@link Fragment} subclass.
 */
public class SheetFragment extends Fragment {


    public SheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sheet, container, false);

        PieChart expensesChart = view.findViewById(R.id.expensesChart);
        PieChart earningsChart = view.findViewById(R.id.earningsChart);

        setPieChart(expensesChart,1,"Gastos","Nenhum gasto cadastrado");
        setPieChart(earningsChart,2,"Ganhos","Nenhum ganho cadastrado");

        return view;
    }

    private void setPieChart(PieChart pieChart,int type,String label, String noDataString){
        // Configurações basicas
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        // Valores do grafico
        ArrayList<PieEntry> values = new ArrayList<>();

        for(int i =1;i <= 12;i++){
            String monthName = MonthName.getMonthName(i);
            float value;
            switch (type){
                case 1:
                    value = ExpenseController.getMonthExpense(i);
                    break;
                case 2:
                    value = EarningController.getMonthEarning(i);
                    break;
                default:
                    value = 0;
                    break;
            }

            if(value > 0.5){
                values.add(new PieEntry(value,monthName));
            }
        }


        if(values.size() > 0){
            PieDataSet dataSet = new PieDataSet(values,label);
            dataSet.setSliceSpace(1f);
            dataSet.setSelectionShift(1f);
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            PieData data = new PieData(dataSet);
            data.setValueTextSize(16f);
            data.setValueTextColor(Color.WHITE);

            pieChart.setData(data);
        }else{
            pieChart.setNoDataText(noDataString);
        }
    }

}
