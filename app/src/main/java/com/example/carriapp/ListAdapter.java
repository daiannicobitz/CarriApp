package com.example.carriapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Entidades.CarribarView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<CarribarView> datos;
    private LayoutInflater inflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(CarribarView item);
    }

    public ListAdapter(List<CarribarView> lista, Context context, ListAdapter.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        this.datos=lista;
        this.listener=listener;
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

    public void setItems(List<CarribarView> items){ this.datos=items; }

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

        void bindData(final CarribarView item){
            //imagen.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());
            horaCierre.setText(item.getHoraCierre() + " hs.");
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(item);
                }
            });
        }
    }


}
