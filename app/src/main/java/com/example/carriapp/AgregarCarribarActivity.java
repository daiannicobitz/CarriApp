package com.example.carriapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarCarribarActivity extends AppCompatActivity {

    String textoNombre,textoDireccion,textoHoraApertura,textoHoraCierre,textoContacto;
    TextView textToolBar;
    Boolean hamburguesa,choripan,papasFritas,pizza,pancho,milanesa,bondiola;
    Button botonAgregar, botonTomarFoto, botonVolver;
    Bitmap bitmapImagen;
    public static final String CANAL01_MENSAJES_ID  = "10001";
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    AppDataBase db;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carribar);

        drawerLayout = findViewById(R.id.drawer_layout_add_carribar);

        textToolBar = findViewById(R.id.textToolBar);
        textToolBar.setText("Agregar un carribar");

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

        this.createNotificationChannel();

        //convert string to double
        Double lat;
        String lat_str = "17.456174";
        lat = Double.parseDouble(lat_str);
        System.out.println(lat);


        imageView = (ImageView) findViewById(R.id.imageViewFotoTomada);
        bitmapImagen = null;

        this.botonAgregar = (Button) findViewById(R.id.buttonAgregarCarribar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicializarComponentes();

                if(!validarDatosVacios()){
                    System.out.println("Datos No vacios");
                    if(validarFormato()){
                        System.out.println("Datos bien formateados");

                       LatLng latLng =  determineLatLngFromAddress(AgregarCarribarActivity.this, textoDireccion + " , Santa Fe" );
                        double dLat = latLng.latitude;
                        double dLon = latLng.longitude;
                        String strLat = Double.toString(dLat);
                        String strLon = Double.toString(dLon);


                        Carribar carriPrueba = new Carribar(textoNombre ,textoDireccion, strLat, strLon, textoHoraApertura,textoHoraCierre,
                                textoContacto, hamburguesa, choripan, pizza, papasFritas, pancho, milanesa, bondiola, DataConverter.convertImageToByteArray(bitmapImagen));
                        db.carribarDao().insert(carriPrueba);

                        Intent i = new Intent();
                        i.putExtra (" data1", "A" );
                        i.putExtra (" data12", "B" );
                        i.setAction (MyReceiver1.EVENTO_01);
                        sendBroadcast(i);

                    }else{
                        System.out.println("Datos mal formateados");
                    }
                }else{
                    System.out.println("Datos vacios");
                }
            }
        });

        this.botonTomarFoto = (Button) findViewById(R.id.buttonTomarFoto);
        botonTomarFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        this.botonVolver = (Button) findViewById(R.id.buttonCancelar);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    public void ClickMenu(View view) {
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAddCarribar(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickVerCarribar(View view) {
        MainActivity.redirectActivity(this, ListaCarribaresActivity.class);
    }

    public void ClickSalir(View view) {
        MainActivity.salir(this);
    }

    private void createNotificationChannel() {

        System.out.println("Crea Canal");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 =
                    new NotificationChannel(CANAL01_MENSAJES_ID, "CANAL 1", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("Descripcion 1");
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }

    public LatLng determineLatLngFromAddress(Context appContext, String strAddress) {
        LatLng latLng = null;
        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());
        List<Address> geoResults = null;

        try {
            geoResults = geocoder.getFromLocationName(strAddress, 1);
            while (geoResults.size()==0) {
                geoResults = geocoder.getFromLocationName(strAddress, 1);
            }
            if (geoResults.size()>0) {
                Address addr = geoResults.get(0);
                latLng = new LatLng(addr.getLatitude(),addr.getLongitude());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return latLng; //LatLng value of address
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ){
            Bundle extras = data.getExtras();
            bitmapImagen = (Bitmap) extras.get ("data");
            imageView.setImageBitmap(bitmapImagen);
        }
    }

    public void inicializarComponentes(){

        this.textoNombre = ((EditText) findViewById(R.id.editTextNombre)).getText().toString();
        this.textoDireccion = ((EditText) findViewById(R.id.editTextDireccion)).getText().toString();
        this.textoHoraApertura = ((EditText) findViewById(R.id.editTextHoraApertura)).getText().toString();
        this.textoHoraCierre = ((EditText) findViewById(R.id.editTextHoraCierre)).getText().toString();
        this.textoContacto = ((EditText) findViewById(R.id.editTextMultilineInfoContacto)).getText().toString();

        this.hamburguesa = ((CheckBox) findViewById(R.id.checkBoxHayHamburguesa)).isChecked();
        this.choripan = ((CheckBox) findViewById(R.id.checkBoxHayChoripan)).isChecked();
        this.papasFritas = ((CheckBox) findViewById(R.id.checkBoxHayPapasFritas)).isChecked();
        this.pancho = ((CheckBox) findViewById(R.id.checkBoxHayPancho)).isChecked();
        this.pizza = ((CheckBox) findViewById(R.id.checkBoxHayPizza)).isChecked();
        this.milanesa = ((CheckBox) findViewById(R.id.checkBoxHayMilanesa)).isChecked();
        this.bondiola = ((CheckBox) findViewById(R.id.checkBoxHayBondiola)).isChecked();


    }

    public boolean validarDatosVacios(){
        if(     this.textoNombre.isEmpty() ||
                this.textoDireccion.isEmpty()||
                this.textoHoraApertura.isEmpty()||
                this.textoHoraCierre.isEmpty()||
                this.textoContacto.isEmpty() ||
                this.bitmapImagen == null){
            return true;
        }

        if(     this.hamburguesa == false &&
                this.choripan == false &&
                this.papasFritas == false &&
                this.pancho == false &&
                this.pizza == false &&
                this.milanesa == false &&
                this.bondiola == false){
            return true;
        }


        return false;
    }

    public boolean validarFormato(){

        String caracteresDireccion = "[A-Za-z]+\\s[0-9]";
        String caracteresHora = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        Pattern patronDireccion = Pattern.compile(caracteresDireccion);
        Matcher emparejadorDireccion = patronDireccion.matcher(this.textoDireccion);

        Pattern patronHora = Pattern.compile(caracteresHora);
        Matcher emparejadorHoraApertura = patronHora.matcher(this.textoHoraApertura);
        Matcher emparejadorHoraCierre = patronHora.matcher(this.textoHoraCierre);

        boolean esCoincidente = emparejadorDireccion.find();
        boolean esCoincidente1 = emparejadorHoraApertura.find();
        boolean esCoincidente2 = emparejadorHoraCierre.find();

        if(!esCoincidente) return false;
        if(!esCoincidente1) return false;
        if(!esCoincidente2) return false;

        return true;
    }

}