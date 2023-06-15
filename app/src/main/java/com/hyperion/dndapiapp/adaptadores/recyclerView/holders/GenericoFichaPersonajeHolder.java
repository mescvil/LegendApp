package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.utilidades.Utils;

public class GenericoFichaPersonajeHolder extends RecyclerView.ViewHolder {

    private final TextView tituloItem;

    public GenericoFichaPersonajeHolder(@NonNull View itemView) {
        super(itemView);

        tituloItem = itemView.findViewById(R.id.tituloItemGenericoPersonaje);
    }

    public void bindItem(String item) {
        int colorTexto = Color.parseColor("#FFFFFFFF");
        char[] caracteres = item.toCharArray();
        String ultimoCaracter = String.valueOf(caracteres[caracteres.length - 1]);

        if (ultimoCaracter.equalsIgnoreCase(Utils.FLAG_HECHIZO)) {
            item = item.substring(0, item.length() - 1);
            colorTexto = Color.parseColor("#64B5F6");
        }

        tituloItem.setText(item);
        tituloItem.setTextColor(colorTexto);
    }
}
