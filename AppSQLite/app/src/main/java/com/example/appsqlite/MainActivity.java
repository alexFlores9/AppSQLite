package com.example.appsqlite;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Build;
import android.os.Bundle;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    private EditText et_codigo,et_descripcion,et_precio;
    private Button btn_guardar, btn_consultar1,btn_consultar2,btn_eliminar,btn_actualizar;
    private TextView tv_resultado;

    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;
    int resultadoInsert=0;

    Modal ventanas = new Modal();
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();
    AlertDialog.Builder dialogo;

private FABToolbarLayout morph;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_seguro)
                    .setTitle("Warning")
                    .setMessage("Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel,null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                   finishAffinity();
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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_home));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitleMargin(0,0,0,0);
        toolbar.setSubtitle("SQLite Marlon Flores");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setTitle("Marlon SIS22A");
        setSupportActionBar(toolbar);

      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              confirmacion();
          }
      });

   /*     FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanas.Search(MainActivity.this);
            }
        });*/

        final FloatingActionButton fab = findViewById(R.id.fab);






        morph =findViewById(R.id.fabtoolbar);

        View uno,dos,tres,cuatro,cinco,salir;

        uno=findViewById(R.id.uno);
        dos=findViewById(R.id.dos);
        tres=findViewById(R.id.tres);
        cuatro=findViewById(R.id.cuatro);
        cinco=findViewById(R.id.cinco);
        salir=findViewById(R.id.salir);

        fab.setOnClickListener(this);
        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        salir.setOnClickListener(this);



        et_codigo = findViewById(R.id.et_codigo);
        et_descripcion = findViewById(R.id.et_descripcion);
        et_precio= findViewById(R.id.et_precio);
        btn_guardar= findViewById(R.id.btn_guardar);
    //    btn_consultar1= findViewById(R.id.btn_consultar1);
      //  btn_consultar2= findViewById(R.id.btn_consultar2);
        //btn_eliminar= findViewById(R.id.btn_eliminar);
        //btn_actualizar= findViewById(R.id.btn_actualizar);

        String senal="";
        String codigo="";
        String descripcion="";
        String precio="";

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle!= null){
                codigo= bundle.getString("codigo");
                senal=bundle.getString("senal");
                descripcion=bundle.getString("descripcion");
                precio= bundle.getString("precio");
                if(senal.equals("1")){
                    et_codigo.setText(codigo);
                    et_descripcion.setText(descripcion);
                    et_precio.setText(precio);
                }
            }
        }catch (Exception e){

        }
        }

        private void confirmacion(){
        String mensaje ="Realemente desea salir?";
        dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setIcon(R.drawable.ic_seguro);
        dialogo.setTitle("Warning");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {
                MainActivity.this.finish();

            }

        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {

            }
        });
        dialogo.show();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_limpiar) {

            et_codigo.setText(null);
            et_descripcion.setText(null);
            et_precio.setText(null);
            return true;
        } else if (id == R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(MainActivity.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;
        } else if (id == R.id.action_listaArticulos1) {
            Intent listViewActivity = new Intent(MainActivity.this, List_view_articulo.class);
            startActivity(listViewActivity);
        }  else if (id == R.id.recy) {
        Intent listViewActivity = new Intent(MainActivity.this, recyclerview5.class);
        startActivity(listViewActivity);
    }

        return super.onOptionsItemSelected(item);

    }

    public void alta(View v){
        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("Campo obligatorio");
            inputEt=false;

        }else{
            inputEt=true;
        }
        if(et_descripcion.getText().toString().length()==0){
            et_descripcion.setError("Campo obligatorio");
            inputEd=false;

        }else{
            inputEd=true;
        }
        if(et_precio.getText().toString().length()==0){
            et_precio.setError("Campo obligatorio");
            input1=false;

        }else{
            input1=true;
        }
        if(inputEt&& inputEd&& input1) {
            try {
                datos.setCodigo(Integer.parseInt(et_codigo.getText().toString()));
                datos.setDescripcion(et_descripcion.getText().toString());
                datos.setPrecio(Double.parseDouble(et_precio.getText().toString()));

                if (conexion.InsertTradicional(datos)) {
                    Toast.makeText(this, "Registro con exito!!", Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                } else {
                    Toast.makeText(this, "Error. Ya existe un registro\n" +
                            "Codigo: " + et_codigo.getText().toString(), Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error, Ya existe", Toast.LENGTH_SHORT).show();

            }
        }
        }
        public void mensaje (String mensaje){
            Toast.makeText(this, ""+mensaje, Toast.LENGTH_SHORT).show();
    }
    public void limpiarDatos(){
        et_codigo.setText(null);
        et_descripcion.setText(null);
        et_precio.setText(null);
        et_codigo.requestFocus();
    }
    public void consultaporcodigo(){
        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("Campo obligatorio");
            inputEt=false;
        }else{
            inputEt=true;

        }
        if (inputEt){
            String codigo= et_codigo.getText().toString();
            datos.setCodigo(Integer.parseInt(codigo));
            if (conexion.consultaArticulos(datos)){
                et_descripcion.setText(datos.getDescripcion());
                et_precio.setText(""+datos.getPrecio());
            }else {
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
            limpiarDatos();
            }
        }else {
            Toast.makeText(this, "Ingrese el codigo del articulo a buscar", Toast.LENGTH_SHORT).show();
        }
    }
public void consultapordescripcion(){
        if(et_descripcion.getText().toString().length()==0){
            et_descripcion.setError("Campo obligatorio");
            inputEd=false;
        }else {
            inputEd=true;
        }
        if (inputEd){
            String descripcion= et_descripcion.getText().toString();
            datos.setDescripcion(descripcion);
            if (conexion.ConsultarDescripcion(datos)){
                et_codigo.setText(""+datos.getCodigo());
                et_descripcion.setText(datos.getDescripcion());
                et_precio.setText(""+datos.getPrecio());
            }else {
                Toast.makeText(this, "No existe un articulo con dicha descripcion", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        }else {
            Toast.makeText(this, "Ingrese la descripcion del articulo a buscar", Toast.LENGTH_SHORT).show();
        }
}


        public void bajaporcodigo(){
    if (et_codigo.getText().toString().length()==0){
        et_codigo.setError("campo obligatorio");
        inputEt=false;
}else {
        inputEt=true;
    }
    if (inputEt){
        String cod= et_codigo.getText().toString();
        datos.setCodigo(Integer.parseInt(cod));
        if (conexion.bajaCodigo(MainActivity.this,datos)){
            limpiarDatos();
        }else {
            Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
       limpiarDatos();
        }
    }

    }

public  void modificar(){
        if (et_codigo.getText().toString().length()==0){
            et_codigo.setError("campo obliagatorio");
            inputEt=false;
        }else{
            inputEt=true;
        }
        if (inputEt){
            String cod = et_codigo.getText().toString();
            String descripcion= et_descripcion.getText().toString();
            double precio = Double.parseDouble(et_precio.getText().toString());

            datos.setCodigo(Integer.parseInt(cod));
            datos.setDescripcion(descripcion);
            datos.setPrecio(precio);
            if (conexion.modificar(datos)){
                Toast.makeText(this, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void showToast (int opciones,String message){
        LayoutInflater inflater =getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root) );

TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toasImage = layout.findViewById(R.id.toast_image);

        toastText.setText(message);
        if (opciones==1){
            toasImage.setImageResource(R.drawable.ic_save);
        }else if (opciones==2){
            toasImage.setImageResource(R.drawable.ic_buscar);
        }else if (opciones==3){
            toasImage.setImageResource(R.drawable.ic_buscar1);
        }else if (opciones==4){
            toasImage.setImageResource(R.drawable.ic_borrar);
        }else if (opciones==5){
            toasImage.setImageResource(R.drawable.ic_ed1);
        }


        Toast toast =new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.fab){
            morph.show();
        }
        switch (v.getId()){
            case R.id.uno:
               showToast(1 ,"Opción para guardar en BD ");
               alta(null);
                morph.hide();
                break;

            case R.id.dos:
                ventanas.Search(MainActivity.this);
            showToast(2,"Opción para buscar por codigo ");
                morph.hide();
                break;

            case R.id.tres:
              showToast(3,"Opción para buscar por descripcion");
              consultapordescripcion();
                morph.hide();
                break;

            case R.id.cuatro:
               showToast(4,"Opción para borrar registro en BD ");
               bajaporcodigo();
                morph.hide();
                break;

            case R.id.cinco:
              showToast(5,"Opción para editar registro en BD ");
              modificar();
                morph.hide();
                break;

            case R.id.salir:

                morph.hide();
                break;
        }
    }
}