package com.example.egzovar1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList abcList = new ArrayList<>();
    int bals, prieb, sum, pressed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        barChart = view.findViewById(R.id.idBarChart);
        lineChart = view.findViewById(R.id.lineChart);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            pressed = bundle.getInt("pressed");
            bals = getArguments().getInt("bals");
            prieb = getArguments().getInt("prieb");
            sum = getArguments().getInt("sum");

            if (pressed == 1)
                barChart();
            else
                lineChart();

            return view;
        }
        return view;
    }

    public void barChart() {
        abcList.add(new BarEntry(1f, bals));
        abcList.add(new BarEntry(2f, prieb));
        abcList.add(new BarEntry(3f, sum));
        barDataSet = new BarDataSet(abcList, "Stulpeline diagrama");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }

    public void lineChart() {
        abcList.add(new Entry(2f, bals));
        abcList.add(new Entry(4f, prieb));
        abcList.add(new Entry(6f, sum));
        lineDataSet = new LineDataSet(abcList, "Linijine diagrama");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }
}
