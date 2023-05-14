package com.example.maplocation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask {

    
    private Context ctx;

    //Return de doInBackground
    StringBuffer myStringB;
    //=====================

    //show result
    private AlertDialog myAlertDialogue;
    public static ArrayList<User> arrayOfUsers;
    public static String loginStatut ="pending";
    public static String registerStatut ="pending";
    public static String nom;
    public static String prenom;
    //====

    //Firebase=======
    private FirebaseStorage fbStorage;
    private StorageReference storageReference;
    //==============


    //Constructeuur
    public MyAsyncTask(Context _ctx){
        ctx=_ctx;
    }
    ///========

    //Avant l'execution du doInBackground=====
    @Override
    protected void onPreExecute() {
//        super.onPreExecute();

        //Alert dialaogue
        myAlertDialogue = new AlertDialog.Builder(ctx).create();
        myAlertDialogue.setTitle("Le Statut de la requette");
        //======


        //Firebase=======
        fbStorage = FirebaseStorage.getInstance();
        storageReference = fbStorage.getReference();
        //==============

    }
    //==========


    @Override
    //Object... : autant de params de tout type.
    protected Object doInBackground(Object[] param) {
//        return null;



        if(param[0]=="insertUser"){
            String myPhpSelect_Url = "http://172.20.10.2/externDb/insertRegisterUser.php";

            try{

                //Requette========================================
                URL myUrl = new URL(myPhpSelect_Url);
                HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
                myConn.setDoInput(true);
                myConn.setDoOutput(true);
                myConn.setRequestMethod("POST");
                //====================

                //Envoyer la requette===================================================
                OutputStream myOutS = myConn.getOutputStream();
                BufferedWriter myBufW = new BufferedWriter(new OutputStreamWriter(myOutS,"utf-8"));

                //!!!!!!!mettre les Variable dans le Link en parametre!!!!!!!!!
                String msg = URLEncoder.encode("nom","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[1]),"utf-8") + "&" + URLEncoder.encode("prenom","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[2]),"utf-8") + "&" + URLEncoder.encode("photo","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[3]),"utf-8")+ "&" + URLEncoder.encode("userName","utf-8") + "=" + URLEncoder.encode((String)param[4],"utf-8")+ "&" + URLEncoder.encode("pass","utf-8") + "=" + URLEncoder.encode((String)param[5],"utf-8");
                //!!!!!!!!!!!!!!=================================

                myBufW.write(msg);
                myBufW.flush();
                myBufW.close();
                myOutS.close();
                //========================================================================

                //Recuperer la Reponse de php dans > myStringB=====================
                InputStream myInpS = myConn.getInputStream();
                BufferedReader myBufR = new BufferedReader(new InputStreamReader(myInpS,"iso-8859-1"));

                String resultat;

                myStringB = new StringBuffer();

                while ( (resultat=myBufR.readLine()) != null ){
                    myStringB.append(resultat + "\n");
                }
                //===================================================

            }catch(Exception ex){
                return ex.getMessage();
            }
        }


        if(param[0]=="login"){
//            String myPhpSelect_Url = "http://elmehdimyphp.great-site.net/checkLogin.php";

            String myPhpSelect_Url = "http://172.20.10.2/externDb/checkLogin.php";

            try{

                //Requette========================================
                URL myUrl = new URL(myPhpSelect_Url);

                HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
                myConn.setDoInput(true);
                myConn.setDoOutput(true);
                myConn.setRequestMethod("POST");
                //====================

                //Envoyer la requette===================================================
                OutputStream myOutS = myConn.getOutputStream();
                BufferedWriter myBufW = new BufferedWriter(new OutputStreamWriter(myOutS,"utf-8"));

                //!!!!!!!mettre les Variable dans le Link en parametre!!!!!!!!!
                String msg = URLEncoder.encode("userName","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[1]),"utf-8") + "&" + URLEncoder.encode("pass","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[2]),"utf-8");
                //!!!!!!!!!!!!!!=================================

                myBufW.write(msg);
                myBufW.flush();
                myBufW.close();
                myOutS.close();
                //========================================================================


                //Recuperer la Reponse de php dans > myStringB=====================
                InputStream myInpS = myConn.getInputStream();
                BufferedReader myBufR = new BufferedReader(new InputStreamReader(myInpS,"iso-8859-1"));

                String resultat;

                myStringB = new StringBuffer();

                while ( (resultat=myBufR.readLine()) != null ){
                    myStringB.append(resultat + "\n");
                }
                //===================================================



            }catch(Exception ex){
                return ex.getMessage();
            }
        }

        if(param[0]=="selectAll"){
            String myPhpSelect_Url = "http://172.20.10.2/externDb/selectAllLocations.php";

            try{
                //Requette========================================
                URL myUrl = new URL(myPhpSelect_Url);
                HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
                myConn.setDoInput(true);
                myConn.setDoOutput(true);
                myConn.setRequestMethod("POST");
                //====================

                //Envoyer la requette===================================================
                OutputStream myOutS = myConn.getOutputStream();
                BufferedWriter myBufW = new BufferedWriter(new OutputStreamWriter(myOutS,"utf-8"));

                myBufW.flush();
                myBufW.close();
                myOutS.close();
                //========================================================================

                //Recuperer la Reponse de php dans > myStringB=====================
                InputStream myInpS = myConn.getInputStream();
                BufferedReader myBufR = new BufferedReader(new InputStreamReader(myInpS,"iso-8859-1"));

                String resultat;

                myStringB = new StringBuffer();

                while ( (resultat=myBufR.readLine()) != null ){
                    myStringB.append(resultat + "\n");
                }
                //===================================================


            }catch(Exception ex){
                return ex.getMessage();
            }
        }


        if(param[0]=="insert"){
            String myPhpSelect_Url = "http://172.20.10.2/externDb/insertLocation.php";

            try{

                //Requette========================================
                URL myUrl = new URL(myPhpSelect_Url);
                HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
                myConn.setDoInput(true);
                myConn.setDoOutput(true);
                myConn.setRequestMethod("POST");
                //====================

                //Envoyer la requette===================================================
                OutputStream myOutS = myConn.getOutputStream();
                BufferedWriter myBufW = new BufferedWriter(new OutputStreamWriter(myOutS,"utf-8"));

                    //!!!!!!!mettre les Variable dans le Link en parametre!!!!!!!!!
                    String msg = URLEncoder.encode("latitude","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[1]),"utf-8") + "&" + URLEncoder.encode("longitude","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[2]),"utf-8") + "&" + URLEncoder.encode("altitude","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[3]),"utf-8")+ "&" + URLEncoder.encode("pays","utf-8") + "=" + URLEncoder.encode((String)param[4],"utf-8")+ "&" + URLEncoder.encode("ville","utf-8") + "=" + URLEncoder.encode((String)param[5],"utf-8")+ "&" + URLEncoder.encode("province","utf-8") + "=" + URLEncoder.encode((String)param[6],"utf-8")+ "&" + URLEncoder.encode("postalCode","utf-8") + "=" + URLEncoder.encode((String)param[7],"utf-8")+ "&" + URLEncoder.encode("nom","utf-8") + "=" + URLEncoder.encode((String)param[8],"utf-8")+ "&" + URLEncoder.encode("prenom","utf-8") + "=" + URLEncoder.encode((String)param[9],"utf-8");
                    //!!!!!!!!!!!!!!=================================

                myBufW.write(msg);
                myBufW.flush();
                myBufW.close();
                myOutS.close();
                //========================================================================

                //Recuperer la Reponse de php dans > myStringB=====================
                InputStream myInpS = myConn.getInputStream();
                BufferedReader myBufR = new BufferedReader(new InputStreamReader(myInpS,"iso-8859-1"));

                String resultat;

                myStringB = new StringBuffer();

                while ( (resultat=myBufR.readLine()) != null ){
                    myStringB.append(resultat + "\n");
                }
                //===================================================



            }catch(Exception ex){
                return ex.getMessage();
            }
        }


        if(param[0]=="update"){
            String myPhpSelect_Url = "http://172.20.10.2/externDb/updateLocationToHide.php";

            try{

                //Requette========================================
                URL myUrl = new URL(myPhpSelect_Url);
                HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
                myConn.setDoInput(true);
                myConn.setDoOutput(true);
                myConn.setRequestMethod("POST");
                //====================

                //Envoyer la requette===================================================
                OutputStream myOutS = myConn.getOutputStream();
                BufferedWriter myBufW = new BufferedWriter(new OutputStreamWriter(myOutS,"utf-8"));

                //!!!!!!!mettre les Variable dans le Link en parametre!!!!!!!!!
                String msg = URLEncoder.encode("nom","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[1]),"utf-8") + "&" + URLEncoder.encode("prenom","utf-8") + "=" + URLEncoder.encode(String.valueOf(param[2]),"utf-8");
                //!!!!!!!!!!!!!!=================================

                myBufW.write(msg);
                myBufW.flush();
                myBufW.close();
                myOutS.close();
                //========================================================================

                //Recuperer la Reponse de php dans > myStringB=====================
                InputStream myInpS = myConn.getInputStream();
                BufferedReader myBufR = new BufferedReader(new InputStreamReader(myInpS,"iso-8859-1"));

                String resultat;

                myStringB = new StringBuffer();

                while ( (resultat=myBufR.readLine()) != null ){
                    myStringB.append(resultat + "\n");
                }
                //===================================================


            }catch(Exception ex){
                return ex.getMessage();
            }
        }






        //return le response=========
        return  myStringB.toString();
        //==========================

    }

    //Apres l'execution de Don in Background
    @Override
    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);

