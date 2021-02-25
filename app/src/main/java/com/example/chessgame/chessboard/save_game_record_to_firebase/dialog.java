package com.example.chessgame.chessboard.save_game_record_to_firebase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.chessgame.R;

public class dialog extends AppCompatDialogFragment {
    @NonNull
    private EditText name, description;
    private dialoglistener listener;



    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view).setTitle("save record").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("send to server", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sname = name.getText().toString();
                String sdescription = description.getText().toString();
                listener.applyTexts(sname,sdescription);
            }
        });
        name= view.findViewById(R.id.name);
        description= view.findViewById(R.id.description);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (dialoglistener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement dialog");
        }
    }
    public interface dialoglistener{
        void applyTexts(String name, String description);
    }
}
