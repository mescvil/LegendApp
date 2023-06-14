package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;

public class GenericoFichaPersonajeHolder extends RecyclerView.ViewHolder {

    private final TextView tituloItem;

    public GenericoFichaPersonajeHolder(@NonNull View itemView) {
        super(itemView);

        tituloItem = itemView.findViewById(R.id.tituloItemGenericoPersonaje);
    }

    public void bindItem(String item) {
        tituloItem.setText(item);
    }
}