//        myAlertDialogue.setMessage( (String) o );
//        myAlertDialogue.show();

        String response = (String) o;
        response = response.trim();


        if(response.equals("votre localisation a ete partager avec succes") || response.equals("votre localisation a ete HIDE avec succes")){
            myAlertDialogue.setMessage( response );
            myAlertDialogue.show();
        } else if(response.contains(":,:")){
            //recuperer le nom et le prenom du User qui c connecter
            //il vont etre utiliser apres pour faire des requette
            String[] array = response.split(":,:");
            nom = array[0];
            prenom = array[1];
            Intent i = new Intent(ctx,MainActivity.class);
            ctx.startActivity(i);
        }else if(response.contains("Register:")){
            if(response.contains("registerSucces")){
                Intent i = new Intent(ctx,Login.class);
                ctx.startActivity(i);
            }
        }else{
            myAlertDialogue.dismiss();

            String[] arrayOfLignes = response.split("//");

            arrayOfUsers = new ArrayList<>();
            for(int i=1;i<arrayOfLignes.length;i++){
                String[] arrayOfValues = arrayOfLignes[i].split(",");
                 int id = Integer.parseInt(arrayOfValues[0]);
                 String nom = arrayOfValues[1];
                 String prenom = arrayOfValues[2];
                 int photo= Integer.parseInt(arrayOfValues[3]);
                 double latitude = Double.parseDouble(arrayOfValues[4]);
                 double longitude = Double.parseDouble(arrayOfValues[5]);
                 double altitude = Double.parseDouble(arrayOfValues[6]);
                 String pays = arrayOfValues[7];
                 String ville = arrayOfValues[8];
                 String province = arrayOfValues[9];
                 String postalCode= arrayOfValues[10];
                arrayOfUsers.add(new User(id,nom,prenom,photo,latitude,longitude,altitude,pays,ville,province,postalCode));
                String x = "";
            }

            try{
                //Adapter
                myAdapterC adapter = new myAdapterC
                        (ctx,R.layout.one_listview_ligne_layout,arrayOfUsers);
                MainActivity.lstUsers.setAdapter(adapter);
            }catch (Exception ex){
                Log.i("error",ex.getMessage());
            }

            String x = "s";
        }
    }
    //==========================

}
