package com.example.grapeproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.grapeproject.Models.Values;
import com.example.grapeproject.R;

import java.util.List;

public class AdapterValues extends RecyclerView.Adapter<AdapterValues.MyViewHolder> {

    List<Values> movimentacoes;
    Context context;

    public AdapterValues(List<Values> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Values value = movimentacoes.get(position);

        holder.titulo.setText("Comprador: " + value.getCustumer());
        holder.valor.setText(value.getType());
        holder.categoria.setText("Quantidade: " + Integer.toString(value.getQuantity()));
        holder.data.setText(value.getDate());
        holder.value.setText("Valor: RS "  + value.getValue());

        if (value.getType().equals("Amarela")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        if (value.getType().equals("Vermelha")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.red));
        }
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria, data, value;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
            data = itemView.findViewById(R.id.txtAdapterDate);
            value = itemView.findViewById(R.id.textAdapterValue);
        }

    }
}
