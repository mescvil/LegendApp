package com.hyperion.dndapiapp.adaptadores.fragmentState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.glosario.clases.Especialidad;
import com.hyperion.dndapiapp.entidades.glosario.clases.RasgoClase;
import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.EspecialidadClaseFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.GeneralClasesFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.clases.RasgosClaseFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.trasfondos.CompetenciasTrasfodosFragment;
import com.hyperion.dndapiapp.fragmentos.personaje.CompetenciasPersonajeFragment;
import com.hyperion.dndapiapp.fragmentos.personaje.GeneralPersonajeFragment;
import com.hyperion.dndapiapp.fragmentos.personaje.MochilaPersonajeFragment;
import com.hyperion.dndapiapp.fragmentos.personaje.RasgosPersonajeFragment;

public class PersonajeStateAdapter extends FragmentStateAdapter {

    PersonajeFicha ficha;

    public PersonajeStateAdapter(@NonNull FragmentActivity fragmentActivity, PersonajeFicha ficha) {
        super(fragmentActivity);
        this.ficha = ficha;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return RasgosPersonajeFragment.newInstance(
                        ficha.getClase().getRasgos(),
                        ficha.getRaza().getRasgos()
                );
            case 2:
                return new CompetenciasPersonajeFragment();
            case 3:
                return new MochilaPersonajeFragment();
            default:
                return new GeneralPersonajeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
