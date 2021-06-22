package com.example.activity9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLihatData extends RecyclerView.Adapter<AdapterLihatData.ViewHolder> {

    private ArrayList<Barang> daftarBarang;
    private Context context;
    public AdapterLihatData(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = daftarBarang.get(position).getNama();
        holder.tvTitle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //next act untuk fungsi detail data
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        //mengembalikan jumlah item pada barang
        return daftarBarang.size();
    }
}
