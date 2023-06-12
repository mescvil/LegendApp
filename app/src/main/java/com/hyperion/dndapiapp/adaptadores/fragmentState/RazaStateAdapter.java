package com.hyperion.dndapiapp.adaptadores.fragmentState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hyperion.dndapiapp.entidades.glosario.razas.RasgoRaza;
import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.fragmentos.fichas.razas.GeneralRazaFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.razas.RasgosRazaFragment;

public class RazaStateAdapter extends FragmentStateAdapter {

    private Raza raza;

    public RazaStateAdapter(@NonNull FragmentActivity fragmentActivity, Raza raza) {
        super(fragmentActivity);
        this.raza = raza;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return GeneralRazaFragment.newInstance(raza);
        } else {
            return RasgosRazaFragment.newInstance(raza.getRasgosRaza().toArray(new RasgoRaza[0]));
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
