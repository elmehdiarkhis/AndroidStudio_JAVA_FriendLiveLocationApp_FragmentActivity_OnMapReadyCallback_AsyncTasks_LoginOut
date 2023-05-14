package com.example.maplocation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity implements Serializable {

    //Element====
    private EditText edtNomR;
    private EditText edtPrenomR;
    private EditText edtUserNameR;
    private EditText edtPassR;
    private Button btnRegister;
    public  Uri photoUri;
    //=====

    //Variable==
    String nom;
    String prenom;
    int photo;
    String userName;
    String pass;
    //==========

    //Firebase=======
    private FirebaseStorage fbStorage;
    private StorageReference storageReference;
    //==============

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        edtNomR = findViewById(R.id.idEdtNomR);
        edtPrenomR = findViewById(R.id.idEdtPrenomR);
        edtUserNameR = findViewById(R.id.idEdUserNameR2);
        edtPassR = findViewById(R.id.idEdPassR2);
        btnRegister = findViewById(R.id.idBtnTryToRegister);


        //Firebase=======
        fbStorage = FirebaseStorage.getInstance();
        storageReference = fbStorage.getReference();
        //==============
    }

    public void Register(View view) {
        nom = edtNomR.getText().toString();
        prenom = edtPrenomR.getText().toString();
        userName = edtUserNameR.getText().toString();
        pass = edtPassR.getText().toString();

        if(nom.length()==0 || prenom.length()==0 || userName.length()==0 || pass.length()==0){
            Toast.makeText(this, "Veuillez ne laisser aucun champ vide!!", Toast.LENGTH_SHORT).show();
        }else{

            //DEMANDE DE REQUETTE POUR INSERT un nouveau User dans Location==================
            MyAsyncTask myAsyncTAsk = new MyAsyncTask(Register.this);
            String command = "insertUser";
            myAsyncTAsk.execute(command,nom,prenom,photo,userName,pass);
            //===========================

        }

    }

    public void goToLogin(View view) {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    //Photo FIREBASE=====================================================

    //apres click , demander au user de chosir une photo
    public void UploadPhoto(View view) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }

    //apres choix Upload la photo to Firebase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode == RESULT_OK && data!=null) {
            photoUri = data.getData();
            UploadPhotoToFireBase();
        }
    }


    //Fonction qui permet d'upload la photo to fireBase
    private void UploadPhotoToFireBase() {
        //Help > Find > FireBase > cloud storage

        //Progress Bar
        final ProgressDialog myPd = new ProgressDialog(this);
        myPd.setTitle("Upload en cours ....");
        myPd.show();
        //======


        // Random Key va representer notre cle dans notre Base de donne
//        final String randomKey = UUID.randomUUID().toString();
        Random randomKey = new Random();
        photo = randomKey.nextInt(1000);
        //========

        // Create a reference
        StorageReference myRef = storageReference.child("images/"+(String.valueOf(photo)) );
        //====

        //upload
        myRef.putFile(photoUri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    myPd.dismiss();
                    btnRegister.setEnabled(true);
                    Toast.makeText(Register.this, "Votre photo a ete uploader", Toast.LENGTH_LONG).show();
                }
             })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    myPd.dismiss();
                    Toast.makeText(Register.this, "erreur d'upload", Toast.LENGTH_LONG).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot _taskSnapshot) {
                    double progressParCent = (100.00 * _taskSnapshot.getBytesTransferred()/_taskSnapshot.getTotalByteCount());
                    myPd.setMessage("Pourcentage : "+(int)progressParCent+"%");
                }
            });

    }
    //=====================



}
