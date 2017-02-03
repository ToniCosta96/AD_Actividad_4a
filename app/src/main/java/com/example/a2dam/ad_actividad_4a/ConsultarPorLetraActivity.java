package com.example.a2dam.ad_actividad_4a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ConsultarPorLetraActivity extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    private ArrayList<Elemento> arrayElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_por_letra);

        final EditText editText= (EditText)findViewById(R.id.editText_letra);
        final Button buttonConsulta= (Button)findViewById(R.id.btn_letra);
        final RecyclerView recyclerView= (RecyclerView)findViewById(R.id.recyclerLetra);

        //Iniciar Adapter
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();

        //RecyclerView
        arrayElementos= new ArrayList<>();
        final CustomAdapter adaptador = new CustomAdapter(arrayElementos);
        recyclerView.setAdapter(adaptador);
        //linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Boton consultas
        buttonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Vaciar recyclerView
                arrayElementos.clear();
                String valor= editText.getText().toString()+"%";
                dbAdapter.seleccionar(arrayElementos,valor,MyDBAdapter.NOMBRE,MyDBAdapter.DATABASE_ESTUDIANTE);
                dbAdapter.seleccionar(arrayElementos,valor,MyDBAdapter.NOMBRE,MyDBAdapter.DATABASE_PROFESOR);
                adaptador.notifyDataSetChanged();
            }
        });
    }
}
