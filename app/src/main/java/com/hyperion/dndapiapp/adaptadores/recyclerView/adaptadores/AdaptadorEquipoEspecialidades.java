package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.EquipoEspecialidadHolder;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.List;

public class AdaptadorEquipoEspecialidades extends RecyclerView.Adapter<EquipoEspecialidadHolder> {

    private final List<GetNombreInterface> listaElementos;
    private final RecyclerViewClick recyclerViewClick;

    public AdaptadorEquipoEspecialidades(List<GetNombreInterface> listaElementos, RecyclerViewClick recyclerViewClick) {
        this.listaElementos = listaElementos;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public EquipoEspecialidadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_equipo_especialidad, parent, false);
        return new EquipoEspecialidadHolder(vistaItem, recyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoEspecialidadHolder holder, int position) {
        holder.bindItem(listaElementos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }
}
