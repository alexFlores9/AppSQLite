package com.example.appsqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class consulta_spinner extends AppCompatActivity {
private Spinner sp_options;
private TextView tv_cod,tv_descripcion,tv_precio;

ConexionSQLite conexion = new ConexionSQLite(this);
Dto datos= new Dto();

private FloatingActionMenu menu;
private FloatingActionButton item1, item2,item3;

modal_Toast_Custom mo = new modal_Toast_Custom();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);


        sp_options = findViewById(R.id.sp_options);
        tv_cod= findViewById(R.id.tv_cod);
        tv_descripcion= findViewById(R.id.tv_descripcion);
        tv_precio= findViewById(R.id.tv_precio);

        menu = findViewById(R.id.menu_fab);
        item1 =findViewById(R.id.item1);
        item2 =findViewById(R.id.item2);
        item3 =findViewById(R.id.item3);

        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened){
                    Toast.makeText(consulta_spinner.this, "Menu abierto", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(consulta_spinner.this, "Menu cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });



        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   finish();
               // Intent intent = new Intent(consulta_spinner.this, MainActivity.class);
mo.dialogConfirmCustom2(consulta_spinner.this);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mo.dialogAbout(consulta_spinner.this);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
cerrarAplicacion();

            }
        });

        conexion.consultaListaArticulos();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,conexion.obtenerListaArticulos());
        sp_options.setAdapter(adaptador);

        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position!=0){
                    tv_cod.setText("Codigo: "+conexion.consultaListaArticulos().get(position-1).getCodigo());
               tv_descripcion.setText("Descripcion: "+conexion.consultaListaArticulos().get(position-1).getDescripcion());
               tv_precio.setText("Precio:"+String.valueOf(conexion.consultaListaArticulos().get(position-1).getPrecio()));
                }else {
                    tv_cod.setText("Código: ");
                    tv_descripcion.setText("Descripción");
                    tv_precio.setText("Precio: ");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}