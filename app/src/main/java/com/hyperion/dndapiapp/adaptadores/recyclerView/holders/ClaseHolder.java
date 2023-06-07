package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_CLASES;
import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_CRIATURAS;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.AdaptadorMixClick;
import com.hyperion.dndapiapp.entidades.clases.Clase;

public class ClaseHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView nombre;
    private TextView caracteristica;
    private ImageView imageView;

    public ClaseHolder(@NonNull View itemView, AdaptadorMixClick adaptadorMixClick, Context context) {
        super(itemView);

        this.context = context;
        nombre = itemView.findViewById(R.id.textViewNombreClase);
        caracteristica = itemView.findViewById(R.id.textViewCaracteristica);
        imageView = itemView.findViewById(R.id.imageViewClase);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                adaptadorMixClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Clase clase) {
        nombre.setText(clase.getNombre());
        caracteristica.setText(clase.getCaracteristicaPrincipal());

        Glide.with(context)
                .load(URL_BASE_IMAGEN_CLASES + clase.getImagen())
                .into(imageView);
    }
}
