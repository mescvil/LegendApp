package com.hyperion.dndapiapp.adaptadores.fragmentState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hyperion.dndapiapp.entidades.enemigos.Accion;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.enemigos.RasgoEnemigo;
import com.hyperion.dndapiapp.fragmentos.fichas.enemigos.AccionesEnemigosFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.enemigos.GeneralEnemigosFragment;
import com.hyperion.dndapiapp.fragmentos.fichas.enemigos.RasgosEnemigosFragment;

public class EnemigosStateAdapter extends FragmentStateAdapter {

    private final Enemigo enemigo;

    public EnemigosStateAdapter(@NonNull FragmentActivity fragmentActivity, Enemigo enemigo) {
        super(fragmentActivity);
        this.enemigo = enemigo;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return RasgosEnemigosFragment.newInstance(enemigo.getRasgoCriaturas().toArray(new RasgoEnemigo[0]));
            case 2:
                return AccionesEnemigosFragment.newInstance(enemigo.getAcciones().toArray(new Accion[0]));
            default:
                return GeneralEnemigosFragment.newInstance(enemigo);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
