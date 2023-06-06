package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.clases.Clase;

public class ClaseHolder extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView caracteristica;

    public ClaseHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreClase);
        caracteristica = itemView.findViewById(R.id.textViewCaracteristica);
    }

    public void bindItem(Clase clase) {
        nombre.setText(clase.getNombre());
        caracteristica.setText(clase.getCaracteristicaPrincipal());
    }
}
