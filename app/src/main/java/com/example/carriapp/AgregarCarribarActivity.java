package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarCarribarActivity extends AppCompatActivity {

    String textoNombre,textoDireccion,textoHoraApertura,textoHoraCierre,textoContacto;
    Boolean hamburguesa,choripan,papasFritas,pizza,pancho,milanesa,bondiola,btnDia,btnSemana,btnMes;
    Button botonAgregar, botonTomarFoto;
    RadioGroup grupoBotones;
    byte[] imagen;
    Bitmap bitmapImagen;

    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carribar);

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "prueba2")
                .allowMainThreadQueries()
                .build();

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

                        Carribar carriPrueba = new Carribar(textoNombre ,textoDireccion,textoHoraApertura,textoHoraCierre,
                                textoContacto, hamburguesa, choripan, pizza, papasFritas, pancho, milanesa, bondiola, DataConverter.convertImageToByteArray(bitmapImagen));
                        db.carribarDao().insert(carriPrueba);
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

        this.btnDia = ((RadioButton) findViewById(R.id.radioButtonUnDia)).isChecked();
        this.btnSemana = ((RadioButton) findViewById(R.id.radioButtonUnaSemana)).isChecked();
        this.btnMes = ((RadioButton) findViewById(R.id.radioButtonUnMes)).isChecked();

        this.grupoBotones = ((RadioGroup)findViewById(R.id.radioGrupo));
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

        if(grupoBotones.getCheckedRadioButtonId() == -1){
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