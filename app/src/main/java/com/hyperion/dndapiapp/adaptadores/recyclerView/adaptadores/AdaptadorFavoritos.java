package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.FavoritosHolder;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class AdaptadorFavoritos extends RecyclerView.Adapter<FavoritosHolder> {

    private final Context context;
    private final List<Favorito> favoritos;
    private final List<Favorito> favoritosBackup;
    private final RecyclerViewClick recyclerViewClick;

    public AdaptadorFavoritos(Context context, List<Favorito> favoritos, RecyclerViewClick recyclerViewClick) {
        this.favoritos = favoritos;
        this.recyclerViewClick = recyclerViewClick;
        this.context = context;
        favoritosBackup = new ArrayList<>();
        favoritosBackup.addAll(favoritos);
    }

    @NonNull
    @Override
    public FavoritosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_favorito, parent, false);
        return new FavoritosHolder(vistaItem, recyclerViewClick, context);
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

    public boolean containsItem(Favorito favorito) {
        return favoritos.contains(favorito);
    }

    public void removeItem(Favorito favorito) {
        favoritos.remove(favorito);
        favoritosBackup.clear();
        favoritosBackup.addAll(favoritos);
        notifyDataSetChanged();
    }

    public void clearAll() {
        favoritos.clear();
        favoritosBackup.clear();
    }

    public void addItem(Favorito favorito) {
        favoritos.add(favorito);
        favoritosBackup.clear();
        favoritosBackup.addAll(favoritos);
        notifyDataSetChanged();
    }

    public void filtra(String filtro) {
        favoritos.clear();

        if (filtro.isEmpty()) {
            favoritos.addAll(favoritosBackup);
        } else {
            for (Favorito favorito : favoritosBackup) {
                if (favorito.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                    favoritos.add(favorito);
            }
        }
        notifyDataSetChanged();
    }
}
