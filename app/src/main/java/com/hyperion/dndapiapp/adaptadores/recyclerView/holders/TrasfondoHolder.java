package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.AdaptadorMixClick;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;

public class TrasfondoHolder extends RecyclerView.ViewHolder {

    private final TextView nombre;

    public TrasfondoHolder(@NonNull View itemView, AdaptadorMixClick adaptadorMixClick) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreTrasfondo);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                adaptadorMixClick.onCosaCliked(posicion);
            }
        });
    }

    public void bindItem(Trasfondo trasfondo) {
        nombre.setText(trasfondo.getNombre());
    }
}
