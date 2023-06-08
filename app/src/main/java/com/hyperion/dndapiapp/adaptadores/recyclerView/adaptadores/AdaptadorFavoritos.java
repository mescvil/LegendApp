package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.FavoritosHolder;
import com.hyperion.dndapiapp.sqlite.Favorito;

import java.util.List;

public class AdaptadorFavoritos extends RecyclerView.Adapter<FavoritosHolder> {

    private final List<Favorito> favoritos;
    private final RecyclerViewClick recyclerViewClick;

    public AdaptadorFavoritos(List<Favorito> favoritos, RecyclerViewClick recyclerViewClick) {
        this.favoritos = favoritos;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public FavoritosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_favorito, parent, false);
        return new FavoritosHolder(vistaItem, recyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosHolder holder, int position) {
        holder.bindItem(favoritos.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }

    public Favorito getFavorito(int position) {
        return favoritos.get(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeItem(Favorito favorito) {
        favoritos.remove(favorito);
        notifyDataSetChanged();
    }

    public void addItem(Favorito favorito) {
        favoritos.add(favorito);
    }
}
