package com.hyperion.dndapiapp.adaptadores;

import static com.hyperion.dndapiapp.utilidades.Utils.ordenaListaPorNombre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class AdaptadorMix extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_ENEMIGO = 0;
    private static final int ITEM_HECHIZO = 1;
    private static final int ITEM_CLASE = 2;
    private final Context context;
    private List<OrdenablePorNombre> elementos;
    private List<OrdenablePorNombre> elementosBackup;

    public AdaptadorMix(Context context) {
        this.elementos = new ArrayList<>();
        elementosBackup = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == ITEM_ENEMIGO) {
            View view = layoutInflater.inflate(R.layout.item_lista_enemigo, parent, false);
            return new EnemigoHolder(view);
        }

        if (viewType == ITEM_HECHIZO) {
            View view = layoutInflater.inflate(R.layout.item_lista_hechizo, parent, false);
            return new HechizoHolder(view);
        }

        if (viewType == ITEM_CLASE) {
            View view = layoutInflater.inflate(R.layout.item_lista_clase, parent, false);
            return new ClaseHolder(view);
        }

        View view = layoutInflater.inflate(R.layout.item_lista_null, parent, false);
        return new HechizoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (elementos.get(position) instanceof Enemigo) {
            Enemigo enemigo = (Enemigo) elementos.get(position);
            ((EnemigoHolder) holder).bindItem(enemigo, context);
        }

        if (elementos.get(position) instanceof Hechizo) {
            Hechizo hechizo = (Hechizo) elementos.get(position);
            ((HechizoHolder) holder).bindItem(hechizo);
        }

        if (elementos.get(position) instanceof Clase) {
            Clase clase = (Clase) elementos.get(position);
            ((ClaseHolder) holder).bindItem(clase);
        }
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (elementos.get(position) instanceof Hechizo)
            return ITEM_HECHIZO;
        if (elementos.get(position) instanceof Enemigo)
            return ITEM_ENEMIGO;
        if (elementos.get(position) instanceof Clase)
            return ITEM_CLASE;

        return -1;
    }

    public void clearElementos() {
        this.elementos.clear();
        this.elementosBackup.clear();
    }

    public void setElementos(List<OrdenablePorNombre> elementos) {
        this.elementos = elementos;
        elementosBackup.clear();
        elementosBackup.addAll(this.elementos);
        notifyDataSetChanged();
    }

    public void addElementos(List<OrdenablePorNombre> elementos) {
        this.elementos.addAll(elementos);
        this.elementos = ordenaListaPorNombre(this.elementos);
        elementosBackup.clear();
        elementosBackup.addAll(this.elementos);
        notifyDataSetChanged();
    }

    public void filtra(String texto) {
        elementos.clear();

        if (texto.isEmpty()) {
            elementos.addAll(elementosBackup);
        } else {
            for (OrdenablePorNombre objeto : elementosBackup) {
                if (objeto instanceof Enemigo)
                    if (((Enemigo) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                if (objeto instanceof Clase)
                    if (((Clase) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                if (objeto instanceof Hechizo)
                    if (((Hechizo) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);
            }
        }
        notifyDataSetChanged();
    }
}
