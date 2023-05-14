package com.example.maplocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class myAdapterC extends ArrayAdapter<User> {

    //props------
    private Context context;
    private int res;
    private ArrayList<User> myArray;

    private ImageView img;
    private TextView txtNom;
    private TextView txtPrenom;
    private TextView txtPays;
    private TextView txtVille;

    //Firebase=======
    private FirebaseStorage fbStorage;
    private StorageReference storageReference;
    private RequestManager glide;
    //==============





    //Constructeur
    public myAdapterC(@NonNull Context _context, int _res, @NonNull List<User> _objects) {
        super(_context, _res, _objects);

        context = _context;
        res = _res;
        myArray = new ArrayList<User>();
        myArray = (ArrayList<User>) _objects;


        //Firebase=======
        fbStorage = FirebaseStorage.getInstance();
        storageReference = fbStorage.getReference();

        glide=Glide.with(context);
        //==============
    }




    //Overide de la fonction getView
    @NonNull
    @Override
    public View getView(int _position, @Nullable View _convertView, @NonNull ViewGroup _parent) {

        //recuperer le costum_layout
        _convertView = LayoutInflater.from(context).inflate(res,_parent,false);



        //Recuperer les Views
        img = (ImageView) _convertView.findViewById(R.id.img_ID);
        txtNom = (TextView) _convertView.findViewById(R.id.txtNom_ID);
        txtPrenom = (TextView) _convertView.findViewById(R.id.txtPrenom_ID);
        txtPays = (TextView) _convertView.findViewById(R.id.txtPays_ID);
        txtVille = (TextView) _convertView.findViewById(R.id.txtVille_ID);




//        glide.load(storageReference.child("images/"+myArray.get(_position).getPhoto())).into(img);



//        storageReference.child("/images/"+myArray.get(_position).getPhoto()).getBytes(1024*1024)
//                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                @Override
//                    public void onSuccess(byte[] data) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                    img.setImageBitmap(bitmap);
//                }
//        });


        img.setImageResource(R.drawable.myp);

        txtNom.setText(myArray.get(_position).getNom());
        txtPrenom.setText(myArray.get(_position).getPrenom());
        txtPays.setText(myArray.get(_position).getPays());
        txtVille.setText(myArray.get(_position).getVille());


        return _convertView;
    }




}
