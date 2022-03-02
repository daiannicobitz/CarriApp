package com.example.carriapp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Entidades.CarribarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int posicion){
        holder.bindData(datos.get(posicion));
    }

    public void setItems(List<CarribarView> items){ this.datos=items; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre, direccion, horaCierre, horaApertura;

        ViewHolder(View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imgElementoRecycler);
            nombre = itemView.findViewById(R.id.textViewNombre);
            direccion = itemView.findViewById(R.id.textViewDireccion);
            horaCierre = itemView.findViewById(R.id.textViewCierre);
            horaApertura = itemView.findViewById(R.id.textViewAbierto);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void bindData(final CarribarView item){
            //imagen.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);


            if( calcularAperturaCierre(item.getHoraCierre(), item.getHoraApertura())){
                horaCierre.setVisibility(View.VISIBLE);
                horaApertura.setVisibility(View.GONE);
            }else{
                horaCierre.setVisibility(View.GONE);
                horaApertura.setVisibility(View.VISIBLE);
            }


                nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());
            horaCierre.setText("Cierra a las: " + item.getHoraCierre() + " hs.");
            horaApertura.setText("Abre a las: " + item.getHoraApertura() + " hs.");
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(item);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean calcularAperturaCierre(String horarioCierre, String horarioApertura){

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        LocalTime horaCierre = LocalTime.parse( horarioCierre); //00:50
        LocalTime horaApertura = LocalTime.parse( horarioApertura); //22:14
        LocalTime horaActual = LocalTime.parse(df.format(date)); //1 si horaApertura > horaCierre

        return horaActual.compareTo(horaApertura) == 1 && horaCierre.compareTo(horaActual) == 1;
    }


}
