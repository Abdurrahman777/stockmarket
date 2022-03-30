package com.ps.sm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.sm.R;
import com.ps.sm.activity.FillValuesActivity;
import com.ps.sm.common.StockMarketBaseActivity;
import com.ps.sm.databinding.AdapterAllItemsBinding;


public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.ViewHolder> {
    private String LOG_TAG = "AllItemsAdapter";
    Context mContext;
    private AdapterAllItemsBinding binding;
    private LayoutInflater inflater;
    StockMarketBaseActivity baseActivity;

    public AllItemsAdapter(Context context) {

        mContext = context;
        inflater = LayoutInflater.from(mContext);
        baseActivity = (StockMarketBaseActivity) mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.adapter_all_items, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder._binding.linearDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, FillValuesActivity.class));
                baseActivity.enterActivityAnimation();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterAllItemsBinding _binding;

        public ViewHolder(@NonNull AdapterAllItemsBinding binding) {
            super(binding.getRoot());
            this._binding = binding;
        }
    }
}
