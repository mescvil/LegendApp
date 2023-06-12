package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;

public class GenericoHolder extends RecyclerView.ViewHolder {

    private final TextView tituloItem;
    private final TextView descripcionItem;

    public GenericoHolder(@NonNull View itemView) {
        super(itemView);

        tituloItem = itemView.findViewById(R.id.tituloItem);
        descripcionItem = itemView.findViewById(R.id.descripcionItem);
    }

    public void bindItem(GenericoRecyclerView item) {
        tituloItem.setText(item.getNombre());
        descripcionItem.setText(item.getDescripcion());

        if (item instanceof Hechizo) {
            tituloItem.setTextColor(Color.parseColor("#64B5F6"));
        } else {
            tituloItem.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }
}
