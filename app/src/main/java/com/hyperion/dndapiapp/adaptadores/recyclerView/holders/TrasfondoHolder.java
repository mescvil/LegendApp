package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;

public class TrasfondoHolder extends RecyclerView.ViewHolder {

    private TextView nombre;

    public TrasfondoHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreTrasfondo);
    }

    public void bindItem(Trasfondo trasfondo) {
        nombre.setText(trasfondo.getNombre());
    }
}
