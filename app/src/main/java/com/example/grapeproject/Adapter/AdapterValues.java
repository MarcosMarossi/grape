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

        holder.title.setText("Cliente: " + value.getCustumer());
        holder.type.setText(value.getType());
        holder.quantity.setText("Quantidade: " + Integer.toString(value.getQuantity()));
        holder.date.setText(value.getDate());
        holder.value.setText("Parcial: RS "  + value.getValue());
        holder.price.setText("Preço: RS "  + value.getPrice());

        if (value.getType().equals("Amarela")) {
            holder.type.setTextColor(context.getResources().getColor(R.color.yellow));
        }
       if (value.getType().equals("Vermelha")) {
            holder.type.setTextColor(context.getResources().getColor(R.color.red));
        }
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, type, quantity, date, value, price;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById( R.id.txtAdapterTitle);
            type = itemView.findViewById(R.id.txtAdapterType);
            quantity = itemView.findViewById(R.id.txtAdapterQuantity);
            date = itemView.findViewById(R.id.txtAdapterDate);
            value = itemView.findViewById(R.id.txtAdapterValue);
            price = itemView.findViewById(R.id.txtAdapterPrice);
        }

    }
}
