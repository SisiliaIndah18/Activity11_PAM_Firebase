package com.example.activity9;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLihatData extends RecyclerView.Adapter<AdapterLihatData.ViewHolder> {

    private ArrayList<Barang> daftarBarang;
    private Context context;
    private DatabaseReference databaseReference;
    public AdapterLihatData(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);

            databaseReference = FirebaseDatabase.getInstance().getReference();
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

        String kode, nama, telpon;

        nama = daftarBarang.get(position).getNama();
        kode = daftarBarang.get(position).getKode();
        telpon = daftarBarang.get(position).getTelpon();

        holder.tvTitle.setText(nama);

        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.menuteman);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Bundle bundle = new Bundle();
                                bundle.putString("Kunci1", kode);
                                bundle.putString("Kunci2", nama);
                                bundle.putString("Kunci3", telpon);
                                Intent intent = new Intent(v.getContext(), TemanEdit.class);
                                intent.putExtras(bundle);
                                v.getContext().startActivity(intent);
                                break;

                            case R.id.mnHapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                                alertDlg.setTitle("Yakin data" + nama + " akan dihapus ?");
                                alertDlg.setMessage("Tekan 'Ya' untuk menghapus").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DeleteData(kode);
                                        Toast.makeText(v.getContext(), "Data" + nama + "berhasil dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                                        v.getContext().startActivity(intent);
                                    }
                                })
                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog aDlg = alertDlg.create();
                                aDlg.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }

    public void DeleteData(String kode){
        if (databaseReference != null) {
            databaseReference.child("Barang").child(kode).removeValue();
        }
    }
}
