package com.example.jhonarendra.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Jhonarendra on 4/10/2018.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private Context mCtx;
    private List<Mahasiswa> mahasiswaList;


    public MahasiswaAdapter(Context mCtx, List<Mahasiswa> mahasiswaList) {
        this.mCtx = mCtx;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_recycle,null);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);

        holder.textViewTitle.setText(mahasiswa.getTitle());
        holder.textViewDesc.setText(mahasiswa.getDesc());
        holder.textViewHarga.setText(mahasiswa.getHarga());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewDesc, textViewHarga;


        public MahasiswaViewHolder(View itemView) {
            super(itemView);


            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewHarga = itemView.findViewById(R.id.textViewHarga);

        }
    }
}
