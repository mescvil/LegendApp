package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.GenericoFichaPersonajeHolder;

import java.util.List;

public class AdaptadorGenericoFichaPersonaje extends RecyclerView.Adapter<GenericoFichaPersonajeHolder> {

    private final List<String> listaElementos;

    public AdaptadorGenericoFichaPersonaje(List<String> listaElementos) {
        this.listaElementos = listaElementos;
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
        holder.bindItem(listaElementos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }
}
