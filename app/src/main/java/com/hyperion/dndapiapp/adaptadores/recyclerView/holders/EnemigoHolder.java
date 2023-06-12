package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_CRIATURAS;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;

public class EnemigoHolder extends RecyclerView.ViewHolder {

    private final TextView nombre;
    private final TextView claseArmadura;
    private final TextView puntosVida;
    private final TextView desafio;
    private final ImageView imagen;

    public EnemigoHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombre);
        claseArmadura = itemView.findViewById(R.id.textViewAC);
        puntosVida = itemView.findViewById(R.id.textViewPV);
        desafio = itemView.findViewById(R.id.textViewDesafio);
        imagen = itemView.findViewById(R.id.imageViewEnemigo);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Enemigo enemigo, Context context) {
        nombre.setText(enemigo.getNombre());
        claseArmadura.setText(String.format("%s", enemigo.getClaseArmadura()));
        puntosVida.setText(String.format("%s", enemigo.getPuntosGolpe()));
        desafio.setText(String.format("%s", enemigo.getDesafio()));

        Glide.with(context)
                .load(URL_BASE_IMAGEN_CRIATURAS + enemigo.getUrlImagen())
                .into(imagen);
    }
}