package com.example.moneyknowledge.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Notes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteDialog extends AppCompatDialogFragment {
    public static final String NOTES = "notes";
    private Notes note;
    private TextView tvAddNote;
    Bundle bundle;
    String lessonId;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog_add_notes,null);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference(NOTES);

        bundle = getArguments();
        lessonId = bundle.getString("lessonId","");

        tvAddNote = view.findViewById(R.id.tv_addNotes);

        builder.setView(view)
                .setTitle(R.string.notite)
                .setNegativeButton(getString(R.string.renunta), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(R.string.salveaza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!tvAddNote.getText().toString().trim().isEmpty() && tvAddNote != null){
                            note = new Notes("notite-" + userId + "-" + lessonId, lessonId, userId, tvAddNote.getText().toString().trim());
                            database.child(note.getId()).setValue(note);
                        }
                    }
                });

        return builder.create();

    }

}
