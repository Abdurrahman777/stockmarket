package com.ps.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ps.sm.R;
import com.ps.sm.adapter.AllItemsAdapter;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.ActivityAddBinding;
import com.ps.sm.databinding.ActivityHomeBinding;
import com.ps.sm.session.SessionManager;

public class AddActivity extends StockMarketBaseActivity {
    private String TAG = "AddActivity";
    SessionManager sessionManager;
    Context mContext;
    ActivityAddBinding binding;
    public static AddActivity addActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        initValues();
        clickEvents();
    }

    private void initValues() {
        addActivity = this;
        mContext = this;
        sessionManager = new SessionManager(mContext);
    }

    private void clickEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                exitActivityAnimation();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(mContext, AddNextActivity.class));
//                enterActivityAnimation();
                finish();
                exitActivityAnimation();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        exitActivityAnimation();
    }
}