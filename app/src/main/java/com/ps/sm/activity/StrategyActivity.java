package com.ps.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ps.sm.R;
import com.ps.sm.adapter.CategoryAdapter;
import com.ps.sm.adapter.StrategyAdapter;
import com.ps.sm.adapter.ValuesAdapter;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.ActivityStrategyBinding;
import com.ps.sm.dto.CategoryDTO;
import com.ps.sm.dto.StrategyDTO;
import com.ps.sm.session.SessionManager;

import java.util.ArrayList;

public class StrategyActivity extends StockMarketBaseActivity {
    private String TAG = "StrategyActivity";
    SessionManager sessionManager;
    Context mContext;
    ActivityStrategyBinding binding;
    ArrayList<CategoryDTO> categoryDTOArrayList;
    CategoryAdapter categoryAdapter;
    StrategyAdapter strategyAdapter;
    public ArrayList<StrategyDTO> strategyDTOArrayList;
    public static StrategyActivity strategyActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_strategy);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_strategy);
        initValues();
        addItemsToArray();
        setCategoryAdapter();
        setStrategyAdapter();
        /*setArrayForStrategy(0, 10);*/
        clickEvents();
    }

    private void initValues() {
        strategyActivity = this;
        mContext = this;
        sessionManager = new SessionManager(mContext);
        binding.nestedView.setVisibility(View.GONE);
        categoryDTOArrayList = new ArrayList<>();
        strategyDTOArrayList = new ArrayList<>();
    }

    private void addItemsToArray() {
        categoryDTOArrayList.clear();
        CategoryDTO categoryDTO = new CategoryDTO("1", "Volatile");
        CategoryDTO categoryDTO2 = new CategoryDTO("2", "Non-Volatile");
        CategoryDTO categoryDTO3 = new CategoryDTO("3", "Bullish");
        CategoryDTO categoryDTO4 = new CategoryDTO("4", "Bearish");
        categoryDTOArrayList.add(categoryDTO);
        categoryDTOArrayList.add(categoryDTO2);
        categoryDTOArrayList.add(categoryDTO3);
        categoryDTOArrayList.add(categoryDTO4);
        Log.d(TAG, "addItemsToArray: " + categoryDTOArrayList.size());
    }

    private void setCategoryAdapter() {
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        categoryAdapter = new CategoryAdapter(mContext, categoryDTOArrayList);
        binding.recycleCategory.setLayoutManager(horizontalLayoutManager1);
        binding.recycleCategory.setAdapter(categoryAdapter);
    }

    private void setStrategyAdapter() {
        strategyAdapter = new StrategyAdapter(mContext, strategyDTOArrayList);
        binding.recycleStrategy.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recycleStrategy.setAdapter(strategyAdapter);
    }


    public void setArrayForStrategy(int loopStart, int loopStop) {
        strategyDTOArrayList.clear();
        for (int i = loopStart; i < loopStop; i++) {
            StrategyDTO strategyDTO = new StrategyDTO("" + i, ("strategy-" + i));
            strategyDTOArrayList.add(strategyDTO);
        }
        if (CategoryAdapter.categoryAdapter != null) {
            if (CategoryAdapter.categoryAdapter.selectedPosition == -1) {
                strategyDTOArrayList.clear();
            }
        }

        strategyAdapter.notifyDataSetChanged();
    }

    private void clickEvents() {

        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etBank.getText().toString().trim().length() < 1) {
                    customToast("Enter name", true);
                } else {
                    binding.nestedView.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();

            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                exitActivityAnimation();
            }
        });


    }

    private void checkValidation() {
        if (binding.etQuantity.getText().toString().trim().length() < 1) {
            customToast("Enter Quantity", true);
        } else if (CategoryAdapter.categoryAdapter.selectedPosition == -1) {
            customToast("Select Category", true);
        } else if (StrategyAdapter.strategyAdapter.selectedPosition == -1) {
            customToast("Select Strategy", true);
        } else {

            String catName = categoryDTOArrayList.get(CategoryAdapter.categoryAdapter.selectedPosition).getName();
            String strategyName = strategyDTOArrayList.get(StrategyAdapter.strategyAdapter.selectedPosition).getName();

            startActivity(new Intent(mContext, ResultActivity.class)
                    .putExtra("catName", catName)
                    .putExtra("strategyName", strategyName)
                    .putExtra("quantity", binding.etQuantity.getText().toString().trim())
                    .putExtra("bankName", binding.etBank.getText().toString().trim())
                    .putExtra("buyValue", binding.tvBuyValue.getText().toString().trim())
                    .putExtra("sellValue", binding.tvSellValue.getText().toString().trim())
            );
            enterActivityAnimation();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        exitActivityAnimation();
    }
}