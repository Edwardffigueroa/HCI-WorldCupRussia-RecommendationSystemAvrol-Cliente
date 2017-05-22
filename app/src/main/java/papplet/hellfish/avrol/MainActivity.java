package papplet.hellfish.avrol;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import serial.Usuario;

public class MainActivity extends AppCompatActivity implements Observer {


    private static final int REGISTRO_REQ_ACT = 0;
    private static final String TAG = "MainActivity";
    private String mensajePantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Comunicacion.getInstance().addObserver(this);


        mensajePantalla = "...";
        initBotonLogin();


    }

    private void initBotonLogin() {
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usuario = (EditText) findViewById(R.id.ed_txt_user);
                EditText contrasena = (EditText) findViewById(R.id.ed_txt_pass);

                String user = usuario.getText().toString();
                String pass = contrasena.getText().toString();

                if (!user.equals("") && !pass.equals("")) {
                    Comunicacion.getInstance().enviar("codigo:" + user + ":" + pass);

                } else {
                   // usuario.setHintTextColor(Color.RED);
                    //contrasena.setHintTextColor(Color.RED);
                    //usuario.setHighlightColor(Color.RED);
                    //contrasena.setHighlightColor(Color.RED);
                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {

        String mensaje = (String) data;
        Log.d("sdf", mensaje);

        if (mensaje.contains("CÓDIGO ACEPTADO")){
                mostrarMensajeToast("Código Aceptado");

            Intent i = new Intent(getApplicationContext(), EnviarEquipo.class);
            startActivity(i);

        }

    }

    private void mostrarMensajeToast(String mensajeAMostrar) {
        mensajePantalla = mensajeAMostrar;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mensajePantalla, Toast.LENGTH_LONG).show();
            }
        });
    }



}
