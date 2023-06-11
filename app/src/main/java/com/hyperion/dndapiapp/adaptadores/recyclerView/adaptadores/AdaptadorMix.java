package com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores;

import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_CLASE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_COMPENTENCIA;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_ENEMIGO;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_EQUIPAMIENTO;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_HECHIZO;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_RAZA;
import static com.hyperion.dndapiapp.utilidades.Constantes.ITEM_TRASFONDO;
import static com.hyperion.dndapiapp.utilidades.Utils.ordenaListaPorNombre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.ClaseHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.CompetenciaHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.EnemigoHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.EquipamientoHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.HechizoHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.RazaHolder;
import com.hyperion.dndapiapp.adaptadores.recyclerView.holders.TrasfondoHolder;
import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class AdaptadorMix extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final RecyclerViewClick recyclerViewClick;
    private List<GetNombreInterface> elementos;
    private final List<GetNombreInterface> elementosBackup;

    public AdaptadorMix(Context context, RecyclerViewClick recyclerViewClick) {
        this.elementos = new ArrayList<>();
        elementosBackup = new ArrayList<>();
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == ITEM_ENEMIGO) {
            View view = layoutInflater.inflate(R.layout.item_lista_enemigo, parent, false);
            return new EnemigoHolder(view, recyclerViewClick);
        }

        if (viewType == ITEM_HECHIZO) {
            View view = layoutInflater.inflate(R.layout.item_lista_hechizo, parent, false);
            return new HechizoHolder(view, recyclerViewClick);
        }

        if (viewType == ITEM_CLASE) {
            View view = layoutInflater.inflate(R.layout.item_lista_clase, parent, false);
            return new ClaseHolder(view, recyclerViewClick, context);
        }

        if (viewType == ITEM_EQUIPAMIENTO) {
            View view = layoutInflater.inflate(R.layout.item_lista_equipamiento, parent, false);
            return new EquipamientoHolder(view, recyclerViewClick);
        }

        if (viewType == ITEM_RAZA) {
            View view = layoutInflater.inflate(R.layout.item_lista_raza, parent, false);
            return new RazaHolder(view, context, recyclerViewClick);
        }

        if (viewType == ITEM_TRASFONDO) {
            View view = layoutInflater.inflate(R.layout.item_lista_trasfondo, parent, false);
            return new TrasfondoHolder(view, recyclerViewClick);
        }

        if (viewType == ITEM_COMPENTENCIA) {
            View view = layoutInflater.inflate(R.layout.item_lista_competencia, parent, false);
            return new CompetenciaHolder(view, recyclerViewClick);
        }

        View view = layoutInflater.inflate(R.layout.item_lista_null, parent, false);
        return new HechizoHolder(view, recyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (elementos.get(position) instanceof Enemigo) {
            Enemigo enemigo = (Enemigo) elementos.get(position);
            ((EnemigoHolder) holder).bindItem(enemigo, context);
            return;
        }

        if (elementos.get(position) instanceof Hechizo) {
            Hechizo hechizo = (Hechizo) elementos.get(position);
            ((HechizoHolder) holder).bindItem(hechizo);
            return;
        }

        if (elementos.get(position) instanceof Clase) {
            Clase clase = (Clase) elementos.get(position);
            ((ClaseHolder) holder).bindItem(clase);
            return;
        }

        if (elementos.get(position) instanceof Equipamiento) {
            Equipamiento equipamiento = (Equipamiento) elementos.get(position);
            ((EquipamientoHolder) holder).bindItem(equipamiento);
            return;
        }

        if (elementos.get(position) instanceof Raza) {
            Raza raza = (Raza) elementos.get(position);
            ((RazaHolder) holder).bindItem(raza);
            return;
        }

        if (elementos.get(position) instanceof Trasfondo) {
            Trasfondo trasfondo = (Trasfondo) elementos.get(position);
            ((TrasfondoHolder) holder).bindItem(trasfondo);
            return;
        }

        if (elementos.get(position) instanceof Competencia) {
            Competencia competencia = (Competencia) elementos.get(position);
            ((CompetenciaHolder) holder).bindItem(competencia);
        }
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    public GetNombreInterface getObjeto(int posicion) {
        return elementos.get(posicion);
    }

    @Override
    public int getItemViewType(int position) {
        if (elementos.get(position) instanceof Hechizo)
            return ITEM_HECHIZO;

        if (elementos.get(position) instanceof Enemigo)

            return ITEM_ENEMIGO;
        if (elementos.get(position) instanceof Clase)

            return ITEM_CLASE;
        if (elementos.get(position) instanceof Equipamiento)
            return ITEM_EQUIPAMIENTO;

        if (elementos.get(position) instanceof Raza)
            return ITEM_RAZA;

        if (elementos.get(position) instanceof Trasfondo)
            return ITEM_TRASFONDO;

        if (elementos.get(position) instanceof Competencia)
            return ITEM_COMPENTENCIA;

        return -1;
    }

    public void clearElementos() {
        this.elementos.clear();
        this.elementosBackup.clear();
    }

    public void setElementos(List<GetNombreInterface> elementos) {
        this.elementos = elementos;
        elementosBackup.clear();
        elementosBackup.addAll(this.elementos);
        notifyDataSetChanged();
    }

    public void addElementos(List<GetNombreInterface> elementos) {
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
            for (GetNombreInterface objeto : elementosBackup) {
                if (objeto instanceof Enemigo) {
                    if (((Enemigo) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);
                } else if (objeto instanceof Clase) {
                    if (((Clase) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                } else if (objeto instanceof Hechizo) {
                    if (((Hechizo) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                } else if (objeto instanceof Equipamiento) {
                    if (((Equipamiento) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                } else if (objeto instanceof Raza) {
                    if (((Raza) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                } else if (objeto instanceof Trasfondo) {
                    if (((Trasfondo) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);

                } else if (objeto instanceof Competencia) {
                    if (((Competencia) objeto).getNombre().toLowerCase().contains(texto.toLowerCase()))
                        elementos.add(objeto);
                }
            }
        }
        notifyDataSetChanged();
    }
}
