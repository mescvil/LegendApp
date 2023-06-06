package com.hyperion.dndapiapp.adaptadores.fragmentState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.fragmentos.fichas.trasfondos.CompetenciasTrasfodosFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.trasfondos.GeneralTrasfondoFragment;

public class TrasfondoStateAdapter extends FragmentStateAdapter {

    private final Trasfondo trasfondo;

    public TrasfondoStateAdapter(@NonNull FragmentActivity fragment, Trasfondo trasfondo) {
        super(fragment);
        this.trasfondo = trasfondo;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return GeneralTrasfondoFragment.newInstance(trasfondo);
        } else {
            return CompetenciasTrasfodosFragment.newInstance(trasfondo.getCompetencias().toArray(new Competencia[0]));
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
