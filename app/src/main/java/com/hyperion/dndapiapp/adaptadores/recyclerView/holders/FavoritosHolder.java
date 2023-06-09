package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.sqlite.Favorito;

import javax.crypto.Cipher;

public class FavoritosHolder extends RecyclerView.ViewHolder {

    private final TextView titulo;
    private final MaterialCardView carta;
    private final Context context;

    public FavoritosHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick, Context context) {
        super(itemView);
        this.context = context;

        titulo = itemView.findViewById(R.id.tituloFavorito);
        carta = itemView.findViewById(R.id.cartaFavorito);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Favorito favorito) {
        int colorBorde = 0;

        switch (favorito.getTipo()) {
            case "Hechizo":
                colorBorde = ContextCompat.getColor(context, R.color.azulHechizo);
                break;
            case "Arma":
            case "Armadura":
                colorBorde = ContextCompat.getColor(context, R.color.hint);
                break;
            case "Trasfondo":
                colorBorde = ContextCompat.getColor(context, R.color.moradoTrasfondo);
                break;
            case "Raza":
                colorBorde = ContextCompat.getColor(context, R.color.doradoMetalico);
                break;
            case "Clase":
                colorBorde = ContextCompat.getColor(context, R.color.naranjaClase);
                break;
            case "Enemigo":
                colorBorde = ContextCompat.getColor(context, R.color.verdeCriatura);
                break;
        }

        titulo.setText(favorito.getNombre());
        carta.setStrokeColor(colorBorde);
    }
}
