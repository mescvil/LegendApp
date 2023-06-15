package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.GenericoFichaPersonajeHolder;

import java.util.List;

import javax.crypto.Cipher;

public class AdaptadorGenericoFichaPersonaje extends RecyclerView.Adapter<GenericoFichaPersonajeHolder> {

    private final List<String> listaElementos;
    private final Context context;
    private final RecyclerViewClick click;

    public AdaptadorGenericoFichaPersonaje(List<String> listaElementos, Context context, RecyclerViewClick click) {
        this.listaElementos = listaElementos;
        this.context = context;
        this.click = click;
    }

    public AdaptadorGenericoFichaPersonaje(List<String> listaElementos, Context context) {
        this.listaElementos = listaElementos;
        this.context = context;
        this.click = null;
    }

    @NonNull
    @Override
    public GenericoFichaPersonajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_generico_personaje, parent, false);
        return new GenericoFichaPersonajeHolder(vistaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericoFichaPersonajeHolder holder, int position) {
        holder.bindItem(listaElementos.get(position), context, click);
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public String getItem(int posicion) {
        return listaElementos.get(posicion);
    }
}
