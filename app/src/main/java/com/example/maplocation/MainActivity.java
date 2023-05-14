package com.example.maplocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Element
    private TextView txtViewLatGPS;
    private TextView txtViewLongGPS;
    private TextView txtViewAltGPS;
    private TextView txtViewPaysGPS;
    private TextView txtViewVilleGPS;
    private TextView txtViewProvinceGPS;
    private TextView txtViewPostalCodeGPS;
//    private Button btnGPS;
    private Button btnPostLocation;
    private Button btnShow_MyLoc_OnMap;
    private Button btnHide;
    public static ListView lstUsers;
    //===


    //Location==
    private LocationManager mLocationManagerGPS;
    private LocationListener mLocationListenerGPS;
    //=-=====


    //POUR DES PLUS DETAILLER
    private Geocoder geocoder;
    //======================

    //AsyncTask tache===
    double latitude;
    double longitude;
    double altitude;
    String command;
    String pays;
    String ville;
    String province;
    String postalCode;
    String adressName;
    //=====


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Element
        txtViewLatGPS = findViewById(R.id.txtViewLatGPS);
        txtViewLongGPS = findViewById(R.id.txtViewLonGPS);
        txtViewAltGPS = findViewById(R.id.txtViewAltGPS);
        txtViewPaysGPS = findViewById(R.id.txtViewPaysGPS);
        txtViewVilleGPS = findViewById(R.id.txtViewVilleGPS);
        txtViewProvinceGPS = findViewById(R.id.txtViewProvinceGPS);
        txtViewPostalCodeGPS = findViewById(R.id.txtViewPostalCodeGPS);
//        btnGPS = findViewById(R.id.btnGPSLoc);
        btnPostLocation = findViewById(R.id.btnPostLocation);
        btnShow_MyLoc_OnMap = findViewById(R.id.btnShow_MyLoc_OnMap);
        btnHide = findViewById(R.id.btnHide);
        lstUsers = findViewById(R.id.lstUsers);
        //===

        //Get my Position on Load
        getPositionGPS();
        //============

        //DEMANDE DE REQUETTE POUR SELECT la LOCALISATION  de mes amis ON LOAD==================
        MyAsyncTask myAsyncTAsk = new MyAsyncTask(MainActivity.this);
        command = "selectAll";
        myAsyncTAsk.execute(command);//on Set un ListView dans myAsyncTask
        //========


        //Hide ma localisation===
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DEMANDE DE REQUETTE POUR Update MA localisation to > HIDE==================
                MyAsyncTask myAsyncTAsk = new MyAsyncTask(MainActivity.this);
                command = "update";
                myAsyncTAsk.execute(command,MyAsyncTask.nom,MyAsyncTask.prenom);//on Set un ListView dans myAsyncTask
                //========

                //DEMANDE DE REQUETTE POUR !!!Re-SELECT!! la LOCALISATION  de mes amis==================
                myAsyncTAsk = new MyAsyncTask(MainActivity.this);
                command = "selectAll";
                myAsyncTAsk.execute(command);//on Set un ListView dans myAsyncTask
                //========
            }
        });
        //========================



        //on Set un ListView dans myAsyncTask
        //onLonClick > On Affiche sur map les coordonner du user clicked
        lstUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int _position, long id) {
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("adressName",MyAsyncTask.arrayOfUsers.get(_position).getVille()+":"+MyAsyncTask.arrayOfUsers.get(_position).getPays());
                i.putExtra("latitude",MyAsyncTask.arrayOfUsers.get(_position).getLatitude());
                i.putExtra("longitude",MyAsyncTask.arrayOfUsers.get(_position).getLongitude());
                startActivity(i);
                return false;
            }
        });
        //===========================



//        btnGPS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getPositionGPS();
//            }
//        });


        btnShow_MyLoc_OnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("adressName",adressName);
                i.putExtra("latitude",latitude);
                i.putExtra("longitude",longitude);
                startActivity(i);
            }
        });
    }


    private void getPositionGPS() {

        mLocationManagerGPS = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        mLocationListenerGPS = new LocationListener() {

            public void onLocationChanged(Location location) {

                String x = "";
                //Lantitude +  Longitude
                latitude = location.getLatitude();
                longitude =location.getLongitude();
                altitude = location.getAltitude();
                //===
                txtViewLatGPS.setText(Double.toString(latitude));
                txtViewLongGPS.setText(Double.toString(longitude));
                txtViewAltGPS.setText(Double.toString(altitude));
                //=====================


                //POUR DES PLUS DETAILLER===========================================================
                geocoder = new Geocoder(MainActivity.this);

                //Address est une class deja existante dans le systeme
                List<Address> myAddressList = null;

                try {
                    myAddressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                    if(myAddressList.size()>0){

                        pays = myAddressList.get(0).getCountryName();
                        txtViewPaysGPS.setText(pays);

                        ville = myAddressList.get(0).getLocality();
                        txtViewVilleGPS.setText(ville);

                        adressName=pays+":"+ville;

                        province = myAddressList.get(0).getAdminArea();
                        txtViewProvinceGPS.setText(province);

                        postalCode = myAddressList.get(0).getPostalCode();
                        txtViewPostalCodeGPS.setText(postalCode);

                        btnPostLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //DEMANDE DE REQUETTE POUR INSERT Ma LOCALISATION==================
                                MyAsyncTask myAsyncTAsk = new MyAsyncTask(MainActivity.this);
                                command = "insert";
                                myAsyncTAsk.execute(command,latitude,longitude,altitude,pays,ville,province,postalCode,MyAsyncTask.nom,MyAsyncTask.prenom);
                                //===========================

                                //DEMANDE DE REQUETTE POUR !!!Re-SELECT!! la LOCALISATION  de mes amis==================
                                myAsyncTAsk = new MyAsyncTask(MainActivity.this);
                                command = "selectAll";
                                myAsyncTAsk.execute(command);//on Set un ListView dans myAsyncTask
                                //========
                            }
                        });


                    }else{
                        Log.i("tag","geocoder n'a pas pu trouver les information supplementaire");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //==================================================================================
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setTitle("Configuration du GPS");
                alertDialog.setMessage("le GPS n'est pas actif, voullez vous allez au menu de configuration?");

                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });

                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); //enlever l'alertDialague
                    }
                });

                alertDialog.show();
            }
        };

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestLocationPermission();
            } else {
//                btnGPS.setEnabled(false);
                mLocationManagerGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0, mLocationListenerGPS);
            }
        }
    }




    private void requestLocationPermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            alertDialog.setTitle("PERMISSIONS");
            alertDialog.setMessage("Vous devez accepeter les permissions pour utiliser cette application!!!");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                }
            });

            alertDialog.show();
        }
        else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            alertDialog.setTitle("PERMISSIONS");
            alertDialog.setMessage("Vous devez accepeter les permissions pour utiliser cette application!!!");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName())));
                }
            });

            alertDialog.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();

        }
    }


//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (mLocationManagerGPS != null) {
//            mLocationManagerGPS.removeUpdates(mLocationListenerGPS);
//        }
//
//
//        txtViewLatGPS.setText(null);
//        txtViewLongGPS.setText(null);
//        txtViewAltGPS.setText(null);
//
//    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (!btnGPS.isEnabled()) {
//            btnGPS.setEnabled(true);
//        }
//    }




}