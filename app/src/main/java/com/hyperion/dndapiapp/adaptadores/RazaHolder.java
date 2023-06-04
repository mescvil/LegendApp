package com.hyperion.dndapiapp.adaptadores;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.razas.Raza;

public class RazaHolder extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView velocidad;
    private TextView edad;

    public RazaHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textViewNombreRaza);
        velocidad = itemView.findViewById(R.id.textViewVelocidad);
        edad = itemView.findViewById(R.id.textViewEdad);
    }

    @SuppressLint("SetTextI18n")
    public void bindItem(Raza raza) {
        nombre.setText(raza.getNombre());
        velocidad.setText(String.valueOf(raza.getVelocidad()));
        edad.setText(raza.getEdadMaxima() + " años");
    }
}
