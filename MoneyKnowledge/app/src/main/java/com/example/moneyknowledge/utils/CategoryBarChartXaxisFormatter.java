package com.example.moneyknowledge.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class CategoryBarChartXaxisFormatter extends ValueFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public CategoryBarChartXaxisFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int val = (int)value;
        String label="";
        if(val>=0 && val<mValues.length) {
            label= mValues[val];
        }
        else {
            label= "";
        }
        return label;
    }
}