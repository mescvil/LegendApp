package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.EliminarFichaClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.FichasHolder;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;

import java.util.List;

public class AdaptadorFichas extends RecyclerView.Adapter<FichasHolder> {

    private final List<PersonajeFicha> listaFichas;
    private final RecyclerViewClick clickListener;
    private final EliminarFichaClick eliminarFichaClick;

    public AdaptadorFichas(List<PersonajeFicha> listaElementos, RecyclerViewClick clickListener, EliminarFichaClick eliminarFichaClick) {
        this.listaFichas = listaElementos;
        this.clickListener = clickListener;
        this.eliminarFichaClick = eliminarFichaClick;
    }

    @NonNull
    @Override
    public FichasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_ficha, parent, false);
        return new FichasHolder(vistaItem, clickListener, eliminarFichaClick);
    }

    @Override
    public void onBindViewHolder(@NonNull FichasHolder holder, int position) {
        holder.bindItem(listaFichas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaFichas.size();
    }

    public PersonajeFicha getItem(int posicion) {
        return listaFichas.get(posicion);
    }
}
