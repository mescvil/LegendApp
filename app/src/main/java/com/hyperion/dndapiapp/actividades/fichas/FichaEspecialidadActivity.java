package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ESPECIALIDADES_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.ActivityFichaEspecialidadBinding;
import com.hyperion.dndapiapp.entidades.clases.Especialidad;

import java.util.ArrayList;
import java.util.List;

public class FichaEspecialidadActivity extends AppCompatActivity {

    private ActivityFichaEspecialidadBinding binding;
    private Especialidad especialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaEspecialidadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            especialidad = bundle.getParcelable(ESPECIALIDADES_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        binding.botonAtrasFichaEspecialidad.setOnClickListener(view -> finish());
        binding.fichaEspecialidadTitulo.setText(especialidad.getNombre());
        binding.fichaDescEspecialidad.setText(especialidad.getDescripcion());

        List<GenericoRecyclerView> lista = new ArrayList<>(especialidad.getHabilidades());
        RecyclerView recyclerView = binding.listaRagosEspecialidad;
        AdaptadorGenerico adaptador = new AdaptadorGenerico(lista);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptador);
    }
}