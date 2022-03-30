package com.ps.sm.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.sm.R;
import com.ps.sm.activity.StrategyActivity;
import com.ps.sm.databinding.AdapterCategoryBinding;
import com.ps.sm.databinding.AdapterResultBinding;
import com.ps.sm.dto.CategoryDTO;

import java.util.ArrayList;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private String TAG = "ResultAdapter";
    Context mContext;
    private AdapterResultBinding binding;
    private LayoutInflater inflater;
    public static ResultAdapter resultAdapter;

    public ResultAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        resultAdapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.adapter_result, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterResultBinding _binding;

        public ViewHolder(@NonNull AdapterResultBinding binding) {
            super(binding.getRoot());
            this._binding = binding;
        }
    }
}
