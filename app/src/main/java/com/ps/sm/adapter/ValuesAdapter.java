package com.ps.sm.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.sm.R;
import com.ps.sm.databinding.AdapterAllItemsBinding;
import com.ps.sm.databinding.AdapterValuesBinding;

import java.util.ArrayList;


public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ViewHolder> {
    private String LOG_TAG = "ValuesAdapter";
    Context mContext;
    private AdapterValuesBinding binding;
    private LayoutInflater inflater;
    int val = 0;
    ArrayList<Integer> integerArrayList;

    public ValuesAdapter(Context context, int _val, ArrayList<Integer> _integerArrayList) {
        integerArrayList = _integerArrayList;
        val = _val;
        val = Math.round(val);
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.adapter_values, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        val = val + 10;
//        holder._binding.tvValue2.setText("" + val);
        holder._binding.tvValue2.setText("" + integerArrayList.get(position));
        int mid = 0 + ((integerArrayList.size() - 1) - 0) / 2;
        Log.d(LOG_TAG, "\t\tmid\t\t" + mid + "\n\t\t\t" + integerArrayList.get(mid));
        if (position == mid) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder._binding.tvValue2.setBackgroundColor(mContext.getColor(R.color.teal_200));
            }
        }
    }

    @Override
    public int getItemCount() {
        return integerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterValuesBinding _binding;

        public ViewHolder(@NonNull AdapterValuesBinding binding) {
            super(binding.getRoot());
            this._binding = binding;
        }
    }
}
