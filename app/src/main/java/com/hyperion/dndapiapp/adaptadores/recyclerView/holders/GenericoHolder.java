package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;

public class GenericoHolder extends RecyclerView.ViewHolder {

    private final TextView tituloItem;
    private final TextView descripcionItem;

    public GenericoHolder(@NonNull View itemView) {
        super(itemView);

        tituloItem = itemView.findViewById(R.id.tituloItem);
        descripcionItem = itemView.findViewById(R.id.descripcionItem);
    }

    public void bindItem(GenericoRecyclerView item) {
        tituloItem.setText(item.getNombre());
        descripcionItem.setText(item.getDescripcion());
    }
}