package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.EquipoHolder;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import java.util.List;

public class AdaptadorEquipamiento extends RecyclerView.Adapter<EquipoHolder> {

    private final List<OrdenablePorNombre> listaElementos;
    private final RecyclerViewClick recyclerViewClick;

    public AdaptadorEquipamiento(List<OrdenablePorNombre> listaElementos, RecyclerViewClick recyclerViewClick) {
        this.listaElementos = listaElementos;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public EquipoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_equipo, parent, false);
        return new EquipoHolder(vistaItem, recyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoHolder holder, int position) {
        holder.bindItem(listaElementos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }
}
