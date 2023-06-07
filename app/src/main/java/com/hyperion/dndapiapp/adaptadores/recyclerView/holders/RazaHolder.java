package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_RAZAS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.razas.Raza;

public class RazaHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView nombre;
    private final TextView velocidad;
    private final TextView edad;
    private final ImageView imageView;

    public RazaHolder(@NonNull View itemView, Context context, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreRaza);
        velocidad = itemView.findViewById(R.id.textViewVelocidad);
        edad = itemView.findViewById(R.id.textViewEdad);
        imageView = itemView.findViewById(R.id.imageViewRaza);
        this.context = context;

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void bindItem(Raza raza) {
        nombre.setText(raza.getNombre());
        velocidad.setText(String.valueOf(raza.getVelocidad()));
        edad.setText(raza.getEdadMaxima() + " años");

        Glide.with(context)
                .load(URL_BASE_IMAGEN_RAZAS + raza.getImagenHembra())
                .into(imageView);
    }
}
