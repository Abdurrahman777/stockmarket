package com.ps.sm.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ps.sm.R;
import com.ps.sm.adapter.ValuesAdapter;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.ActivityFillValuesBinding;
import com.ps.sm.session.SessionManager;

import java.util.ArrayList;
import java.util.Collections;

public class FillValuesActivity extends StockMarketBaseActivity {
    private String TAG = "AddNextActivity";
    SessionManager sessionManager;
    Context mContext;
    ActivityFillValuesBinding binding;
    ValuesAdapter valuesAdapter;
    int _value = 0;
    ArrayList<Integer> integerArrayList;
    int lotValue = 0;
    int totalStrikeValue = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_next);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fill_values);
        initValues();
//        setItemAdapter();
        clickEvents();
    }

    private void clickEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                exitActivityAnimation();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, StrategyActivity.class));
                enterActivityAnimation();

            }
        });

        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.etCmp.getText().toString().trim().length() < 1) {
                    customToast("Enter CMP", true);
                } else {
                    integerArrayList.clear();
                    binding.linearResult.setVisibility(View.VISIBLE);
                    _value = Integer.parseInt(binding.etCmp.getText().toString().trim());
                    getNewValue();
                    getArrayValues();
                    setTopAdapter();
                }

            }
        });

    }

    String convertValue;

    private void getNewValue() {
        try {

            int vaaal = Integer.parseInt(binding.etCmp.getText().toString().trim());
            int lastdigit = (vaaal % 10);
            int _other = 0;
            if (lastdigit == 0 || lastdigit == 1 || lastdigit == 2 || lastdigit == 8 || lastdigit == 9) {
                _other = 0;
            }
            if (lastdigit == 3 || lastdigit == 4 || lastdigit == 5 || lastdigit == 6 || lastdigit == 7) {
                _other = 5;
            }
            convertValue = String.valueOf(vaaal);
            convertValue = convertValue.replace(String.valueOf(lastdigit), String.valueOf(_other));
            Log.d(TAG, "getNewValue: " + lastdigit + "\t\t\t" + _other + "\t\t\t\t" + convertValue);
            _value = Integer.parseInt(convertValue);
        } catch (Exception e) {
            Log.d(TAG, "getNewValue:Exception " + e);
        }

    }

    private void getArrayValues() {
        try {
            for (int i = totalStrikeValue; i > 0; i--) {
//                _value = _value - 10;
                _value = _value - lotValue;
                integerArrayList.add(_value);
            }
            Collections.reverse(integerArrayList);
//            integerArrayList.add(Integer.parseInt(binding.etCmp.getText().toString().trim()));
            integerArrayList.add(Integer.parseInt(convertValue));
//            _value = Integer.parseInt(binding.etCmp.getText().toString().trim());
            _value = Integer.parseInt(convertValue);
            for (int i = 0; i < totalStrikeValue; i++) {
//                _value = _value + 10;
                _value = _value + lotValue;
                integerArrayList.add(_value);
            }
            Log.d(TAG, "integerArrayList: " + integerArrayList.toString());
            Log.d(TAG, "integerArrayList:size " + integerArrayList.size());
        } catch (Exception e) {
            Log.d(TAG, "getArrayValues: " + e);
        }


    }

    private void initValues() {
        mContext = this;
        sessionManager = new SessionManager(mContext);
        binding.linearResult.setVisibility(View.GONE);
        integerArrayList = new ArrayList<>();
        lotValue = 10;
        totalStrikeValue = 10;
    }

    private void setTopAdapter() {
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        valuesAdapter = new ValuesAdapter(mContext, _value, integerArrayList);
        binding.recycleTopValues.setLayoutManager(horizontalLayoutManager1);
        binding.recycleTopValues.setAdapter(valuesAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        exitActivityAnimation();
    }
}