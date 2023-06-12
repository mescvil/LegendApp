package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;

public class FichasHolder extends RecyclerView.ViewHolder {

    private final TextView nombrePersonaje;
    private final TextView clasePersonaje;

    public FichasHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
        super(itemView);

        nombrePersonaje = itemView.findViewById(R.id.textViewNombrePersonaje);
        clasePersonaje = itemView.findViewById(R.id.textViewClase);
        ImageButton botonBorrarFicha = itemView.findViewById(R.id.botonBorrarFicha);

        itemView.setOnClickListener(v -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                recyclerViewClick.onCosaCliked(posicion);
            }
        });

        botonBorrarFicha.setOnClickListener(view -> {

        });
    }

    public void bindItem(PersonajeFicha personajeFicha) {
        nombrePersonaje.setText(personajeFicha.getNombre());
        clasePersonaje.setText(personajeFicha.getClase().getNombre());
    }
}
