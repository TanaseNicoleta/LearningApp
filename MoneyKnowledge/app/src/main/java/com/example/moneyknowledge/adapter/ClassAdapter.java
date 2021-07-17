package com.example.moneyknowledge.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Book;
import com.example.moneyknowledge.model.Classes;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends ArrayAdapter<Classes> {
    private Context context;
    private int resource;
    private List<Classes> classes = new ArrayList<>();
    private LayoutInflater inflater;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public ClassAdapter(@NonNull Context context, int resource, @NonNull List<Classes> objects, LayoutInflater inflater){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.classes=objects;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = inflater.inflate(resource,parent,false);
        Classes class1 = classes.get(position);
        ImageView image = view.findViewById(R.id.image);

        StorageReference profileRef = storageReference.child("classes/"+class1.getId()+"/image.png");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
            }
        });

        if(class1 != null){
            TextView title = view.findViewById(R.id.title);
            title.setText("Titlu: " + class1.getTitle());

            TextView auth=view.findViewById(R.id.professor);
            auth.setText("Profesor: " + class1.getProfessors());

            TextView academy = view.findViewById(R.id.academy);
            academy.setText("Academie: " + class1.getAcademy());
        }

        return view;
    }
}