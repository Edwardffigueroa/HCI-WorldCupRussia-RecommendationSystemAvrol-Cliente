package papplet.hellfish.avrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class EnviarEquipo extends AppCompatActivity implements Observer{

    Spinner sp_paises_U;
    Spinner sp_paises_D;
    Spinner sp_paises_T;
    Spinner sp_paises_C;

    Button enviar;


    String[] items;

    String equipoUno="";
    String equipoDos="";
    String equipoTres="";
    String equipoCuatro="";

    private boolean firstTime = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_equipo);

        Comunicacion.getInstance().addObserver(this);

        sp_paises_U = (Spinner) findViewById(R.id.sp_paises_U);
        sp_paises_D = (Spinner) findViewById(R.id.sp_paises_D);
        sp_paises_T = (Spinner) findViewById(R.id.sp_paises_T);
        sp_paises_C = (Spinner) findViewById(R.id.sp_paises_C);


        enviar = (Button) findViewById(R.id.enviar);

        items = getResources().getStringArray(R.array.paises);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_paises_U.setAdapter(adapter);
        sp_paises_U.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstTime){
                    firstTime = false;
                }else{
                    Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                    String[] partes=items[i].split("-");
                    equipoUno=partes[1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_paises_D.setAdapter(adapter);
        sp_paises_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstTime){
                    firstTime = false;
                }else{
                    Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                    String[] partes=items[i].split("-");
                    equipoDos=partes[1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_paises_T.setAdapter(adapter);
        sp_paises_T.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstTime){
                    firstTime = false;
                }else{
                    Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                    String[] partes=items[i].split("-");
                    equipoTres=partes[1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_paises_C.setAdapter(adapter);
        sp_paises_C.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstTime){
                    firstTime = false;
                }else{
                    Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                    String[] partes=items[i].split("-");
                    equipoCuatro=partes[1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        enviar();
    }


    public void enviar(){
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar("datos"+":"+equipoUno+":"+equipoDos+":"+equipoTres+":"+equipoCuatro);
                Toast.makeText(getApplicationContext(),equipoUno+":"+equipoDos+":"+equipoTres+":"+equipoCuatro,Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}