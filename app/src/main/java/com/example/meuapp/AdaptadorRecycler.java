package com.example.meuapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MyViewHolder> {
    Context context;
    ArrayList<Produto> lista;
    AdaptadorRecycler.OnItemClickListener listener;

    public AdaptadorRecycler(Context context, ArrayList<Produto> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layoutproduto, parent, false);
        return new AdaptadorRecycler.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.MyViewHolder holder, int position) {
        Produto p = lista.get(position);
        holder.nome.setText(p.getNome());
        holder.quantidade.setText(p.getQuantidade()+" itens");
        holder.preco.setText("R$"+p.getPreco());
        holder.itemView.setOnClickListener(click ->  {
            listener.onItemClick(p);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Produto p);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, quantidade, preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeCard);
            quantidade = itemView.findViewById(R.id.quantidadeCard);
            preco = itemView.findViewById(R.id.precoCard);
        }
    }
}
