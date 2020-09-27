package com.example.appsqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class recyclerview5 extends AppCompatActivity {
    private RecyclerView rv;
    private AdaptadorArticulos5 adaptadorArticulos5;

    private FloatingActionMenu men;
    private FloatingActionButton b1, b2;
    modal_Toast_Custom mo = new modal_Toast_Custom();
    ConexionSQLite datos = new ConexionSQLite(recyclerview5.this);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_seguro)
                    .setTitle("Warning")
                    .setMessage("Realmente desea ir al inicio?")
                    .setNegativeButton(android.R.string.cancel,null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          finish();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void cerrarAplicacion() {
        new AlertDialog.Builder(this) .setIcon(R.drawable.ic_seguro)
                .setTitle("¿Realmente desea cerrar la aplicación?")
                .setCancelable(false) .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    // un listener queal pulsar, cierre la aplicacion
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAffinity();
                        //Su funcion es algo similar a lo que se llama cuando sepresiona el botón "Forzar Detención" o "Administrar aplicaciones" , lo cuál mata la aplicación //finish(); Si solo quiere mandar la aplicación a segundo plano
                    }
                }).show(); }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview5);
        men = findViewById(R.id.menu1);
        b1 =findViewById(R.id.regresar);
        b2 =findViewById(R.id.salirapp);

        rv = (RecyclerView)findViewById(R.id.review);
        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        rv.setHasFixedSize(true);
        // Nuestro RecyclerView usará un linear layout manager
       rv.setLayoutManager(new LinearLayoutManager(this));
        //adaptadorArticulos = new AdaptadorArticulos(consulta_recyclerView.this,obtenerArticulos());
        adaptadorArticulos5 = new AdaptadorArticulos5(recyclerview5.this,datos.mostrarArticulos());
        rv.setAdapter(adaptadorArticulos5);

        men.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened){
                    Toast.makeText(recyclerview5.this, "Menu abierto", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(recyclerview5.this, "Menu cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

      b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mo.dialogConfirmCustom2(recyclerview5.this);
            }
        });

      b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              cerrarAplicacion();

          }
      });
    }


}