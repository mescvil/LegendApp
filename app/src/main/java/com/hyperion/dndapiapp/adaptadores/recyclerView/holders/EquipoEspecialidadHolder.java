package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

public class EquipoEspecialidadHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public EquipoEspecialidadHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        textView = itemView.findViewById(R.id.tituloItemEquipo);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(GetNombreInterface item) {
        textView.setText(item.getNombre());

        if (item instanceof Hechizo) {
            textView.setTextColor(Color.parseColor("#64B5F6"));
        } else {
            textView.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }
}
