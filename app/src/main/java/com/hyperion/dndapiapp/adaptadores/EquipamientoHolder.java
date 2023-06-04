package com.hyperion.dndapiapp.adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;

public class EquipamientoHolder extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView precio;

    public EquipamientoHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreEquipamiento);
        precio = itemView.findViewById(R.id.textViewPrecio);
    }

    public void bindItem(Equipamiento equipamiento) {
        nombre.setText(equipamiento.getNombre());
        precio.setText(equipamiento.getPrecio());
    }
}
