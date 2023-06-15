package com.hyperion.dndapiapp.adaptadores.recyclerView.holders;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.utilidades.Utils;

public class GenericoFichaPersonajeHolder extends RecyclerView.ViewHolder {

    private final ConstraintLayout layout;
    private final TextView tituloItem;
    private final TextView verMas;
    private int colorTexto;
    private String textoItem;

    public GenericoFichaPersonajeHolder(@NonNull View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.layoutItemGenericoPersonaje);
        tituloItem = itemView.findViewById(R.id.tituloItemGenericoPersonaje);
        verMas = itemView.findViewById(R.id.texViewVerMas);

    }

    public void bindItem(String item, Context context, RecyclerViewClick click) {
        char[] caracteres = item.toCharArray();
        String ultimoCaracter = String.valueOf(caracteres[caracteres.length - 1]);

        if (ultimoCaracter.equalsIgnoreCase(Utils.FLAG_HECHIZO))
            bindHechizo(item, context, click);
        else
            bindOtro(item, context);

        tituloItem.setText(textoItem);
        tituloItem.setTextColor(colorTexto);
    }

    private void bindHechizo(String item, Context context, RecyclerViewClick click) {
        textoItem = item.substring(0, item.length() - 1);
        colorTexto = Color.parseColor("#64B5F6");
        verMas.setVisibility(View.VISIBLE);


        int margen = Utils.numeroToDP(0, context);
        ConstraintSet constraintSet = new ConstraintSet();

        constraintSet.clone(layout);
        constraintSet.connect(
                tituloItem.getId(), ConstraintSet.BOTTOM,
                verMas.getId(), ConstraintSet.TOP, margen);

        constraintSet.applyTo(layout);

        layout.setOnClickListener(view -> {
            int posicion = getAdapterPosition();
            if (posicion != RecyclerView.NO_POSITION) {
                click.onCosaCliked(posicion);
            }
        });
    }

    private void bindOtro(String item, Context context) {
        colorTexto = Color.parseColor("#FFFFFFFF");
        textoItem = item;

        int margen = Utils.numeroToDP(16, context);
        ConstraintSet constraintSet = new ConstraintSet();

        constraintSet.clone(layout);
        constraintSet.connect(
                tituloItem.getId(), ConstraintSet.BOTTOM,
                layout.getId(), ConstraintSet.BOTTOM, margen);

        constraintSet.applyTo(layout);

        verMas.setVisibility(View.GONE);
    }
}
