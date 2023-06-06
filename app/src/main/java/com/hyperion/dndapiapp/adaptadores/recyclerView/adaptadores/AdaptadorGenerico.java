package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.GenericoHolder;

import java.util.List;

public class AdaptadorGenerico extends RecyclerView.Adapter<GenericoHolder> {

    private final List<GenericoRecyclerView> listaElementos;

    public AdaptadorGenerico(List<GenericoRecyclerView> listaElementos) {
        this.listaElementos = listaElementos;
    }

    @NonNull
    @Override
    public GenericoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_generico, parent, false);
        return new GenericoHolder(vistaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericoHolder holder, int position) {
        holder.bindItem(listaElementos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }
}
