package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDataBase db;
    String textoNombre,textoDireccion,textoHoraApertura,textoHoraCierre,textoContacto;
    Boolean hamburguesa,choripan,papasFritas,pizza,pancho,milanesa,bondiola,btnDia,btnSemana,btnMes;
    Button botonAgregar;
    RadioGroup grupoBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carribar);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();
//                Carribar carriPrueba = new Carribar(nombreCarribar ,"AA 7013","20","23",
//                        "3434474355", true, true, false, false, false,
//                        false, true);
//                db.carribarDao().insert(carriPrueba);
//        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();
//        System.out.println(listaCarribares.get(0).getDireccion());
//        EditText editTextNombreCarri = (EditText) findViewById(R.id.editTextNombre);
//        String textoNombre = editTextNombreCarri.getText().toString();

        this.botonAgregar = (Button) findViewById(R.id.buttonAgregarCarribar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicializarComponentes();
                validarDatosVacios();
            }
        });

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

    public void validarDatosVacios(){
        if(     this.textoNombre.isEmpty() ||
                this.textoDireccion.isEmpty()||
                this.textoHoraApertura.isEmpty()||
                this.textoHoraCierre.isEmpty()||
                this.textoContacto.isEmpty()){
            System.out.println("Strings Vacios");

        }
        if(     this.hamburguesa == false &&
                this.choripan == false &&
                this.papasFritas == false &&
                this.pancho == false &&
                this.pizza == false &&
                this.milanesa == false &&
                this.bondiola == false){
            System.out.println("CheckBox Vacios");
        }

        if(grupoBotones.getCheckedRadioButtonId() == -1){
            System.out.println("Radio Button Vacios");
        }
    }
}

