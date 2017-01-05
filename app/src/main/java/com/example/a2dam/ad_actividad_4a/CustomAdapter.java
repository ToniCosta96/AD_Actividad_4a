package com.example.a2dam.ad_actividad_4a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 03/01/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListaViewHolder>{
    private ArrayList<Elemento> listData;

    public CustomAdapter(ArrayList<Elemento> listData) {
        this.listData = listData;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_elemento, parent, false);
        ListaViewHolder lvh = new ListaViewHolder(itemView);
        return lvh;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        Elemento item = listData.get(position);
        //Método bindLista de la clase ListaViewHolder
        holder.bindLlista(item);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder{
        //Datos del view
        private TextView textViewIdentificador;
        private TextView textViewNombre;

        public ListaViewHolder(View itemView) {
            super(itemView);

            textViewIdentificador = (TextView) itemView.findViewById(R.id.textViewId);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
        }

        public void bindLlista(Elemento elementLlista) {
            textViewIdentificador.setText("Identificador: "+String.valueOf(elementLlista.getIdentificador()));
            textViewNombre.setText("Nombre: "+elementLlista.getNombre());
        }
    }
}
