package com.example.a2dam.ad_actividad_4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ConsultasActivity extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    private ArrayList<Elemento> arrayElementos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        final Spinner spinnerBusqueda1= (Spinner)findViewById(R.id.spinner_busqueda1);
        final Spinner spinnerBusqueda2= (Spinner)findViewById(R.id.spinner_busqueda2);
        final EditText editTextConsulta= (EditText)findViewById(R.id.editTextConsulta);
        final Spinner spinnerConsulta= (Spinner)findViewById(R.id.spinner_consulta);
        final Button btnBusqueda= (Button)findViewById(R.id.btn_busqueda);
        final RecyclerView recyclerView= (RecyclerView)findViewById(R.id.recyclerConsultas);

        //Iniciar Adapter
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();

        //Llenar spinners
        ArrayAdapter<String> adaptadorBusqueda1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipo_consulta_1));
        spinnerBusqueda1.setAdapter(adaptadorBusqueda1);
        ArrayAdapter<String> adaptadorBusqueda2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipo_consulta_2));
        spinnerBusqueda2.setAdapter(adaptadorBusqueda2);
        ArrayAdapter<String> adaptadorConsulta = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ciclo_y_curso));
        spinnerConsulta.setAdapter(adaptadorConsulta);

        //RecyclerView
        arrayElementos= new ArrayList<>();
        final CustomAdapter adaptador = new CustomAdapter(arrayElementos);
        recyclerView.setAdapter(adaptador);
        //linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        spinnerBusqueda1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i) {
                    case 0:
                    case 1:
                        editTextConsulta.setVisibility(View.VISIBLE);
                        spinnerConsulta.setVisibility(View.GONE);
                        break;
                    case 2:
                        editTextConsulta.setVisibility(View.GONE);
                        spinnerConsulta.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Vaciar recyclerView
                arrayElementos.clear();
                //Se prepara el tipo de consulta
                String nombreColumna="";
                String valor="";
                switch((int) spinnerBusqueda1.getSelectedItemId()){
                    case 0:
                        nombreColumna= dbAdapter.ID;
                        valor=editTextConsulta.getText().toString();
                        break;
                    case 1:
                        nombreColumna= dbAdapter.NOMBRE;
                        valor=editTextConsulta.getText().toString();
                        break;
                    case 2:
                        nombreColumna= dbAdapter.CICLO_Y_CURSO;
                        valor=spinnerConsulta.getSelectedItem().toString();
                        break;
                }
                //Acceder a la base de datos
                switch((int) spinnerBusqueda2.getSelectedItemId()){
                    case 0:
                        dbAdapter.seleccionar(arrayElementos,valor,nombreColumna,dbAdapter.DATABASE_ESTUDIANTE);
                        break;
                    case 1:
                        dbAdapter.seleccionar(arrayElementos,valor,nombreColumna,dbAdapter.DATABASE_PROFESOR);
                        break;
                    case 2:
                        dbAdapter.seleccionar(arrayElementos,valor,nombreColumna,dbAdapter.DATABASE_ESTUDIANTE);
                        dbAdapter.seleccionar(arrayElementos,valor,nombreColumna,dbAdapter.DATABASE_PROFESOR);
                        break;
                }
                //Notificar cambios al adaptador
                adaptador.notifyDataSetChanged();
            }
        });
    }
}
