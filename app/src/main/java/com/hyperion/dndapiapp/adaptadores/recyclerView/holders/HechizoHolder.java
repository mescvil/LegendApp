package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;

public class HechizoHolder extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView nivel;
    private TextView alcance;

    public HechizoHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreHechizo);
        nivel = itemView.findViewById(R.id.textViewNivel);
        alcance = itemView.findViewById(R.id.textViewAlcance);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Hechizo hechizo) {
        nombre.setText(hechizo.getNombre());
        nivel.setText(String.valueOf(hechizo.getNivel()));
        alcance.setText(String.valueOf(hechizo.getAlcance()));
    }
}