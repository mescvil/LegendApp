package com.hyperion.dndapiapp.fragmentos.personaje;

import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_READ_ONLY;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.actividades.fichas.FichaHechizoActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenericoFichaPersonaje;
import com.hyperion.dndapiapp.controladores.Controlador;
import com.hyperion.dndapiapp.databinding.FragmentMochilaPersonajeBinding;
import com.hyperion.dndapiapp.entidades.fichas.EquipamientoPersonaje;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.Utils;

import java.util.ArrayList;
import java.util.List;

public class MochilaPersonajeFragment extends Fragment implements RecyclerViewClick {
    private static final String ARG_EQUIPAMIENTO = "equipamiento";

    private FragmentMochilaPersonajeBinding binding;
    private AdaptadorGenericoFichaPersonaje adaptador;
    private EquipamientoPersonaje equipamiento;

    public MochilaPersonajeFragment() {
    }

    public static MochilaPersonajeFragment newInstance(EquipamientoPersonaje equipamiento) {
        MochilaPersonajeFragment fragment = new MochilaPersonajeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_EQUIPAMIENTO, equipamiento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            equipamiento = getArguments().getParcelable(ARG_EQUIPAMIENTO);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMochilaPersonajeBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        List<String> armas = Utils.StringToLista(equipamiento.getArmas());
        List<String> armaduras = Utils.StringToLista(equipamiento.getArmaduras());
        List<String> hechizos = Utils.StringToListaHechizos(equipamiento.getHechizos());
        List<String> listaMix = new ArrayList<>();

        listaMix.addAll(armas);
        listaMix.addAll(armaduras);

        if (!hechizos.get(0).equalsIgnoreCase(Utils.FLAG_HECHIZO))
            listaMix.addAll(hechizos);

        RecyclerView recyclerView = binding.listaMochilaPersonaje;
        adaptador = new AdaptadorGenericoFichaPersonaje(listaMix, getContext(), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void onCosaCliked(int posicion) {
        String nombreFlag = adaptador.getItem(posicion);
        String nombre = nombreFlag.substring(0, nombreFlag.length() - 1);
        Hechizo hechizo = Controlador.getInstance().getHechizoByNombre(nombre);

        if (hechizo != null) {
            Intent intent = new Intent(getContext(), FichaHechizoActivity.class);
            intent.putExtra(HECHIZOS_BUNDLE, hechizo);
            intent.putExtra(IS_FAVORITO, false);
            intent.putExtra(IS_READ_ONLY, true);

            startActivity(intent);
        }
    }
}