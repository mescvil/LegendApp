package com.hyperion.dndapiapp.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.hyperion.dndapiapp.R;

@SuppressWarnings("unused")
public class LoadingDialog {

    private final Context context;
    private Dialog dialog;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void show(String titulo) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialogo_cargando);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView textoDialogo = dialog.findViewById(R.id.textoLoading);
        textoDialogo.setText(titulo);

        dialog.create();
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
