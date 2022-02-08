package com.example.carriapp;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> datos;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(List<ListElement> lista, Context context){
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        this.datos=lista;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int posicion){
        holder.bindData(datos.get(posicion));
    }

    public void setItems(List<ListElement> items){ this.datos=items; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre, direccion, horaCierre;

        ViewHolder(View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imgElementoRecycler);
            nombre = itemView.findViewById(R.id.textViewNombre);
            direccion = itemView.findViewById(R.id.textViewDireccion);
            horaCierre = itemView.findViewById(R.id.textViewCierre);
        }

        void bindData(final ListElement item){
            imagen.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());
            horaCierre.setText(item.getHoraCierre());
        }
    }

}
