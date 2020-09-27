package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

import java.util.ArrayList;

public class List_view_articulo extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String>list;
    ArrayAdapter adapter;



    String[]version=
            {"Aestro","Blender","CupCake","Eclair","Froyo","GingerBread","HoneyComb",
            "Icecream Sandwich","Jelly Bean","Kitkat","Lolipop","Marshmallow","Nougth","Oreo"};
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos=new Dto();

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
        setContentView(R.layout.activity_list_view_articulo);

        listViewPersonas = findViewById(R.id.listViewPersonas);
        searchView= findViewById(R.id.searchView);


        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                conexion.consultaListaArticulos1());
        listViewPersonas.setAdapter(adaptador);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
              String text = s;
              adaptador.getFilter().filter(text);
              return false;
            }
        });


        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {

                String informacion="Codigo:"+conexion.consultaListaArticulos().get(pos).getCodigo()+"\n";
                informacion+="Descripcion"+conexion.consultaListaArticulos().get(pos).getDescripcion()+"\n";
                informacion+="Precio"+conexion.consultaListaArticulos().get(pos).getPrecio();


                Dto articulos= conexion.consultaListaArticulos().get(pos);
                Intent intent=new Intent(List_view_articulo.this,Detalles_articulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("articulo",articulos);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

    }
}