package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FavoritosHolder extends RecyclerView.ViewHolder {

    private TextView titulo;

    public FavoritosHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        titulo = itemView.findViewById(R.id.tituloFavorito);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Favorito favorito) {
        titulo.setText(favorito.getNombre());
    }
}
