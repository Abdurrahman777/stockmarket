package com.ps.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ps.sm.R;
import com.ps.sm.adapter.ResultAdapter;
import com.ps.sm.adapter.ValuesAdapter;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.ActivityResultBinding;
import com.ps.sm.session.SessionManager;

public class ResultActivity extends StockMarketBaseActivity {
    private String TAG = "ResultActivity";
    SessionManager sessionManager;
    Context mContext;
    ActivityResultBinding binding;
    Bundle bundle;
    ResultAdapter resultAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_result);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        initValues();
        setValues();
        clickEvents();
        setResultAdapter();
    }

    private void setValues() {
        try {
            if (bundle != null) {
                binding.tvBankName.setText(bundle.getString("bankName"));
                binding.tvCategory.setText(bundle.getString("catName"));
                binding.tvStrategy.setText(bundle.getString("strategyName"));
                binding.tvQuantity.setText("Quantity-" + bundle.getString("quantity"));
                binding.tvBuyValue.setText("Buy-" + bundle.getString("buyValue"));
                binding.tvSellValue.setText("Sell-" + bundle.getString("sellValue"));

            }
        } catch (Exception e) {
            Log.d(TAG, "setValues:Error\t\t\t " + e);
        }
    }

    private void initValues() {
        mContext = this;
        sessionManager = new SessionManager(mContext);
        bundle = getIntent().getExtras();
    }

    private void clickEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                exitActivityAnimation();
            }
        });

        binding.imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, FillValuesActivity.class));
                openToTopAnimation();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        exitActivityAnimation();
    }

    private void setResultAdapter() {
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        resultAdapter = new ResultAdapter(mContext);
        binding.recycleResultView.setLayoutManager(horizontalLayoutManager1);
        binding.recycleResultView.setAdapter(resultAdapter);

    }
}