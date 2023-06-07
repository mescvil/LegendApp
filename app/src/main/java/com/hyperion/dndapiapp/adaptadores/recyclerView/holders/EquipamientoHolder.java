package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;

public class EquipamientoHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView nombre;
    private TextView precio;

    public EquipamientoHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreEquipamiento);
        precio = itemView.findViewById(R.id.textViewPrecio);
        imageView = itemView.findViewById(R.id.imageViewEquipamiento);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Equipamiento equipamiento) {
        nombre.setText(equipamiento.getNombre());
        precio.setText(equipamiento.getPrecio());

        if (equipamiento instanceof Armadura) {
            imageView.setImageResource(R.drawable.icono_armadura);
        } else {
            imageView.setImageResource(R.drawable.icono_equipamiento);
        }
    }
}
