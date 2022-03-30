package com.ps.sm.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ps.sm.R;
import com.ps.sm.adapter.AllItemsAdapter;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.ActivityHomeBinding;
import com.ps.sm.session.SessionManager;


public class HomeActivity extends StockMarketBaseActivity {
    private String TAG = "HomeActivity";
    SessionManager sessionManager;
    Context mContext;
    ActivityHomeBinding binding;
    AllItemsAdapter allItemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initValues();
        setItemAdapter();
        clickEvents();
    }

    private void clickEvents() {
        binding.imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ItemFilterActivity.class));
                openToTopAnimation();
            }
        });

        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AddActivity.class));
                enterActivityAnimation();
            }
        });

    }

    private void initValues() {
        mContext = this;
        sessionManager = new SessionManager(mContext);
    }

    private void setItemAdapter() {
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        allItemsAdapter = new AllItemsAdapter(mContext);
        binding.recycleAllItems.setLayoutManager(horizontalLayoutManager1);
        binding.recycleAllItems.setAdapter(allItemsAdapter);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }
}