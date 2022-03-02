package com.example.carriapp;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.Locale;

public class MapasActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private MarkerOptions markinicio, markfin;
    private Polyline currentPolyline;
    Button getDirection;
    private FusedLocationProviderClient fusedLocationClient;
    Double latitudUbicacionActual;
    Double longitudUbicacionActual;
    DrawerLayout drawerLayout;
    TextView textToolBar;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        drawerLayout = findViewById(R.id.drawer_layout);

        textToolBar = findViewById(R.id.textToolBar);
        textToolBar.setText("Mapa");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getDirection = findViewById(R.id.btnGetDirection);

        Double valor1 = (Double) getIntent().getSerializableExtra("LatitudCarri");
        Double valor2 = (Double) getIntent().getSerializableExtra("LongitudCarri");

        markinicio = new MarkerOptions().position(new LatLng(valor1, valor2)).title("Carribar");
        markfin = new MarkerOptions().position(new LatLng(-31.617436, -60.675379)).title("Ubicacion Actual");
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.choripan));

        SupportMapFragment mapFragment;
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        getDirection.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                actualizarMapa();
                new FetchURL(MapasActivity.this).execute(getUrl(markinicio.getPosition(), markfin.getPosition(), "driving"), "driving");
//                new GetCoordinates().execute("1600 Amphitheatre Parkway, Mountain View, CA");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Log.d("mylog", "Added Markers");
        mMap.addMarker(markinicio);
        mMap.addMarker(markfin);
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
        private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    @SuppressLint("MissingPermission")
    private void ejecutarActualizacionMapa(){
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    public void actualizarMapa(){
        if(noTienePermiso()){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},9999);
        } else {
            ejecutarActualizacionMapa();
        }
    }

    private boolean noTienePermiso(){
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult (int requestCode , String [] permissions , int [] grantResults ){

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            ejecutarActualizacionMapa();
        }else{
            Toast.makeText(MapasActivity.this , "No tiene permiso para obtener la ubicacion actual", Toast.LENGTH_LONG).show();
        }

    }

    public void ClickMenu(View view) { MainActivity.openDrawer(drawerLayout); }

    public void ClickLogo(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAddCarribar(View view) { MainActivity.redirectActivity(this, AgregarCarribarActivity.class); }

    public void ClickVerCarribar(View view) { MainActivity.redirectActivity(this, VerCarribarActivity.class); }

    public void ClickSalir(View view) {
        MainActivity.salir(this);
    }


}

