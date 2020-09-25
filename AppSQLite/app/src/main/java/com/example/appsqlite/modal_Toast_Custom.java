package com.example.appsqlite;


import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class modal_Toast_Custom extends AppCompatActivity {


    Dialog myDialog;
    androidx.appcompat.app.AlertDialog.Builder dialogo;
    AlertDialog.Builder dialogo1;


    public void dialogAbout(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.acercade);
        myDialog.setCancelable(false);

        ImageView BtnCerrarAutor = myDialog.findViewById(R.id.BtnCerrarAutor);

        BtnCerrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }






    public void dialogConfirmCustom2(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.aviso_regresar_inicio);
        myDialog.setCancelable(false);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.ivClose);
        Button btnAccept = (Button)myDialog.findViewById(R.id.btnAccept);
        Button btnCancel = myDialog.findViewById(R.id.btnCancel);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clic en Aceptar.", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                //startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clic en Cancelar", Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void salirapp(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.aviso_salir_app);
        myDialog.setCancelable(false);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.cerrar);
        Button btnAccept = (Button)myDialog.findViewById(R.id.acep);
        Button btnCancel = myDialog.findViewById(R.id.cancel);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clic en Acep.", Toast.LENGTH_SHORT).show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clic en Cancelar", Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



}

