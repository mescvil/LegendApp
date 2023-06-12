package com.hyperion.dndapiapp.adaptadores.fragmentState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.entidades.glosario.clases.Especialidad;
import com.hyperion.dndapiapp.entidades.glosario.clases.RasgoClase;
import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.EquipamientoClaseFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.EspecialidadClaseFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.GeneralClasesFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.RasgosClaseFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.trasfondos.CompetenciasTrasfodosFragment;

public class ClaseStateAdapter extends FragmentStateAdapter {

    private Clase clase;

    public ClaseStateAdapter(@NonNull FragmentActivity fragmentActivity, Clase clase) {
        super(fragmentActivity);
        this.clase = clase;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return EspecialidadClaseFragment.newInstance(clase.getEspecialidades().toArray(new Especialidad[0]));
            case 2:
                return RasgosClaseFragment.newInstance(clase.getRasgosClase().toArray(new RasgoClase[0]));
            case 3:
                return EquipamientoClaseFragment.newInstance(
                        clase.getArmaduras().toArray(new Armadura[0]),
                        clase.getArmas().toArray(new Arma[0]),
                        clase.getHechizos().toArray(new Hechizo[0])
                );
            case 4:
                return CompetenciasTrasfodosFragment.newInstance(clase.getCompetencias().toArray(new Competencia[0]));
            default:
                return GeneralClasesFragment.newInstance(clase);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
