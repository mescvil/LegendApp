package com.hyperion.dndapiapp.adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;

public class CompetenciaHolder extends RecyclerView.ViewHolder {

    private TextView nombre;

    public CompetenciaHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreCompetencia);
    }

    public void bindItem(Competencia competencia) {
        nombre.setText(competencia.getNombre());
    }
}
