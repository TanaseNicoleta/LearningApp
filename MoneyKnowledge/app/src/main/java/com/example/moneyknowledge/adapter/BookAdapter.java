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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private int resource;
    private List<Book> books;
    private LayoutInflater inflater;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    public BookAdapter(@NonNull Context context,int resource,@NonNull List<Book> objects,LayoutInflater inflater){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.books=objects;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view=inflater.inflate(resource,parent,false);
        Book book=books.get(position);
        ImageView image = view.findViewById(R.id.image);

        StorageReference profileRef = storageReference.child("books/"+book.getId()+"/image.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
            }
        });

        if(book != null){
            TextView title = view.findViewById(R.id.title);
            title.setText("Titlu: " + book.getTitle());

            TextView auth=view.findViewById(R.id.author);
            auth.setText("Autor: " + book.getAuthor());

            TextView link = view.findViewById(R.id.link);
            link.setText(book.getDownloadLink());
        }

        return view;
    }
}